package com.example.alice.twitterclient.entities;

import java.util.List;

/**
 * Created by alice on 6/25/16.
 */

public class Hashtag {
    private  String id;
    private  String tweetText;
    private  int favoriteCount;
    private  List<String> hashtags; //asociados al  twi
    private  final static String BASE_TWEET_URL = "https://twitter.com/null/status/";


    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }


    public String getTweetURL (){
        return  BASE_TWEET_URL + this.id;
    }
}
