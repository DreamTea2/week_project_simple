package com.example.myapplication.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * Create By shaodong on 2021/7/20 10:28
 * 2021年7月21日11:11:05
 * 捡起自定义View的知识
 */
public class WeightTitle extends View {

    private static final String TAG = "MyTopTitle";
    /*文本的内容*/
    private String mTitleText;
    /*文本默认的颜色*/
    private int mTitleTextColor;
    /* 文本默认的大小*/
    private int mTitleTextSize;
    /*  标题的区域*/
    private Rect rect;
    /* 标题文字画笔*/
    private Paint mFontPaint;
    /* 角标 */
    private Drawable mArrowDb;
    /* 角标画笔*/
    private Paint mPaintDb;
    /* 角标区域*/
    private Rect mRectDb;
    private float defWidth;
    private int margin = 15;
    /* 阴影*/
    private Paint mLayerPaint;
    private int mShawLayerHeight = 15;
    private Rect layerRect;
    /*更多*/
    private Paint mMorePaint;
    /* 右侧图片和文字都复用这个*/
    private Rect mMoreRect;
    /* 是否显示返回键*/
    private boolean hide = false;
    /* 是否显示阴影*/
    private boolean hideLayer = false;
    /* 右侧文字*/
    private String mEndText;
    /* 右侧文字大小*/
    private int mEndTextSize;
    /*  右侧文字颜色*/
    private int mEndTextColor;
    /* 是否显示右侧文字 */
    private boolean hideEnd;

    public WeightTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeightTitle(Context context) {
        this(context, null);
        /* 如果在代码中动态使用调用这里*/
    }

