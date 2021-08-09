package com.example.myapplication.weight;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.utils.ScreenUtils;

/**
 * Create By shaodong on 2021/7/26 9:29
 * <p>
 * AppCompatDialogFragment
 * MediaRouteChooserDialogFragment
 * MediaRouteControllerDialogFragment
 * PreferenceDialogFragmentCompat
 * 是 DialogFragment的子类
 * <p>
 * 已知的间接子类
 * EditTextPreferenceDialogFragmentCompat
 * 、ListPreferenceDialogFragmentCompat
 * 、MultiSelectListPreferenceDialogFragmentCompat
 * <p>
 * Style 整数
 * <p>
 * STYLE_NORMAL  :没有任何样式,正常的对话框
 * STYLE_NO_FRAME : 没有任何框架, 直接交给onCreateView 操作整个视图
 * STYLE_NO_INPUT : 会禁止所有的对话框输入
 * STYLE_NO_TITLE : 不包含标题区域
 * setStyle (STYLE_NO_FRAME)
 */
public abstract class BaseDialogFragment extends DialogFragment {

    private static final String TAG = "BaseDialog";

    private boolean isLazy = false;

    public BaseDialogFragment(boolean isLazy) {
        this.isLazy = isLazy;
    }

    public BaseDialogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setStyle(STYLE_NO_FRAME, R.style.BaseDialogTheme);
//        setStyle(DialogFragment.STYLE_NO_TITLE,  R.style.BaseDialogTheme);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        /* Bundle?：Fragment 上次保存的实例状态，如果这是一个新创建的 Fragment，则为 null*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.NO_GRAVITY;
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.alpha = 1.0f;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        /*  去除窗体颜色 */
        window.setDimAmount(0);
        return inflater.inflate(layoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isLazy) {
            initComponent(view);
            initData();
            isLazy = true;
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    /**
     * 判断是否可以点击取消
     *
     * @return false  true
     */
    @Override
    public boolean isCancelable() {
        return false;
    }


    //    @Nullable
    @Override
    public Dialog getDialog() {
        return super.getDialog();
    }
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        unBindObject();
    }

    /**
     * 返回用于膨胀此 Fragment 的视图的 LayoutInflater。 如果未附加 Fragment，则默认实现将引发异常,返回onCreateView 的LayoutInflater
     * 如果在 onCreateDialog(Bundle) 中调用它，将返回来自 Fragment#onGetLayoutInflater(Bundle) 的布局充气器，没有对话框主题。
     */
    /* 该布局*/
    public abstract int layoutId();

    /* 控件*/
    public abstract void initComponent(View view);

    /* 数据*/
    public abstract void initData();

    /* 解绑*/
    public abstract void unBindObject();
}
