package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

/**
 * Create By shaodong on 2021/8/5 14:12
 */
public class SelectActivity extends AppCompatActivity {
    private static final String TAG = "SelectActivity";

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, SelectActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button button = findViewById(R.id.btn_show);
        button.setOnClickListener(v -> {
            SelectDialog selectDialog = new SelectDialog();
            selectDialog.show(getSupportFragmentManager(),"showDialog");
        });
    }
}
