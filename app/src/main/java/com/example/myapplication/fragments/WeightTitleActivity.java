package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

/**
 * Create By shaodong on 2021/7/20 11:23
 */
public class WeightTitleActivity extends AppCompatActivity {
    private static final String TAG = "WeightTitleActivity";

    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity,WeightTitleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
    }
}
