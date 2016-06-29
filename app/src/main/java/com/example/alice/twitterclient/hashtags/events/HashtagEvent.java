package com.example.alice.twitterclient.hashtags.events;

import com.example.alice.twitterclient.entities.Hashtag;
import com.example.alice.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by alice on 6/25/16.
 * Error
 * Imagenes traidas de API de TW
 *
 */
public class HashtagEvent {
    private  String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
