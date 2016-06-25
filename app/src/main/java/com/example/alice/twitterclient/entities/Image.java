package com.example.alice.twitterclient.entities;

/**
 * Created by alice on 6/25/16.
 */

public class Image {
    private  String id;
    private  String imageURL;
    private  String tweetText;
    private  int favoriteCount;
    private  final static String BASE_TWEET_URL = "https://twitter.com/null/status/";



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
