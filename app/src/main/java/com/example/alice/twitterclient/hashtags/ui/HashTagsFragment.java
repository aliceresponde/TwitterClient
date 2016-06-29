package com.example.alice.twitterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.alice.twitterclient.App;
import com.example.alice.twitterclient.R;
import com.example.alice.twitterclient.entities.Hashtag;
import com.example.alice.twitterclient.hashtags.HashtagPresenter;
import com.example.alice.twitterclient.hashtags.di.HashtagComponent;
import com.example.alice.twitterclient.hashtags.ui.adapters.HashtagAdapter;
import com.example.alice.twitterclient.hashtags.ui.adapters.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashTagsFragment extends Fragment implements HashtagView, OnItemClickListener {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    ContentLoadingProgressBar progressBar;
    @Bind(R.id.container)
    FrameLayout container;

    //to inject para no inicializarlo en el cosntructor, sin esta injeccion se presentara un error en tiempo de  ejecucion
    @Inject
    HashtagAdapter adapter;
    @Inject
    HashtagPresenter presenter;


    public HashTagsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();

        setupRecyclerView();

        presenter.getHashtagsTweets();

        return view;
    }

//    ===================================PRESENTER====================================================
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

//    ============================================================================================

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager( linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        App app = (App)getActivity().getApplication();
        //el componente viene el applicationCas
        HashtagComponent hashtagComponent = app.getHashtagComponent(this, this);
        hashtagComponent.inject(this);

    }

//    ============================View============================================================

    @Override
    public void showHashtags() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHashtags() {
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
    public void setContent(List<Hashtag> items) {
        adapter.setItems(items);
    }

//    ===============================onClick===========================================
    @Override
    public void onItemClick(Hashtag hashtag) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hashtag.getTweetURL()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
