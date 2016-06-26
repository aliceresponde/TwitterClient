package com.example.alice.twitterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.alice.twitterclient.App;
import com.example.alice.twitterclient.R;
import com.example.alice.twitterclient.entities.Image;
import com.example.alice.twitterclient.images.ImagesPresenter;
import com.example.alice.twitterclient.images.di.ImagesComponent;
import com.example.alice.twitterclient.images.ui.adapters.ImagesAdapter;
import com.example.alice.twitterclient.images.ui.adapters.OnItemClickListener;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ContentLoadingProgressBar progressBar;
    @Bind(R.id.container)
    FrameLayout container;


    //to inject para no inicializarlo en el cosntructor, sin esta injeccion se presentara un error en tiempo de  ejecucion
    @Inject
    ImagesAdapter adapter;
    @Inject
    ImagesPresenter presenter;


    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();

        setupRecyclerView();

        presenter.getImagesTweets();

        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager( new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
    }


    private void setupInjection() {
        App app =  (App) getActivity().getApplication();
        ImagesComponent imagesComponent = app.getImagesComponent(this, this, this);
        imagesComponent.inject(this);

    }

//    ============================Presenter ciclo de vida =========================================

    @Override
    public void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    //    ===============================ImagesView  UI================================================

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgessBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {

        Log.i(ImagesFragment.class.getSimpleName(), "setContent" + items.size());
        adapter.setItems(items);
    }

//    ===============================OnItemClickListener =========================================

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetURL()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
