package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.fragments.child.DialogFragment;
import com.example.myapplication.weight.SeekLoading;
import com.example.myapplication.weight.StatusLayout;

/**
 * Create By shaodong on 2021/7/23 15:22
 */
public class CircleLoadingActivity extends AppCompatActivity {
    private static final String TAG = "CircleLoadingActivity";
    private SeekLoading myLoading;
    private StatusLayout mStatus;

    public static void newInstance(Activity activity) {
        Intent intent = new Intent(activity, CircleLoadingActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        myLoading = findViewById(R.id.loading);
        myLoading.setSeek(80);

        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "dialogFragment");

        mStatus = findViewById(R.id.status);
        mStatus.showEmpty();
        mStatus.setLottieAnim(R.raw.loading);
    }

}
