package com.example.alice.twitterclient.images;

import android.provider.MediaStore;

import com.example.alice.twitterclient.api.CustomTwitterApiClient;
import com.example.alice.twitterclient.entities.Image;
import com.example.alice.twitterclient.images.events.ImagesEvent;
import com.example.alice.twitterclient.libs.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
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

public class ImagesRepositoryImpl implements ImagesRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static  int TWEET_COUNT =100;  //# to  retreive

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {

        Callback<List<Tweet>>  callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Image> images = new ArrayList<Image>();
                for (Tweet  tweet :  result.data){
                    if (containsImages(tweet)){
                        Image tweetmodel = new Image(); //primera imagen del  tw

                        tweetmodel.setId(tweet.idStr);
                        tweetmodel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;

                        int index = tweetText.indexOf("http");
                        if (index > 0){
                            tweetText = tweetText.substring(0, index);
                        }

                        tweetmodel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);  //obtiene primera entidad
                        String imageURL  =  currentPhoto.mediaUrl;

                        tweetmodel.setImageURL(imageURL);

                    }
                }

                //order Twitters en funcion de los favoritos

                Collections.sort(images, new Comparator<Image>() {
                    @Override
                    public int compare(Image image, Image t1) {
                        return t1.getFavoriteCount() -image.getFavoriteCount();
                    }
                });

                post(images)

            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getLocalizedMessage());
            }
        };

        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true, callback);

    }

    private boolean containsImages(Tweet tweet){
        return     tweet.entities != null
                && tweet.entities.media != null
                && !tweet.entities.media.isEmpty();

    }


    private  void  post(List<Image> images){
        post(images, null);

    }

    private  void  post(String error){
        post(null, error);
    }

    private void  post(List<Image> images, String error){
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(images);
        eventBus.post(event);
    }
}
