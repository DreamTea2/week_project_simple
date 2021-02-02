package com.example.myapplication.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * 作品人:create By shaoDong on 2021/2/2 15: 40
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author
 **/
public class MotionToolbar extends MotionLayout implements AppBarLayout.OnOffsetChangedListener {


    private float mProgress;

    public MotionToolbar ( @NonNull Context context,AttributeSet attributeSet ) {
        super ( context,attributeSet );
    }
    @Override
    public void onOffsetChanged ( AppBarLayout appBarLayout, int verticalOffset ) {
        mProgress = ( float ) ( ( - verticalOffset ) / appBarLayout.getTotalScrollRange ( ) );
        setProgress ( mProgress);
    }

    @Override
    protected void onAttachedToWindow ( ) {
        super.onAttachedToWindow ( );
        ViewParent parent = getParent ( );
        if ( parent instanceof AppBarLayout ) {
            ( ( AppBarLayout ) parent ).addOnOffsetChangedListener ( this );
        }
    }
}
