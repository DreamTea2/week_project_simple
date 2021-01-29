package com.example.myapplication;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fragments.CardPositiveFragment;
import com.example.myapplication.fragments.CardReverceFragment;

/**
 * 作品人:create By shaoDong on 2021/1/28 15: 06
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 * 卡片旋转动画
 *
 * @author
 **/
public class CardScaleActivity extends AppCompatActivity {


    private boolean showingBack = false;

    public static void newInstance ( Activity activity ) {
        activity.startActivity ( new Intent ( activity, CardScaleActivity.class ) );
    }

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_card_layout );

        getSupportFragmentManager ( ).beginTransaction ( )
                .add ( R.id.fm_reverse, new CardPositiveFragment ( ) )
                .commit ( );

        TextView tvPositive = findViewById ( R.id.tv_positive );
        tvPositive.setOnClickListener ( v -> {
            showingBack = true;
            flipCard ( );
        } );
        TextView tvReverce = findViewById ( R.id.tv_reverse );
        tvReverce.setOnClickListener ( v -> {
            showingBack = false;
            flipCard ( );
        } );

        final View viewCat = findViewById ( R.id.iv_cat );
        // 给视图设置原型动画 通过半径开始扩散 从 0 到 1
        // TODO : 在使用 ViewAnimationUtils 如果不添加监听 LayoutChangeListener 会报错 不应该被添加视图
        viewCat.addOnLayoutChangeListener ( new View.OnLayoutChangeListener ( ) {
            @Override
            public void onLayoutChange ( View v, int left, int top, int right, int bottom,
                                         int oldLeft, int oldTop, int oldRight, int oldBottom ) {
                v.removeOnLayoutChangeListener ( this );
                if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
                    int cx = v.getWidth ( ) / 2;
                    int cy = v.getHeight ( ) / 2;
                    // 获取剪切视图的半径
                    float finalRadius = ( float ) Math.hypot ( cx, cy );
                    // 创建视图动画
                    Animator anim = ViewAnimationUtils.createCircularReveal ( v, cx, cy, 0f,
                            finalRadius );
                    v.setVisibility ( View.VISIBLE );
                    anim.setDuration ( 5000 );
                    anim.setInterpolator ( new LinearInterpolator ( ) );
                    anim.start ( );
                } else {
                    v.setVisibility ( View.GONE );
                }
            }
        } );
    }

    private void flipCard ( ) {
        if ( showingBack ) {
            getSupportFragmentManager ( ).beginTransaction ( )
                    .setCustomAnimations ( R.animator.card_filp_right_in,
                            R.animator.card_filp_right_out,
                            R.animator.card_filp_left_in,
                            R.animator.card_filp_left_out )
                    .replace ( R.id.fm_reverse, new CardPositiveFragment ( ) )
                    .addToBackStack ( null )
                    .commit ( );
        } else {
            getSupportFragmentManager ( ).beginTransaction ( )
                    .setCustomAnimations ( R.animator.card_filp_right_in,
                            R.animator.card_filp_right_out,
                            R.animator.card_filp_left_in,
                            R.animator.card_filp_left_out )
                    .replace ( R.id.fm_reverse, new CardReverceFragment ( ) )
                    .addToBackStack ( null )
                    .commit ( );
        }
    }

    @Override
    public void onBackPressed ( ) {
        finish ( );
    }
}
