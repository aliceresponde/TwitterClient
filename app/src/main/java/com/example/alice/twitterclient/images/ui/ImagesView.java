package com.example.alice.twitterclient.images.ui;

import com.example.alice.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by alice on 6/25/16.
 */

public interface ImagesView {

    void showElements();
    void hideElements();
    void showProgressBar();
    void hideProgessBar();

    void onError(String error);
    void setContent(List<Image> items);

}
