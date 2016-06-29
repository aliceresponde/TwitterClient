package com.example.alice.twitterclient.hashtags;

import android.util.Log;

import com.example.alice.twitterclient.entities.Hashtag;
import com.example.alice.twitterclient.hashtags.events.HashtagEvent;
import com.example.alice.twitterclient.hashtags.ui.HashtagView;
import com.example.alice.twitterclient.images.ImagesInteractor;
import com.example.alice.twitterclient.images.ImagesPresenter;
import com.example.alice.twitterclient.images.events.ImagesEvent;
import com.example.alice.twitterclient.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 6/25/16.
 * Management activity  cycle
 * EventBus
 * getImageTw
 */

public class HashtagPresenterImp implements HashtagPresenter {

    private HashtagView view;
    private EventBus eventBus;
    private HashtagInteractor interactor;

    public HashtagPresenterImp(HashtagView view, EventBus eventBus, HashtagInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }


    @Override
    public void onResume()
    {
        Log.i(HashtagPresenterImp.class.getSimpleName(), "onResume  -  register");
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        Log.i(HashtagPresenterImp.class.getSimpleName(), "onResume  -  unregister");
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }


    @Override
    public void getHashtagsTweets() {
        interactor.execute();
    }


    /**
     * Get response to request images {error or Lsit<Images> }
     * @param event
     */
    @Subscribe
    @Override
    public void onEventMainThread(HashtagEvent event) {

        String errorMesagge = event.getError();
        Log.i(HashtagPresenterImp.class.getSimpleName(), "onEventMainThread  ");

        if (view !=null){
            view.hideProgessBar();
            view.showHashtags();

            if (errorMesagge != null){
                view.onError(errorMesagge);
            }else{
                view.setContent(event.getHashtags());
            }

        }
    }
}
