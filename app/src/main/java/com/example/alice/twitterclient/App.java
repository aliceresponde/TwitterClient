package com.example.alice.twitterclient;

import android.app.Application;
import android.os.Build;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by alice on 6/22/16.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY , BuildConfig.TWITTER_SECRET );
        Fabric.with(this , new Twitter(authConfig));
    }
}
