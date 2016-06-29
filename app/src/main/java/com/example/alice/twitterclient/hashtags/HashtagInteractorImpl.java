package com.example.alice.twitterclient.hashtags;



/**
 * Created by alice on 6/25/16.
 */

public class HashtagInteractorImpl implements HashtagInteractor{


    HashtagRepository repository;


    public HashtagInteractorImpl(HashtagRepository repository) {
        this.repository = repository;
    }

    /**
     * //solicita el contenido de las imagenes
     */
    @Override
    public void execute() {
        repository.getHashtags();

    }

}
