package com.example.alice.twitterclient.images;

import android.widget.ImageView;

import com.example.alice.twitterclient.images.events.ImagesEvent;
import com.example.alice.twitterclient.images.ui.ImagesView;
import com.example.alice.twitterclient.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 6/25/16.
 * Management activity  cycle
 * EventBus
 * getImageTw
 */

public class ImagesPresenterImp  implements ImagesPresenter {

    private ImagesView view;
    private EventBus eventBus;
    private ImagesInteractor interactor;

    public ImagesPresenterImp(ImagesView view, EventBus eventBus, ImagesInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }


    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    /**
     * start retreive images
     */
    @Override
    public void getImagesTweets() {
        if (view !=null){
            view.hideImages();
            view.showProgressBar();
        }

        interactor.execute();
    }

    /**
     * Get response to request images {error or Lsit<Images> }
     * @param event
     */
    @Subscribe
    @Override
    public void onEventMainThread(ImagesEvent event) {

        String errorMesagge = event.getError();

        if (view !=null){
            view.hideProgessBar();
            view.showImages();

            if (errorMesagge!=null){
                view.onError(errorMesagge);
            }else{
                view.setContent(event.getImages());
            }

        }
    }
}
