package com.example.alice.twitterclient.hashtags.di;

import com.example.alice.twitterclient.api.CustomTwitterApiClient;
import com.example.alice.twitterclient.entities.Hashtag;
import com.example.alice.twitterclient.hashtags.HashtagInteractor;
import com.example.alice.twitterclient.hashtags.HashtagInteractorImpl;
import com.example.alice.twitterclient.hashtags.HashtagPresenter;
import com.example.alice.twitterclient.hashtags.HashtagPresenterImp;
import com.example.alice.twitterclient.hashtags.HashtagRepository;
import com.example.alice.twitterclient.hashtags.HashtagRepositoryImpl;
import com.example.alice.twitterclient.hashtags.ui.HashtagView;
import com.example.alice.twitterclient.hashtags.ui.adapters.HashtagAdapter;
import com.example.alice.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import com.example.alice.twitterclient.libs.base.EventBus;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alice on 6/25/16.
 * Provee el
 * - ImagesAdapter
 */

@Module
public class HashtagModule {
    private HashtagView view;
    private OnItemClickListener clickListener;

    public HashtagModule(HashtagView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }


//    =========================ImagesAdapter===============================================================================================

    /**
     *Nota los parametros del constructor del adaptador q no esten en el constructor deben ser proveeidos @Provider @Singleton  por un petodo providesXXXX
     * @param dataSet -
     * @param clickListener  - del constructor
     * @return
     */
    @Provides
    @Singleton
    HashtagAdapter providesAdapter(List<Hashtag> dataSet, OnItemClickListener clickListener){
        return  new HashtagAdapter(dataSet, clickListener);
    }


    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return  this.clickListener;
    }

    /**
     * crear un nuevo lis<Image>  y lo regresa
     * @return
     */
    @Provides
    @Singleton
    List<Hashtag> providesItemList(){
        return  new ArrayList<Hashtag>();
    }

    //    ======ImagesPresenter: Recibe como parametros, los mismos q su constructor======================================================

    @Provides
    @Singleton
    HashtagPresenter providesHashtagPresenter(HashtagView view, EventBus eventBus, HashtagInteractor interactor){
        return  new HashtagPresenterImp(view, eventBus, interactor) {
        };
    }

    @Provides
    @Singleton
    HashtagView providesHashtagView(){
        return  this.view;
    }

    @Provides
    @Singleton
    HashtagInteractor providesImagesInteractor(HashtagRepository repository){
        return  new HashtagInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new HashtagRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session){
        return  new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitterSession(){
        return Twitter.getSessionManager().getActiveSession();
    }

}
