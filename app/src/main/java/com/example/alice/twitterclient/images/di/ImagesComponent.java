package com.example.alice.twitterclient.images.di;

import android.graphics.ImageFormat;

import com.example.alice.twitterclient.images.ImagesPresenter;
import com.example.alice.twitterclient.images.ui.ImagesFragment;
import com.example.alice.twitterclient.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 6/25/16.
 */

@Singleton @Component (modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
//    =============OPCION I : injecto el target==========

    //injecto el target
    void inject(ImagesFragment fragment);

//    =============OPCION II: obtengo el presenter==========

    ImagesPresenter getPresenter();
}
