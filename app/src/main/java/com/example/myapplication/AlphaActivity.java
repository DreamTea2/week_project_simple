package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作品人:create By shaoDong on 2021/1/28 13: 55
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 * 减变动画实例
 *
 * @author
 **/
public class AlphaActivity extends AppCompatActivity {


    private View contentView;
    private View loadingView;

    public static void newInstance ( Activity activity ) {
        activity.startActivity ( new Intent ( activity, AlphaActivity.class ) );
    }

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_aplha_layout );
        contentView = findViewById ( R.id.content );
        loadingView = findViewById ( R.id.loading_spinner );

        contentView.setVisibility ( View.GONE );

        crossfade ( );
    }

    private void crossfade ( ) {

        contentView.setAlpha ( 0f );
        contentView.setVisibility ( View.VISIBLE );
        // 从0 到1
        contentView.animate ( )
                .alpha ( 1f )
                .setDuration ( 2000 )
                .setListener ( null );
        // 从1 到 0
        loadingView.animate ( )
                .alpha ( 0f )
                .setDuration ( 2000 )
                .setListener ( new AnimatorListenerAdapter ( ) {
                    @Override
                    public void onAnimationEnd ( Animator animation ) {
                        loadingView.setVisibility ( View.GONE );
                    }
                } );
    }
}
