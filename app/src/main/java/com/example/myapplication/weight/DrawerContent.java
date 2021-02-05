package com.example.myapplication.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * 作品人:create By shaoDong on 2021/2/3 09: 34
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author 抽屉内容
 **/
public class DrawerContent extends MotionLayout implements DrawerLayout.DrawerListener {

    private float mProgress;

    public DrawerContent ( @NonNull Context context, @Nullable AttributeSet attrs ) {
        super ( context, attrs );
    }

    @Override
    public void onDrawerSlide ( @NonNull View drawerView, float slideOffset ) {
        mProgress = slideOffset;
        setProgress ( mProgress );
    }

    @Override
    public void onDrawerOpened ( @NonNull View drawerView ) {
    }

    @Override
    public void onDrawerClosed ( @NonNull View drawerView ) {
    }

    @Override
    public void onDrawerStateChanged ( int newState ) {
    }

    @Override
    protected void onAttachedToWindow ( ) {
        super.onAttachedToWindow ( );
        ViewParent parent = getParent ( );
        DrawerLayout drawerLayout = ( DrawerLayout ) parent;
        drawerLayout.addDrawerListener ( this );
    }
}
