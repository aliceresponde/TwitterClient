package com.example.alice.twitterclient.hashtags;

import com.example.alice.twitterclient.hashtags.events.HashtagEvent;
import com.example.alice.twitterclient.images.events.ImagesEvent;

/**
 * Created by alice on 6/25/16.
 * Management activity  cycle
 * EventBus
 * getImageTw
 */

public interface HashtagPresenter {
    void  onResume();
    void  onPause();
    void  onDestroy();
    void  getHashtagsTweets();
    void  onEventMainThread(HashtagEvent event);
}
