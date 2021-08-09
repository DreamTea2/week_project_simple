package com.example.myapplication.fragments.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.fragments.bean.BannerBean;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.List;

/**
 * Create By shaodong on 2021/8/3 9:44
 */
public class ImageAdapter extends BannerAdapter<BannerBean, ImageAdapter.BannerHolder> {
    private static final String TAG = "ImageAdapter";

    private Context context;

    public ImageAdapter(List<BannerBean> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public BannerHolder onCreateHolder(ViewGroup parent, int viewType) {
        /* 建立新的ImageView */
        ImageView iv = new ImageView(parent.getContext());
        iv.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerHolder(iv);
    }

    @Override
    public void onBindView(BannerHolder holder, BannerBean data, int position, int size) {
        Glide.with(context)
                .load(data.getImageUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.imageView);
    }

    class BannerHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerHolder(@NonNull ImageView itemView) {
            super(itemView);
            this.imageView = itemView;
        }
    }
}
