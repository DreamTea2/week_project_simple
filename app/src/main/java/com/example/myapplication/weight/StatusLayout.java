package com.example.myapplication.weight;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Create By shaodong on 2021/7/29 13:54
 * <p>
 * android 状态布局
 */
public class StatusLayout extends FrameLayout {

    private static final String TAG = "StatusLayout";
    //状态布局容器
    private ViewGroup mParentLayout;
    //布局的图片
    private LottieAnimationView mLottieView;
    // 文字
    private AutoTextView mAutoTv;

    public StatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //可以点击
        setClickable(true);
        //获取焦点
        setFocusable(true);
        // 外部可以点击
        setFocusableInTouchMode(true);
    }

    /**
     * 初始化布局
     *
     * @implNote 如果父布局为null 就创建新的覆盖层
     */
    private void initLayout() {
        if (mParentLayout == null) {
            mParentLayout = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.status_layout, this, false);
        }
        addView(mParentLayout);
        mAutoTv = findViewById(R.id.tv_auto);
        mLottieView = findViewById(R.id.lt_gift);
    }

    /**
     * 展示空状态布局
     *
     * @apiNote isShowParent true 展示 false 显示
     */
    public void showEmpty() {
        if (mParentLayout == null) {
            initLayout();
        }
        if (!isShowParent()) {
            mParentLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏状态布局
     *
     * @apiNote 请在 showEmpty 执行,否则抛出来异常
     */
    public void disEmpty() {
        if (mParentLayout != null && isShowParent()) {
            mParentLayout.setVisibility(View.INVISIBLE);
        } else if (mParentLayout == null) {
            throw new NullPointerException("操作异常");
        }
    }


    /**
     * 设置图片样式
     * show 方法之后调用
     * 不然打死
     * @param res
     */
    public  void setDrawable(@DrawableRes int  res) {
        if (mLottieView != null  && res!=0 ) {
            mLottieView.setAnimation(0);
            mLottieView.playAnimation();
            mLottieView.setImageDrawable(ContextCompat.getDrawable(getContext(),res));
        } else {
            Log.e("StatusLayout", "Drawable is Not null");
        }
    }

    /**
     * 这是提示文字
     * show 方法之后调用
     * 不然打死
     *
     * @param hint
     */
    public void setHintText(String hint) {
        if (mAutoTv != null && !TextUtils.isEmpty(hint))
            mAutoTv.setText(hint);
    }

    public  void setLottieAnim(@RawRes int res) {
        if (mLottieView != null && res != 0) {
            /*  每次都要初始化资源, 否则就先以堆栈中缓存的为先*/
            mLottieView.setAnimation(0);
            mLottieView.setAnimation(res);
            mLottieView.playAnimation();
        } else {
            Log.e(TAG, "json animation path no null");
        }
    }

    /**
     * 是否展示了数据状态布局
     *
     * @return true | false
     */
    public boolean isShowParent() {
        return mParentLayout != null && mParentLayout.getVisibility() == View.VISIBLE;
    }
}
