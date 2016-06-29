package com.example.alice.twitterclient.images;

/**
 * Created by alice on 6/25/16.
 */

public class ImagesInteractorImpl implements  ImagesInteractor{


    ImageRepository repository;


    public ImagesInteractorImpl(ImageRepository repository) {
        this.repository = repository;
    }

    /**
     * //solicita el contenido de las imagenes
     */
    @Override
    public void execute() {
        repository.getImages();

    }

}
