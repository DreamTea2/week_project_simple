package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * 作品人:create By shaoDong on 2021/2/3 09: 28
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author MotionLayout 结合 DrawbleLayout
 **/
public class MotionDrawableActivity extends AppCompatActivity {

    private static final String TAG = "MotionDrawableActivity";

    public static void newInstance ( Activity activity ) {
        activity.startActivity ( new Intent ( activity, MotionDrawableActivity.class ) );
    }

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_with_drawable );

        DrawerLayout drawerLayout = findViewById ( R.id.motionLayout );

        drawerLayout.addDrawerListener ( new DrawerLayout.DrawerListener ( ) {
            @Override
            public void onDrawerSlide ( @NonNull View drawerView, float slideOffset ) {
                View childAt = drawerLayout.getChildAt ( 0 );
                View menu = drawerView;
                float offX = 1 - slideOffset;
                // 这里的逻辑， 1 - slideOffSet 等于剩余空间 ，因为menu 完全展开为1
                // 那么1 -offX 即menu已经展开了多少,再用menu的测量宽度 * 百分比 （1-offx）的展开进度 = 偏移量
                childAt.setTranslationX ( menu.getMeasuredWidth ( ) * ( 1 - offX ) );
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
        } );
    }
}
