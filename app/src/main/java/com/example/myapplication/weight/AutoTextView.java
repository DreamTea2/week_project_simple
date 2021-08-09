package com.example.myapplication.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.myapplication.R;
import com.example.myapplication.data.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By shaodong on 2021/7/29 14:10
 * 自定义TextView 实现自动隐藏和显示
 */
public class AutoTextView extends AppCompatTextView {

    private static final String TAG = "AutoTextView";
    private int mReplaceColor;
    private int mReplaceStartIndex = -1 ;
    private String mText;

    public AutoTextView(@NonNull Context context) {
        this(context, null);
    }

    public AutoTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoTextView);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case 0:
                    mText = a.getString(attr);
                    break;
                case 1:
                    mReplaceColor = a.getColor(attr, Color.BLACK);
                    break;
                default:
                    mReplaceStartIndex = a.getInteger(attr, 0);
                    break;
            }
        }
        a.recycle();

        if (!TextUtils.isEmpty(mText)) {
            setText(mText, BufferType.NORMAL);
        } else {
            Log.e(TAG, "文本不可以为null");
        }
        /* 显示一行 */
//        setSingleLine(true);
        /* 显示不全直接省略号 */
        setEllipsize(TextUtils.TruncateAt.END);
        /* 去除默认间隔 */
        setIncludeFontPadding(false);
        /* 设置部分字体颜色*/
        if (mReplaceStartIndex != -1) {
            setTextSpecFileStyle();
        }
    }

    private void setTextSpecFileStyle() {
        if (!TextUtils.isEmpty(mText)) {
            SpannableStringBuilder styledText = new SpannableStringBuilder(mText);
            int length = mText.length();
            for (int i = 0; i < length; i++) {
                if (i >= mReplaceStartIndex && mReplaceStartIndex < mText.length()) {
                    styledText.setSpan(
                            new ForegroundColorSpan(mReplaceColor),
                            mReplaceStartIndex,
                            length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            setText(styledText, BufferType.NORMAL);
            Log.d(TAG, "已经修改颜色后的文案:" + getText());
        } else {
            Log.e(TAG, "文本不可以为null");
        }
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
