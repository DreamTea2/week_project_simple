package com.example.myapplication.weight;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Create By shaodong on 2021/7/29 14:10
 * 自定义TextView 实现自动隐藏和显示
 */
public class AutoTextView extends AppCompatTextView {

    private static final String TAG = "AutoTextView";

    public AutoTextView(@NonNull Context context) {
        this(context, null);
    }

    public AutoTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (TextUtils.isEmpty(text) || text.length() == 0) {
            // 没有数据就隐藏
            setVisibility(View.GONE);
        } else {
            // 如果有数据就显示
            setVisibility(View.VISIBLE);
        }
    }
}
