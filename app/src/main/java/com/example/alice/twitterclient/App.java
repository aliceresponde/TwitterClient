package com.example.alice.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;


import com.example.alice.twitterclient.images.di.DaggerImagesComponent;
import com.example.alice.twitterclient.images.di.ImagesComponent;
import com.example.alice.twitterclient.images.di.ImagesModule;
import com.example.alice.twitterclient.images.ui.ImagesView;
import com.example.alice.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.alice.twitterclient.libs.di.LibsModule;
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

    /**
     *
     * @param fragment - el target del componente  ---> ImagesFragment
     * @param view     - intefaz q implementa el target
     * @param clickListener  - Interfaz q implementa el target
     * @return
     */
    public ImagesComponent getImagesComponent(Fragment fragment  , ImagesView view , OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();

    }
}
