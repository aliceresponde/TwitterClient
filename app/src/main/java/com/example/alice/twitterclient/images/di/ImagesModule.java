package com.example.alice.twitterclient.images.di;

import android.widget.ImageView;

import com.example.alice.twitterclient.api.CustomTwitterApiClient;
import com.example.alice.twitterclient.entities.Image;
import com.example.alice.twitterclient.images.ImagesInteractor;
import com.example.alice.twitterclient.images.ImagesInteractorImpl;
import com.example.alice.twitterclient.images.ImagesPresenter;
import com.example.alice.twitterclient.images.ImagesPresenterImp;
import com.example.alice.twitterclient.images.ImagesRepository;
import com.example.alice.twitterclient.images.ImagesRepositoryImpl;
import com.example.alice.twitterclient.images.ui.ImagesView;
import com.example.alice.twitterclient.images.ui.adapters.ImagesAdapter;
import com.example.alice.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.alice.twitterclient.libs.base.EventBus;
import com.example.alice.twitterclient.libs.base.ImageLoader;
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
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }


//    =========================ImagesAdapter===============================================================================================

    /**
     *Nota los parametros del constructor del adaptador q no esten en el constructor deben ser proveeidos @Provider @Singleton  por un petodo providesXXXX
     * @param dataSet -
     * @param imageLoader - provisto en  libs/di/LibsModule.providesImageLoader  puedo acceder a esto en tiempo de ejecion via  dagger
     * @param clickListener  - del constructor
     * @return
     */
    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataSet, ImageLoader imageLoader, OnItemClickListener clickListener){
        return  new ImagesAdapter(dataSet, imageLoader, clickListener);
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
    List<Image> providesItemList(){
        return  new ArrayList<Image>();
    }

    //    ======ImagesPresenter: Recibe como parametros, los mismos q su constructor======================================================

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor){
        return  new ImagesPresenterImp(view, eventBus , interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView(){
        return  this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return  new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client){
        return  new ImagesRepositoryImpl(eventBus, client);
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
