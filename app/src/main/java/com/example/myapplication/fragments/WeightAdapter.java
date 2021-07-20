package com.example.myapplication.fragments;

import android.content.Context;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseHolder;
import com.example.myapplication.base.onItemClickListener;

import java.util.List;

/**
 * Create By shaodong on 2021/7/20 10:08
 */
public class WeightAdapter extends BaseAdapter<BaseHolder, String> {

    public WeightAdapter(List<String> dataList, Context context) {
        super(dataList, context);
    }

    public WeightAdapter(List<String> dataList, Context context, onItemClickListener<String> clickListener) {
        super(dataList, context, clickListener);
    }

    @Override
    protected int layoutId() {
        return R.layout.item_weight_layout;
    }

    @Override
    protected BaseHolder getViewHolder(View itemView) {
        return new BaseHolder(itemView);
    }

    @Override
    protected void setValue(BaseHolder holder, String data) {
        holder.setText(R.id.tv_content, data);
    }
}
