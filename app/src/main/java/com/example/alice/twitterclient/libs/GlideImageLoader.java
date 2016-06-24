package com.example.alice.twitterclient.libs;

import android.app.DownloadManager;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.alice.twitterclient.libs.base.ImageLoader;

/**
 * Created by alice on 6/23/16.
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequesManager;

    public GlideImageLoader(RequestManager glideRequesManager) {
        this.glideRequesManager = glideRequesManager;
    }

    @Override
    public void load(ImageView imageView, String URL) {
        glideRequesManager
                .load(URL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override(600, 400)
                .into(imageView);

    }
}
