package com.example.alice.twitterclient.images.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alice.twitterclient.R;
import com.example.alice.twitterclient.entities.Image;
import com.example.alice.twitterclient.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alice on 6/25/16.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {


    private List<Image> dataSet;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(List<Image> dataSet, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.dataSet = dataSet;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_images, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imageTweet = dataSet.get(position);

        holder.setOnClickListener(imageTweet, clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());

        //cargo imagen del tw desde la url al holder
        imageLoader.load(holder.imgMedia, imageTweet.getImageURL());

    }

    public void setItems(List<Image> newItems){
        dataSet.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

//    =======================VIEW HOLDER ====================================

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgMedia)
        ImageView imgMedia;
        @Bind(R.id.txtTweet)
        TextView txtTweet;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final  Image image, final  OnItemClickListener listener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(image);
                }
            });
        }
    }
}
