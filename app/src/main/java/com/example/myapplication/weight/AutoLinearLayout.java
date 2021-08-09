package com.example.myapplication.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.example.myapplication.R;
import com.example.myapplication.utils.ScreenUtils;

import java.util.Hashtable;

/**
 * Create By shaodong on 2021/8/5 14:43
 */
public class AutoLinearLayout extends LinearLayout {
    private static final String TAG = "AutoLinearLayout";

    private static final int DEFAULT_ROWS = 2;
    private static final int DEFAULT_COLUMNS = 2;
    private static final int DEFAULT_HORIZONAL_SPACEING = 10;
    private static final int DEFAULT_VERTICAL_SPACEING = 10;
    private int mColumns;//列数
    private float mHorizonalSpaceing;//行距
    private float mVerticalSpaceing;//列距
    private int mLeft, mRight, mTop, mBottom;
    int height;
    int width;
    private Hashtable<View, Position> mHashMap = new Hashtable<>();

    public AutoLinearLayout(Context context) {
        super(context);
        init(context, null);
    }

    public AutoLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLinearLayout);
        mColumns = a.getInt(R.styleable.AutoLinearLayout_auto_col, DEFAULT_COLUMNS);
        mHorizonalSpaceing = a.getDimension(R.styleable.AutoLinearLayout_auto_spec_horizontal, dipTopx(context, DEFAULT_HORIZONAL_SPACEING));
        mVerticalSpaceing = a.getDimension(R.styleable.AutoLinearLayout_auto_spec_vertical, dipTopx(context, DEFAULT_VERTICAL_SPACEING));
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int count = getChildCount();
        int x = 0;
        int y = 0;
        mLeft = 0;
        mRight = 0;
        mTop = 0;
        mBottom = 0;

        int j = 0;
        int row = 0;

        int childWidth = (int) ((widthSize - mHorizonalSpaceing * (mColumns + 1)) / mColumns);
        int childHeight = ScreenUtils.dip2px(getContext(), 50);

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
            x += childWidth;
            Position position = new Position();
            mLeft = getPosition(i - j, i, childWidth);
            mRight = mLeft + childWidth;
            if (x > widthSize) {
                x = childWidth;
                y += childHeight;
                j = i;
                row++;
                mLeft = 0;
                mRight = mLeft + childWidth;
                mTop = y;
            }
            mBottom = mTop + childHeight;
            y = mTop;
            int column = i - j;
            position.left = (int) (mLeft + (column == 0 ? mHorizonalSpaceing : mHorizonalSpaceing * (column + 1)));
            position.top = (int) (mTop + (row == 0 ? mVerticalSpaceing : mVerticalSpaceing * (row + 1)));
            position.right = (int) (mRight + (column == 0 ? mHorizonalSpaceing : mHorizonalSpaceing * (column + 1)));
            position.bottom = (int) (mBottom + (row == 0 ? mVerticalSpaceing : mVerticalSpaceing * (row + 1)));
            mHashMap.put(child, position);
        }
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = getWidth() + getPaddingRight() + getPaddingLeft();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
                double fCount = count;
                double fColumns = mColumns;
                double ceil = fCount / fColumns;
                height = (int) (Math.ceil(ceil) * (childHeight+mVerticalSpaceing) + getPaddingBottom() + getPaddingTop()+mVerticalSpaceing) ;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            Position position = mHashMap.get(child);
            if (position != null) {
                child.layout(position.left, position.top, position.right, position.bottom);
            }
        }
    }

    private class Position {
        int left, top, right, bottom;
    }

    public int getPosition(int indexInRow, int childIndex, int childWidth) {
        if (indexInRow > 0) {
            return getPosition(indexInRow - 1, childIndex - 1, childWidth) + childWidth;
        }
        return getPaddingLeft();
    }

    private int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
