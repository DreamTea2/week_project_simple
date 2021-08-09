package com.example.myapplication.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.myapplication.utils.ScreenUtils;

/**
 * Create By shaodong on 2021/8/3 10:48
 */
public class LeftRect extends View {

    private Paint outPaint;
    private Paint innerPaint;
    private Rect outRect;
    private Rect innerRect;

    private int marginTopAndBottom = ScreenUtils.dip2px(getContext(), 3);

    public LeftRect(Context context) {
        this(context, null);
    }

    public LeftRect(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        outPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outPaint.setStrokeCap(Paint.Cap.BUTT);
        outPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        outPaint.setColor(Color.parseColor("#57B6DF"));

        innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        innerPaint.setStrokeCap(Paint.Cap.BUTT);
        innerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        innerPaint.setColor(Color.parseColor("#169BD5"));

        outRect = new Rect();
        innerRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (width == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {
            width = outRect.width() + getPaddingLeft() + getPaddingRight();
        }
        if (height == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else {
            height = innerRect.height() + getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(width, height);

        outRect.set(0, 0, width, height);
        innerRect.set(0, marginTopAndBottom, width, height - marginTopAndBottom);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(outRect, outPaint);
        canvas.drawRect(innerRect, innerPaint);
    }
}
