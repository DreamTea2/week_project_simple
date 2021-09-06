package com.example.myapplication.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.fragments.adapter.AssortAdapter;
import com.example.myapplication.fragments.bean.Assort;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By shaodong on 2021/8/10 14:21
 * 一个简单的三级筛选封装
 */
public class SimpleRecyclerView extends RecyclerView {

    private static final String TAG = "SimpleRecyclerView";

    private LinearLayoutManager mLayoutManager;
    /* 水平方向  默认垂直*/
    private int horizon = RecyclerView.VERTICAL;
    private AssortAdapter assortAdapter;
    private List<Assort> datas = new ArrayList<>();
    private Assort mChooseDate;
    private OnItemChooseClickListener itemClickListener;
    private Type type;

    public SimpleRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public SimpleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Nullable
    @Override
    public AssortAdapter getAdapter() {
        return assortAdapter;
    }

    private void init() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(horizon);
        setLayoutManager(mLayoutManager);

        assortAdapter = new AssortAdapter(datas, getContext(), (assort, position, view) -> {
            assortAdapter.updateAdapter(position);
            if (itemClickListener != null) {
                mChooseDate = assort;
                itemClickListener.onItemClickListener(position, assort, type);
            }
        });
        setAdapter(assortAdapter);
        setItemAnimator(new DefaultItemAnimator());
    }

    public void update(List<Assort> childData, Type type) {
        this.datas.clear();
        if (childData == null || childData.size() == 0) {
            Log.d(TAG, "数据异常");
            return;
        }
        this.type = type;
        this.datas.addAll(childData);
        /* 避免无响应*/
        assortAdapter.updateAdapter(0);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    /**
     * 获取线性布局管理器
     *
     * @return
     */
    private LinearLayoutManager getLinearLayoutManager() {
        return mLayoutManager;
    }

    public Assort getChooseData() {
        return mChooseDate;
    }

    public void setOnSortClickListener(OnItemChooseClickListener itemClick) {
        this.itemClickListener = itemClick;
    }

    public enum Type {
        PARENT, CHILD, NEXT
    }

    public interface OnItemChooseClickListener {
        void onItemClickListener(int position, Assort assort, Type type);
    }
}
