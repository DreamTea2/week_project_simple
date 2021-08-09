package com.example.myapplication.fragments.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseHolder;
import com.example.myapplication.base.onItemClickListener;
import com.example.myapplication.fragments.bean.DataBean;
import com.example.myapplication.weight.StatusLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By shaodong on 2021/8/3 10:00
 */
public class HomeAdapter extends BaseAdapter<BaseHolder, Object> {
    private static final String TAG = "HomeAdapter";
    /*标题*/
    private final int TYPE_TITLE = 1;
    /*热门推荐*/
    private final int TYPE_HOT = 2;
    /*订阅视频*/
    private final int TYPE_SUBSCRIPT = 3;
    /*公开课*/
    private final int TYPE_OPEN = 4;
    /*讲师*/
    private final int TYPE_TEACHER = 5;

    private List<Object> dataList = new ArrayList<>();

    public HomeAdapter(List<Object> dataList, Context context) {
        super(dataList, context);
        this.dataList = dataList;
    }

    public HomeAdapter(List<Object> dataList, Context context, onItemClickListener<Object> clickListener) {
        super(dataList, context, clickListener);
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        DataBean dataBean = (DataBean) dataList.get(position);
        switch (dataBean.getItemType()) {
            case 0:
            case 1:
                return TYPE_TITLE;
            case 2:
                return TYPE_HOT;
            case 3:
                return TYPE_SUBSCRIPT;
            case 4:
                return TYPE_OPEN;
            default:
                return TYPE_TEACHER;
        }
    }

    @Override
    protected int layoutId(int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                return R.layout.item_title;
            case TYPE_HOT:
            case TYPE_SUBSCRIPT:
                return R.layout.item_grid;
            case TYPE_OPEN:
                return R.layout.item_class;
            default:
                return R.layout.item_teach;
        }
    }

    @Override
    protected BaseHolder getViewHolder(View itemView, int viewType, ViewGroup parent) {
        switch (viewType) {
            case TYPE_TITLE:
                return new TitleHolder(itemView);
            case TYPE_HOT:
                return new HotHolder(itemView);
            case TYPE_SUBSCRIPT:
                return new SubHolder(itemView);
            case TYPE_OPEN:
                return new OpenHolder(itemView);
            default:
                return new TeachHolder(itemView);
        }
    }

    @Override
    protected void setValue(BaseHolder holder, Object data) {
        if (holder instanceof TitleHolder) {
            DataBean titleBean = (DataBean) data;
            holder.setText(R.id.item_title, titleBean.getTitleBean().getTitleName());
        } else if (holder instanceof HotHolder) {
            StatusLayout view = holder.getView(R.id.status);
            view.showEmpty();
        }
    }

    public class TitleHolder extends BaseHolder {
        public TitleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class HotHolder extends BaseHolder {
        public HotHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class SubHolder extends BaseHolder {
        public SubHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class OpenHolder extends BaseHolder {
        public OpenHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class TeachHolder extends BaseHolder {
        public TeachHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
