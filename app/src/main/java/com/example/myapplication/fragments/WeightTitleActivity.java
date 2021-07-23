package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.weight.WeightTitle;

/**
 * Create By shaodong on 2021/7/20 11:23
 */
public class WeightTitleActivity extends AppCompatActivity {
    private static final String TAG = "WeightTitleActivity";

    private WeightTitle mTitle;

    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity, WeightTitleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        mTitle = findViewById(R.id.weight_title);
        mTitle.setOnBackListener(new WeightTitle.onClickBackListener() {
            @Override
            public void onBackListener() {
                finish();
            }
        });
        mTitle.setOnClickListener(new WeightTitle.onClickListener() {
            @Override
            public void onClickTitleListener() {
                Toast.makeText(getApplicationContext(), "这里是主题公园", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickRightTitleListener() {
                Toast.makeText(getApplicationContext(), "这里是更多的小朋友", Toast.LENGTH_SHORT).show();
            }
        });
//        mTitle.hideBack(true);
//        mTitle.hideLayer(true);
    }
}
