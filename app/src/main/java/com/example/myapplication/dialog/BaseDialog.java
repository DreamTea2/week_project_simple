package com.example.myapplication.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.example.myapplication.R;
import com.example.myapplication.action.AnimAction;

/**
 * Create By shaodong on 2021/8/2 9:30
 * 说实话如果想真正的去了解Dialog 还是需要去看windowManager中 Window 的源码.
 */
public class BaseDialog extends AppCompatDialog implements LifecycleOwner
        , AnimAction {

    /* 生命周期监控 */
    private LifecycleRegistry mLifecycle = new LifecycleRegistry(this::getLifecycle);

    public BaseDialog(Context context) {
        this(context, R.style.BaseDialogStyle);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 返回内容布局
     *
     * @return
     */
    public View getContentView() {
        View viewById = findViewById(Window.ID_ANDROID_CONTENT);
        if (viewById instanceof ViewGroup
                && ((ViewGroup) viewById).getChildCount() == 1) {
            return ((ViewGroup) viewById).getChildAt(0);
        }
        return viewById;
    }

    /**
     * 设置宽度
     *
     * @param width 宽度
     */
    public void setWidth(int width) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = width;
            window.setAttributes(attributes);
        }
    }

    /**
     * 设置高度
     *
     * @param height 高度
     */
    public void setHeight(int height) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.height = height;
            window.setAttributes(attributes);
        }
    }

    /**
     * 设置X offset 偏移量
     *
     * @param offset
     */
    public void setXOffset(int offset) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.x = offset;
            window.setAttributes(params);
        }
    }

    /**
     * 设置 Y offset 偏移量
     *
     * @param offset
     */
    public void setYOffset(int offset) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.y = offset;
            window.setAttributes(params);
        }
    }


    /**
     * 获取当前Dialog展示的重心
     *
     * @return
     */
    public int gravity() {
        /* 获取当前Window */
        Window window = getWindow();
        if (window != null) {
            /* 获取window属性*/
            WindowManager.LayoutParams attributes = window.getAttributes();
            return attributes.gravity;
        }
        return Gravity.CENTER;
    }

    /**
     * 设置展示权重
     *
     * @param gravity Gravity.Top
     *                Gravity.Center
     *                Gravity.Bottom
     *                Gravity.Left
     *                Gravity.Right
     */
    public void setGravity(int gravity) {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(gravity);
        }
    }

    /**
     * 获取当前Dialog动画
     *
     * @return
     */
    public int getCurrentAnimation() {
        Window window = getWindow();
        if (window != null) {
            return window.getAttributes().windowAnimations;
        }
        return BaseDialog.ANIM_DEFAULT;
    }

    /**
     * 设置Dialog展出动画
     *
     * @param animation
     */
    public void setAnimation(@StyleRes int animation) {
        Window window = getWindow();
        if (window != null) {
            window.setWindowAnimations(animation);
        }
    }

    /**
     * 设置背景遮罩是否开启
     *
     * @param enable
     */
    public void setBackGroundDimEnable(boolean enable) {
        Window window = getWindow();
        if (window != null) {
            if (enable) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        }
    }

    /**
     * 设置背景透明度(开启遮罩的前提之下)
     *
     * @param dimAmount
     */
    public void setBackgroundDimAmount(@FloatRange(from = 0.0, to = 1.0) float dimAmount) {
        Window window = getWindow();
        if (window != null) {
            window.setDimAmount(dimAmount);
        }
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override
    public void show() {
        super.show();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override
    public void dismiss() {
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        super.dismiss();
    }
}
