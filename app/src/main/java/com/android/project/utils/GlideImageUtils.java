package com.android.project.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.android.project.ui.MApplication;
import com.android.project.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class GlideImageUtils {
    private static GlideImageUtils mInstance;

    public static GlideImageUtils getInstance() {
        if (mInstance == null) {
            mInstance = new GlideImageUtils();
        }
        return mInstance;
    }

    public void loadRound(Context context, ImageView view, String url, Boolean showLoader) {
        if (!TextUtils.isEmpty(url) && context != null) {
            final RequestOptions requestOptions =
                    RequestOptions.circleCropTransform();
            loadImage(context, view, url, requestOptions, showLoader);
        }
    }

    public void loadRect(Context context, ImageView view, String url, Boolean showLoader) {
        if (!TextUtils.isEmpty(url) && context != null) {
            final RequestOptions requestOptions =
                    RequestOptions.centerCropTransform();
            loadImage(context, view, url, requestOptions, showLoader);
        }
    }

    public void loadRectNoCut(Context context, ImageView view, String url, Boolean showLoader) {
        if (!TextUtils.isEmpty(url) && context != null) {
            final RequestOptions requestOptions =
                    RequestOptions.fitCenterTransform();
            loadImage(context, view, url, requestOptions, showLoader);
        }
    }

    public void loadRoundCorner(
            Context context,
            ImageView view,
            String url,
            int roundedCorners,
            Boolean showLoader
    ) {
        if (!TextUtils.isEmpty(url) && context != null) {
            final RequestOptions requestOptions =
                    new RequestOptions().transform(new CenterCrop(), new RoundedCorners(roundedCorners));
            loadImage(context, view, url, requestOptions, showLoader);
        }
    }

    private void loadImage(
            Context context,
            ImageView view,
            String url,
            RequestOptions requestOptions,
            Boolean showLoader
    ) {
        if (!MApplication.getInstance().isContextExisted(context)) {
            return;
        }

        try {
            if (showLoader) {
                final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
                circularProgressDrawable.setStrokeWidth(6F);
                circularProgressDrawable.setColorFilter(
                        MApplication.getInstance().getResources().getColor(
                                R.color.colorAccent
                        ), PorterDuff.Mode.SRC
                );
                circularProgressDrawable.setCenterRadius(40F);
                circularProgressDrawable.start();

                Glide.with(context)
                        .load(url)
                        .thumbnail(0.2f)
                        .apply(requestOptions)
                        .placeholder(circularProgressDrawable)
                        .into(view);
            } else
                Glide.with(context)
                        .load(url)
                        .thumbnail(0.2f)
                        .apply(requestOptions)
                        .into(view);
        } catch (Exception e) {
            LogUtils.showError(e);
        }
    }

    public void loadGif(Context context, ImageView gifView, int gifDrawable, int loopCount) {
        Glide.with(context).asGif().load(gifDrawable).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(loopCount);
                return false;
            }
        }).into(gifView).clearOnDetach();
    }

    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }
}