    /**
     * 自定义自己的属性
     *
     * @param context      上下文
     * @param attrs        自定义属性文件
     * @param defStyleAttr 默认的风格
     */
    public WeightTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /* step 1 :  自定义相关属性 */
        /* 如果在Xml文件中使用调用改方法*/
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WeightTitle, defStyleAttr, 0);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.WeightTitle_weight_title:
                    mTitleText = a.getString(index);
                    break;
                case R.styleable.WeightTitle_weight_color:
                    mTitleTextColor = a.getColor(index, Color.BLACK);
                    break;
                case R.styleable.WeightTitle_weight_size:
                    mTitleTextSize = a.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()
                    ));
                    break;
                case R.styleable.WeightTitle_weight_arrow:
                    mArrowDb = a.getDrawable(index);
                    defWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
                    break;
                case R.styleable.WeightTitle_weight_end_text:
                    mEndText = a.getString(index);
                    break;
                case R.styleable.WeightTitle_weight_end_text_size:
                    mEndTextSize = a.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()
                    ));
                    break;
                case R.styleable.WeightTitle_weight_end_hide:
                    hideEnd = a.getBoolean(index, false);
                    break;
                case R.styleable.WeightTitle_weight_end_text_color:
                    mEndTextColor = a.getColor(index, Color.BLACK);
                    break;
            }
        }
        /*回收资源*/
        a.recycle();
        /* 文字相关的画笔*/
        mFontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFontPaint.setTextSize(mTitleTextSize);
        /* 测量文字区域*/
        rect = new Rect();
        mFontPaint.getTextBounds(mTitleText, 0, mTitleText.length(), rect);
        /* 角标工具Init*/
        mPaintDb = new Paint();
        layerRect = new Rect();
        /* 绘制阴影*/
        mLayerPaint = new Paint();
        mLayerPaint.setColor(Color.DKGRAY);
        mLayerPaint.setStrokeWidth(10);
        /*
         *radius  阴影大小   阴影 x轴  y轴
         */
        mLayerPaint.setShadowLayer(5, 0, 0, Color.DKGRAY);
        mLayerPaint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.INNER));
        /* 右侧更多*/
        mMorePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMorePaint.setTextSize(mEndTextSize);
        mMorePaint.setColor(mEndTextColor);
        mMoreRect = new Rect();
        mMorePaint.getTextBounds(mEndText, 0, mEndText.length(), mMoreRect);
    }

    /**
     * step 2 测量控件在父布局中占用的空间大小
     *
     * @param widthMeasureSpec  宽
     * @param heightMeasureSpec 高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*  模式, EXACTLY 表示已经给了固定的值  AT_MOST  一般是限制了最大值 如warp_parent */
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (modeWidth == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {
            mFontPaint.setTextSize(mTitleTextSize);
            mFontPaint.getTextBounds(mTitleText, 0, mTitleText.length(), rect);
            float rectWidth = rect.width();
            int desired = (int) (getPaddingLeft() + rectWidth + getPaddingRight());
            width = desired;
        }
        if (modeHeight == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else {
            mFontPaint.setTextSize(mTitleTextSize);
            mFontPaint.getTextBounds(mTitleText, 0, mTitleText.length(), rect);
            float rectHeight = rect.height();
            int desired = (int) (getPaddingTop() + rectHeight + getPaddingBottom());
            height = desired;
        }
        layerRect.set(0, 0, width, height - mShawLayerHeight);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mRectDb = new Rect(
                margin + getPaddingLeft()
                , (getPaddingTop() + getPaddingBottom()) / 2
                , (int) (defWidth + margin)
                , getHeight() - ((getPaddingTop() + getPaddingBottom()) / 2) - mShawLayerHeight
        );
    }

    /**
     * step 3 我们来根据自己的想法来绘制控件
     *
     * @param canvas 绘制需要的所有东西,相当于工具
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!hideLayer) {
            canvas.save();
            canvas.drawRect(layerRect, mLayerPaint);
            canvas.restore();
        }
        mFontPaint.setColor(Color.WHITE);
        /* 整个rect 矩形空间已经根据文字测量好了 to step 1 */
        canvas.drawRect(layerRect, mFontPaint);
        /* 给画笔重新上色,来绘制文字*/
        mFontPaint.setColor(mTitleTextColor);
        /* 开始绘制文字
         * mTitleText 内容
         * getWidth/2 整个View中心点  - rect.width /2 整个文字占用的空间中心点 = 等于文字开始起始位置 (x) 高度同理
         */
        canvas.drawText(
                mTitleText
                , getWidth() / 2 - rect.width() / 2
                , getHeight() / 2 + rect.height() / 2 - mShawLayerHeight, mFontPaint
        );

        if (!hideEnd) {
            Log.d("onDraw", "开始绘制右侧文字 width : " + getWidth() + "----moreRect :" + mMoreRect.width() / 2);
            /* 绘制右侧文字*/
            canvas.drawText(
                    mEndText
                    , (getWidth() - mMoreRect.width() - (margin + getPaddingRight()))
                    , getHeight() / 2 + mMoreRect.height() / 2 - mShawLayerHeight, mMorePaint
            );
        }
        /* 绘制范围，并添加Icon */
        if (!hide) {
            mArrowDb.setBounds(mRectDb);
            mArrowDb.draw(canvas);
        }
    }

    float dX;
    float dY;
    float startEnd;
    int endWidth;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = touchX;
                dY = touchY;
                break;
            case MotionEvent.ACTION_UP:
                int width = rect.width();
                int startX = (getWidth() / 2 - width / 2) + getPaddingLeft() + getPaddingRight();

                Log.d("TouchEvent", "dx:" + dX + "->width:" + width + "->startX:" + startX);
                if (dX > startX && dX - startX <= width) {
                    if (titClickListener != null) {
                        titClickListener.onClickTitleListener();
                    }
                }
                if (dX > margin && dX - margin <= mArrowDb.getBounds().width() + getPaddingLeft()) {
                    if (backListener != null) {
                        backListener.onBackListener();
                    }
                }
                endWidth = mMoreRect.width() + getPaddingRight() + margin;

                startEnd = getMeasuredWidth() - endWidth;
                if (dX >= startEnd) {
                    if (titClickListener != null) {
                        titClickListener.onClickRightTitleListener();
                    }
                }
                break;
            default:
        }
        return true;
    }

    /**
     * 是否隐藏返回键
     *
     * @param isHide
     */
    public void hideBack(boolean isHide) {
        this.hide = isHide;
        postInvalidate();
    }

    /**
     * 是否显示分割线
     *
     * @param isHide
     */
    public void hideLayer(boolean isHide) {
        this.hideLayer = isHide;
        postInvalidate();
    }

    public void setOnBackListener(onClickBackListener clickListener) {
        this.backListener = clickListener;
    }

    public void setOnClickListener(onClickListener listener) {
        this.titClickListener = listener;
    }

    public interface onClickBackListener {
        /* 返回键 */
        void onBackListener();
    }

    public onClickBackListener backListener;
    public onClickListener titClickListener;

    public interface onClickListener {
        /*标题*/
        void onClickTitleListener();

        /*右方的事件*/
        void onClickRightTitleListener();
    }
}
