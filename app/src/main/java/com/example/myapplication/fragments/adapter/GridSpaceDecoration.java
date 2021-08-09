package com.example.myapplication.fragments.adapter;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public final class GridSpaceDecoration extends RecyclerView.ItemDecoration {

    private final int mSpace;

    public GridSpaceDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
    }

    @SuppressWarnings("all")
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int position = recyclerView.getChildAdapterPosition(view);
        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
        int offset = mSpace / 2;
        for (int i = 0; i < spanCount; i++) {
            //第一排，顶部不画
            if (i < mSpace) {
                //最左边的，左边不画
                if (i % mSpace == 0) {
                    outRect.set(0, 0, offset, 0);
                    //最右边，右边不画
                } else if (i % mSpace == mSpace - 1) {
                    outRect.set(offset, 0, 0, 0);
                } else {
                    outRect.set(offset, 0, offset, 0);
                }
            } else {
                //上下的分割线，就从第二排开始，每个区域的顶部直接添加设定大小，不用再均分了
                if (i % mSpace == 0) {
                    outRect.set(0, mSpace, offset, 0);
                } else if (i % mSpace == mSpace - 1) {
                    outRect.set(offset, mSpace, 0, 0);
                } else {
                    outRect.set(offset, mSpace, offset, 0);
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
    }
}