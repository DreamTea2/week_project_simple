package com.example.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作品人:create By shaoDong on 2021/1/29 15: 08
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author 启动页的添加
 **/
public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG = "SplashScreenActivity";

    private final int SPLASH_SCREEN = 5000;

    Animation topAnim, bottomAnim;
    ImageView logoIv;
    TextView logo, sogospan;

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        getWindow ( ).setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_screen_layout );

        topAnim = AnimationUtils.loadAnimation ( this, R.anim.top_animation );
        bottomAnim = AnimationUtils.loadAnimation ( this, R.anim.bottom_animation );

        logoIv = findViewById ( R.id.logo );
        logo = findViewById ( R.id.textView2 );
        sogospan = findViewById ( R.id.textView3 );

        logoIv.setAnimation ( topAnim );
        logo.setAnimation ( bottomAnim );
        sogospan.setAnimation ( bottomAnim );

        new Handler ( ).postDelayed ( ( ) -> {
            Intent intent = new Intent ( SplashScreenActivity.this, Login.class );
            Pair[] pair = new Pair[ 2 ];
            pair[ 0 ] = new Pair< View, String > ( logoIv, "login_image" );
            pair[ 1 ] = new Pair< View, String > ( logo, "login_text" );
            ActivityOptions activityOptions =
                    ActivityOptions.makeSceneTransitionAnimation ( SplashScreenActivity.this,
                            pair );
            startActivity ( intent, activityOptions.toBundle ( ) );
            finish ( );
        }, SPLASH_SCREEN );
    }
}
