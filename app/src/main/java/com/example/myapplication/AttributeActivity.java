package com.example.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作品人:create By shaoDong on 2021/1/29 11: 16
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author 属性动画
 **/
public class AttributeActivity extends AppCompatActivity {

    public static void newInstance ( Activity activity ) {
        activity.startActivity ( new Intent ( activity, AttributeActivity.class ) );
    }

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_attribute_layout );

        View view = findViewById ( R.id.v );

        TextView tvTranslationX = findViewById ( R.id.tv_translationX );
        tvTranslationX.setOnClickListener ( v -> setTranslationX ( view ) );

        TextView tvPath = findViewById ( R.id.tv_path );
        tvPath.setOnClickListener ( v -> drawPathAnim ( view ) );
    }

    private void setTranslationX ( View view ) {
        // 从左往右移动200F
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat ( view, "translationX",
                - ( view.getWidth ( ) + view.getLeft ( ) ),
                200F );
        objectAnimator.setDuration ( 2000 );
        objectAnimator.setInterpolator ( new AnticipateOvershootInterpolator ( ) );
        objectAnimator.start ( );
    }

    private void drawPathAnim ( View view ) {
        // 路径动画
        Path path = new Path ( );
        path.arcTo ( 50f, 800f, 1000f, 1000f, - 180f, 145f, true );
//        ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
//        animation.setDuration(5000);
//        animation.start ();
    }
}
