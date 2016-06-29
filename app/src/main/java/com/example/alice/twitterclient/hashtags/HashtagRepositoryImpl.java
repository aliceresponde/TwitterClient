package com.example.alice.twitterclient.hashtags;

import com.example.alice.twitterclient.api.CustomTwitterApiClient;
import com.example.alice.twitterclient.entities.Hashtag;
import com.example.alice.twitterclient.entities.Image;
import com.example.alice.twitterclient.hashtags.events.HashtagEvent;
import com.example.alice.twitterclient.images.ImageRepository;
import com.example.alice.twitterclient.images.events.ImagesEvent;
import com.example.alice.twitterclient.libs.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by alice on 6/25/16.
 * Trae las imagenes de los TW  de internet
 */

public class HashtagRepositoryImpl implements HashtagRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static  int TWEET_COUNT =100;  //# to  retreive

    public HashtagRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    private boolean containsHashtags(Tweet tweet){
        return     tweet.entities != null
                && tweet.entities.hashtags != null
                && !tweet.entities.hashtags.isEmpty();

    }


    private  void  post(List<Hashtag> hashtags){
        post(hashtags, null);

    }

    private  void  post(String error){
        post(null, error);
    }

    private void  post(List<Hashtag> hashtags, String error){
        HashtagEvent event = new HashtagEvent();
        event.setError(error);
        event.setHashtags(hashtags);
        eventBus.post(event);
    }

    @Override
    public void getHashtags() {
        Callback<List<Tweet>>  callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Hashtag> items = new ArrayList<Hashtag>();

                for (Tweet  tweet :  result.data){
                    if (containsHashtags(tweet)){
                        Hashtag tweetmodel = new Hashtag(); //primera imagen del  tw

                        tweetmodel.setId(tweet.idStr);
                        tweetmodel.setFavoriteCount(tweet.favoriteCount);
                        tweetmodel.setTweetText(tweet.text);

                        List<String> hashtags  = new ArrayList<String>();

                        for (HashtagEntity hashtag : tweet.entities.hashtags){
                                hashtags.add(hashtag.text);
                        }

                        tweetmodel.setHashtags(hashtags);
                        items.add(tweetmodel);

                    }
                }

                //order Twitters en funcion de los favoritos

                Collections.sort(items, new Comparator<Hashtag>() {
                    @Override
                    public int compare(Hashtag hashtag, Hashtag t1) {
                        return t1.getFavoriteCount() -hashtag.getFavoriteCount();
                    }
                });

                post(items);

            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getLocalizedMessage());
            }
        };

        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true, callback);
    }
}
