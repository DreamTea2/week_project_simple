package com.example.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

/**
 * 作品人:create By shaoDong on 2021/2/1 10: 06
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author 登录页面
 **/
public class Login extends AppCompatActivity {

    Button btnSignUp;
    TextView tvRegister;
    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        btnSignUp = findViewById ( R.id.btn_sign );
        tvRegister = findViewById ( R.id.tv_register );

        btnSignUp.setOnClickListener ( v ->
                MainActivity.newInstance ( Login.this ) );

        tvRegister.setOnClickListener ( v -> {
            Intent intent = new Intent (Login.this,Register.class );
            Pair[] pairs = new Pair[ 7 ];
            pairs[ 0 ] = new Pair< View, String > ( tvRegister, "register_name" );
            pairs[ 1 ] = new Pair< View, String > ( tvRegister, "register_pwd" );
            pairs[ 2 ] = new Pair< View, String > ( tvRegister, "register_next" );
            pairs[ 3 ] = new Pair< View, String > ( tvRegister, "register_family" );
            pairs[ 4 ] = new Pair< View, String > ( tvRegister, "register_email" );
            pairs[ 5 ] = new Pair< View, String > ( tvRegister, "register_tel" );
            pairs[ 6 ] = new Pair< View, String > ( tvRegister, "register_btn" );

            ActivityOptions activityOptions =
                    ActivityOptions.makeSceneTransitionAnimation ( Login.this,
                            pairs );
            startActivity ( intent, activityOptions.toBundle ( ) );
        } );

    }
}
