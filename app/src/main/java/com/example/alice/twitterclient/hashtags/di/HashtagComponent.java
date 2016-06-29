package com.example.alice.twitterclient.hashtags.di;

import com.example.alice.twitterclient.entities.Hashtag;
import com.example.alice.twitterclient.hashtags.HashtagPresenter;
import com.example.alice.twitterclient.hashtags.ui.HashTagsFragment;
import com.example.alice.twitterclient.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 6/25/16.
 */

@Singleton @Component (modules = {LibsModule.class, HashtagModule.class})
public interface HashtagComponent {
//    =============OPCION I : injecto el target==========

    //injecto el target
    void inject(HashTagsFragment fragment);

//    =============OPCION II: obtengo el presenter==========

    HashtagPresenter getPresenter();
}
