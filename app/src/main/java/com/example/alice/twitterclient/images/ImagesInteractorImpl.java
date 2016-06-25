package com.example.alice.twitterclient.images;

/**
 * Created by alice on 6/25/16.
 */

public class ImagesInteractorImpl implements  ImagesInteractor{


    ImagesRepository repository;


    public ImagesInteractorImpl(ImagesRepository repository) {
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
