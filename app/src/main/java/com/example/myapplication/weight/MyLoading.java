package com.example.myapplication.weight;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * Create By shaodong on 2021/7/23 13:45
 * 加载进度的Loading
 * step 1 : 建立外环半径, 绘制外环,为青色
 * step 2 : 内环半径为 (外环radius + 外环直径  ) /2
 * step 3 : 绘制百分比文字
 * step 4 : 添加文字
 */
public class MyLoading extends View {
    private static final String TAG = "MyLoading";
    /* 外圈颜色*/
    private int mOutColor;
    /* 内圈颜色*/
    private int mInnerColor;
    /* 外圈画笔*/
    private Paint mOutPaint;
    /* 内圈画笔*/
    private Paint mInnerPaint;
    /* 文字画笔*/
    private Paint mTextPaint;
    /* 文字区域 */
    private Rect rect;
    /* 文字大小*/
    private int mTextSize;
    /* 文字内容*/
    private String text = "";
    /*外圈圆的半径 */
    private int radius = 100;
    /* 总角度*/
    private float ANGLE = 360.0f;
    /* 画弧*/
    private RectF rectF;
    /*  动画集合*/
    private ValueAnimator valueAnimator;
    /* 当前进度*/
    private float mProgress;
    private int centerX;
    private int centerY;

    public MyLoading(Context context) {
        super(context, null);
    }

    public MyLoading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyLoading, defStyleAttr, 0);
        for (int i = 0; i < a.length(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyLoading_out_color:
                    mOutColor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MyLoading_inner_color:
                    mInnerColor = a.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.MyLoading_seek_size:
                    mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP
                            , 14
                            , getResources().getDisplayMetrics()
                    ));
                    break;
                default:
                    break;
            }
        }
        a.recycle();

        mOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutPaint.setColor(mOutColor);
        mOutPaint.setStrokeWidth(10);
        mOutPaint.setStyle(Paint.Style.STROKE);

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(5);
        mInnerPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        rect = new Rect();
        if (text != null && text.length() > 0) {
            mTextPaint.getTextBounds(text, 0, text.length(), rect);
        }
    }

    private int width, height;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureWeight(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        centerX = width / 2;
        centerY = height / 2;
        int leftF = centerX - radius;
        int topF = centerY - radius;
        rectF = new RectF(leftF, topF, centerX + radius, centerY + radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*  绘制外圆*/
        canvas.drawArc(rectF, 360, 360, false, mOutPaint);
        /* 绘制内圆*/
        canvas.drawArc(rectF, 360, mProgress, false, mInnerPaint);
        /* 绘制内圈文字*/
        drawTextPoint(canvas,centerX,centerY,text, mTextPaint);
    }

    public void setSeek(int currentSeek) {
        valueAnimator = ValueAnimator.ofInt(0, currentSeek);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(animation -> {
            int current = (int) animation.getAnimatedValue();
            Log.d(TAG, " current:" + current);
            mProgress = 360.0f * (current / 100.0f);
            Log.d(TAG, " mProgress:" + mProgress);
            text = current + "%";
            invalidate();
        });
        valueAnimator.start();
    }

    private void drawTextPoint(Canvas canvas, int centerX, int centerY, String text, Paint paint) {
        float textWidth = paint.measureText(text);
        /*文字度量*/
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        /*得到基线的位置*/
        float baselineY = centerY + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        /*绘制*/
        canvas.drawText(text, centerX - textWidth / 2, baselineY, paint);
    }

    private void measureWeight(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mTextPaint.setTextSize(mTextSize);
            rect = new Rect();
            if (text != null && text.length() > 0) {
                mTextPaint.getTextBounds(text, 0, text.length(), rect);
            }
            /* 圆的半径*/
            width = getMeasuredWidth() - getPaddingStart() - getPaddingEnd();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mTextPaint.setTextSize(mTextSize);
            rect = new Rect();
            if (text != null && text.length() > 0) {
                mTextPaint.getTextBounds(text, 0, text.length(), rect);
            }
            height = getMeasuredWidth() - getPaddingTop() - getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }
}
