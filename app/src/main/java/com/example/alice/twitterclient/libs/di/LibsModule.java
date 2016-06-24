package com.example.alice.twitterclient.libs.di;



import android.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.alice.twitterclient.libs.GlideImageLoader;
import com.example.alice.twitterclient.libs.GreenRobotsEventBus;
import com.example.alice.twitterclient.libs.base.EventBus;
import com.example.alice.twitterclient.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by alice on 6/23/16.
 */

@Module
public class LibsModule {
    private Fragment fragment;

    /**
     * Crea un new GreenRobotsEventBus a partir del mio
     * @param eventBus
     * @return
     */
    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus ){
        return new GreenRobotsEventBus(eventBus);
    }

    /**
     * Retorna unstancia del GreenRobotEventBus
     * @return
     */
    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }


    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return  this.fragment;
    }




}
