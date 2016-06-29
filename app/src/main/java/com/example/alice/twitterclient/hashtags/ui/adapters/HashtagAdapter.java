package com.example.alice.twitterclient.hashtags.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alice.twitterclient.R;
import com.example.alice.twitterclient.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alice on 6/25/16.
 */

public class HashtagAdapter extends RecyclerView.Adapter<HashtagAdapter.ViewHolder> {



    private List<Hashtag> dataSet;

    private OnItemClickListener clickListener;


    public HashtagAdapter(List<Hashtag> dataSet, OnItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hashtag, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag hashtag = dataSet.get(position);

        holder.setOnClickListener(hashtag, clickListener);
        holder.txtTweet.setText(hashtag.getTweetText());
        holder.setItems(hashtag.getHashtags());
    }

    public void setItems(List<Hashtag> newItems) {
        dataSet.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

//    =======================VIEW HOLDER ====================================

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtTweet)
        TextView txtTweet;
        @Bind(R.id.recyclerView)
        RecyclerView recyclerView;
        private View view;

        private HashTagListAdapter adapter;  // reference to txt
        private  ArrayList<String> items;

        /**
         *
         * @param itemView
         * @param context  - pa ra construir el layout manager dentro del  view holder
         */
        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;

            items = new ArrayList<String>();
            adapter = new HashTagListAdapter(items);

            // Crear a  custom Layout mananger  with acontext and #colums
            CustomGridLayoutManager  layoutManager  =  new CustomGridLayoutManager(context, 3) ;
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

        }

        public void setItems(List<String> newItems){
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }



        public void setOnClickListener(final Hashtag hashtag, final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(hashtag);
                }
            });
        }
    }
}
