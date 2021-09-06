package com.example.myapplication.fragments.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.request.transition.Transition;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseHolder;
import com.example.myapplication.base.onItemClickListener;
import com.example.myapplication.fragments.bean.Assort;

import java.util.List;

/**
 * Create By shaodong on 2021/8/10 14:55
 */
public class AssortAdapter extends BaseAdapter<BaseHolder, Assort> {

    private static final String TAG = "AssortAdapter";
    /*  当前选中的Position */
    private int currentPosition = -1;
    private Context context;

    public AssortAdapter(List<Assort> dataList, Context context, onItemClickListener<Assort> clickListener) {
        super(dataList, context, clickListener);
        this.context = context;
    }

    @Override
    protected int layoutId(int viewType) {
        return R.layout.adapter_simple;
    }

    @Override
    protected BaseHolder getViewHolder(View itemView, int viewType, ViewGroup viewGroup) {
        return new BaseHolder(itemView);
    }

    @Override
    protected void setValue(BaseHolder holder, Assort data) {
        /* 设置课程名称*/
        holder.setText(R.id.tv_subject_name, data.getSubjectName());
        int adapterPosition = holder.getAdapterPosition();
        if (currentPosition == adapterPosition) {
            /* 如果选中的话就设置成白色 */
            holder.getView(R.id.parent_layout).setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            holder.getView(R.id.end_line).setVisibility(View.VISIBLE);
        } else {
            /* 否则隐藏*/
            holder.getView(R.id.end_line).setVisibility(View.INVISIBLE);
        }
    }

    /* 更新UI  */
    public void updateAdapter(int position) {
        if (currentPosition != position ) {
            currentPosition = position;
            notifyDataSetChanged();
        }
    }

}
