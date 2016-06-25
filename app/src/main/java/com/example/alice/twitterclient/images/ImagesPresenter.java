package com.example.alice.twitterclient.images;

import com.example.alice.twitterclient.images.events.ImagesEvent;

/**
 * Created by alice on 6/25/16.
 * Management activity  cycle
 * EventBus
 * getImageTw
 */

public interface ImagesPresenter {
    void  onResume();
    void  onPause();
    void  onDestroy();
    void  getImagesTweets();
    void  onEventMainThread(ImagesEvent event);
}
