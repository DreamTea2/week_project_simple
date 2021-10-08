package com.example.myapplication.weight;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.utils.ScreenUtils;

/**
 * Create By shaodong on 2021/9/6 13:54
 */
public class SimpleDragBar extends View {

    private static final String TAG = "SimpleDragBar";
    // 背景
    private Paint mBackgroundPaint;
    // 实际进度
    private Paint mProgressPaint;
    // 圆形指示器画笔
    private Paint mCirclePaint;
    // 文字的画笔
    private Paint mTextPaint;
    // 底部分割
    private Paint mSpacePaint;
    // 矩形
    private Rect rect;
    // 进度条的最大宽度
    private float maxProgress;
    // 进度条当前的宽度
    private float currentProgress;
    // 当前View的宽度
    private int width;
    // 当前View的高度
    private int height;
    // 距离左边的内边距
    private int paddingLeft;
    // 距离右边的内边距
    private int paddingRight;
    // 圆点指示器的半径 默认10dp
    private int mCircleRadius;
    // 进度条高度 默认10dp
    private int progressHeight;
    // 进度条颜色
    private int progressColor;
    // 圆形指示器颜色
    private int circleColor;
    //  底部字体颜色
    private int textColor;
    private int spaceWidth, spaceHeight;
    // 数据集合
    private SparseArray<String> dataArray;

    private Context mContext;
    private String text;
    private int weightProgress;
    // 平均值
    private float mWeightLength;
    private boolean isDrag = true;

    public SimpleDragBar(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public SimpleDragBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public SimpleDragBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mContext = context;
        initParams();
        initPaint();
    }

    private void initParams() {
        // 初始化参数
        progressColor = ContextCompat.getColor(mContext, R.color.seek_drag);
        circleColor = ContextCompat.getColor(mContext, R.color.seek_white);
        textColor = Color.BLACK;
        mCircleRadius = ScreenUtils.dip2px(mContext, 10);
        progressHeight = ScreenUtils.dip2px(mContext, 10);
        dataArray = new SparseArray<>();
        dataArray.put(0, "1");
        dataArray.put(1, "2");
        dataArray.put(2, "3");
        dataArray.put(3, "4");
        dataArray.put(4, "5");
        dataArray.put(5, "6");
    }

    private void initPaint() {
        // 进度条背景画笔
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setStrokeCap(Paint.Cap.ROUND);
        mBackgroundPaint.setStrokeWidth(progressHeight);
        // 设置辐射背景
        Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#FFFFFF"),
                Color.parseColor("#E6E6E6"), Shader.TileMode.CLAMP);
        mBackgroundPaint.setShader(shader);

        // 设置进度画笔
        mProgressPaint = new Paint();
        mProgressPaint.setColor(progressColor);          // 进度条进度颜色
        mProgressPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setStrokeWidth(progressHeight);

