package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作品人:create By shaoDong on 2021/2/1 14: 28
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author MotionLayout的相关使用
 **/
public class MotionActivity extends AppCompatActivity {

    public static void newInstance ( Activity activity, int position ) {
        Bundle bundle = new Bundle ( );
        bundle.putInt ( "position", position );
        activity.startActivity ( new Intent ( activity, MotionActivity.class ).putExtras ( bundle ) );
    }

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        Bundle extras = getIntent ( ).getExtras ( );
        int position = extras.getInt ( "position" );
        switch ( position ) {
            case 0:
                setContentView ( R.layout.activity_motion );
                break;
            case 1:
                setContentView ( R.layout.activity_motion_1 );
                break;
            case 2:
                setContentView ( R.layout.activity_motion_2 );
                break;
            case 3:
                
                break;
            default:
        }
    }
}
