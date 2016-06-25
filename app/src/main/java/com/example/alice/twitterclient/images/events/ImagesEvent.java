package com.example.alice.twitterclient.images.events;

import com.example.alice.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by alice on 6/25/16.
 * Error
 * Imagenes traidas de API de TW
 *
 */
public class ImagesEvent {
    private  String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
