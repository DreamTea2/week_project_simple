package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作品人:create By shaoDong on 2021/2/2 14: 33
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author MotionLayout 结合AppBar
 **/
public class MotionToolbarActivity extends AppCompatActivity {
    private static final String TAG = "MotionToolbarActivity";

    public static void startActivity ( Activity activity ) {
        activity.startActivity ( new Intent ( activity, MotionToolbarActivity.class ) );
    }

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_appbar );
    }
}