        // 圆点指示器
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);         // 设置抗锯齿
        mCirclePaint.setColor(circleColor);      // 圆点指示器颜色
        mCirclePaint.setStyle(Paint.Style.FILL); // 填充

        // 底部文字
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(textColor);
        mTextPaint.setStrokeWidth(2);
        mTextPaint.setTextSize(35);
        // 底部分割
        mSpacePaint = new Paint();
        mSpacePaint.setAntiAlias(true);
        mSpacePaint.setColor(textColor);
        mSpacePaint.setStrokeWidth(1);

        rect = new Rect();
        text = "0123456789";
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        // 绘制底部文字
        spaceWidth = ScreenUtils.dip2px(mContext, 1);
        spaceHeight = ScreenUtils.dip2px(mContext, 10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 给了一个默认文字高度,因为刚开始没有绘制任何文字,测量高度无法测量
        float v = mTextPaint.measureText(text);
        int minHeight = (int) (mCircleRadius * 2 + (ScreenUtils.dip2px(mContext, 2) * 2)
                + rect.height() + spaceHeight + v / 2);
        int height = resolveSize(minHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
        // 距离左边的距离
        paddingLeft = getPaddingLeft();
        if (getPaddingLeft() < mCircleRadius) {
            paddingLeft = mCircleRadius;
        }
        // 距离右边的距离
        paddingRight = getPaddingRight();
        if (getPaddingRight() < mCircleRadius) {
            paddingRight = mCircleRadius;
        }
        // 如果当前进度小于左边距
        setCurrentProgress();
        // 最大进度长度等于View的宽度-(左边的内边距+右边的内边距)
        maxProgress = width - paddingLeft - paddingRight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mProgressPaint.setColor(progressColor);  // 进度条颜色
        mCirclePaint.setColor(circleColor);      // 指示器颜色
        // 绘制进度条背景
        canvas.drawLine(paddingLeft, height * 0.5f, width - paddingRight, height * 0.5f, mBackgroundPaint);
        // 绘制进度条进度
        // 从（左边距，View高度的一半）开始，到（现在的触摸到的进度宽度，View高度的一半）还将绘制灰色背景线段
        canvas.drawLine(paddingLeft, height * 0.5f, currentProgress, height * 0.5f, mProgressPaint);
        // 绘制圆点指示器
        canvas.drawCircle(currentProgress, getHeight() * 0.5f, mCircleRadius, mCirclePaint);

        weightProgress = (int) maxProgress / dataArray.size();

        for (int i = 0; i < dataArray.size() + 1; i++) {
            //文字度量
            Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
            //得到基线的位置
            float baselineY = height * 0.5f + mCircleRadius + spaceHeight + rect.height() + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
            if (i == 0) {
                text = "0";
                //获取文本的宽度
                float textWidth = mTextPaint.measureText(text);
                canvas.drawLine(paddingLeft, height * 0.5f + mCircleRadius + 2,
                        paddingLeft, height * 0.5f + mCircleRadius + spaceHeight, mSpacePaint);

                canvas.drawText(text, paddingLeft - textWidth / 2, baselineY, mTextPaint);
            } else {
                text = dataArray.get(i - 1);
                float textWidth = mTextPaint.measureText(text);
                canvas.drawLine(paddingLeft + i * weightProgress
                        , height * 0.5f + mCircleRadius + 2
                        , paddingLeft + i * weightProgress
                        , height * 0.5f + mCircleRadius + spaceHeight, mSpacePaint);
                //获取文本的宽度
                canvas.drawText(text, paddingLeft + i * weightProgress - textWidth / 2
                        , baselineY, mTextPaint);
            }
        }
    }

    float xTrans;
    float xDown;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                xTrans = event.getX();
                if (xDown == xTrans) isDrag = false;  else isDrag = true;
                break;
            case MotionEvent.ACTION_UP:
                // 获取当前触摸点，赋值给当前进度
                setMotionProgress(event);
                return true;
        }
        return true;
    }


    /**
     * 根据用户手势计算进度值
     *
     * @param event 用户手势操作事件
     */
    private void setMotionProgress(MotionEvent event) {
        if (!isDrag) {
            // 获取当前触摸点，赋值给当前进度
            currentProgress = (int) event.getX();
            // 获取手指当前抬起来的进度
            int size = dataArray.size();
            // 平均值
            mWeightLength = maxProgress / size;
            float num = currentProgress / mWeightLength;
            // 四舍五入
            int ceil = Math.round(num);
            currentProgress = mWeightLength * ceil + paddingLeft;
            // 如果当前进度小于左边距
            setCurrentProgress();
            invalidate();
        }
    }

    // 如果当前进度超出边界，将当前进度赋值为边界极值
    private void setCurrentProgress() {
        if (currentProgress < paddingLeft) {    // 如果当前进度小于左边距
            currentProgress = paddingLeft;
        } else if (currentProgress > width - paddingRight) {  // 如果当前进度大于 宽度 - 右边距
            currentProgress = width - paddingRight;
        }
    }

}
