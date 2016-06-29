package com.example.alice.twitterclient.hashtags.ui;

import com.example.alice.twitterclient.entities.Hashtag;
import com.example.alice.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by alice on 6/25/16.
 */

public interface HashtagView {

    void showHashtags();
    void hideHashtags();
    void showProgressBar();
    void hideProgessBar();

    void onError(String error);
    void setContent(List<Hashtag> items);

}
