package com.example.myapplication.fragments.child;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.utils.EventBusUtils;

import me.yokeyword.fragmentation.SupportFragment;

public class TestDetailIm extends SupportFragment {

    private static final String TAG = "TestDetailIm";

    public static TestDetailIm newInstance() {

        Bundle args = new Bundle();

        TestDetailIm fragment = new TestDetailIm();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_im, container, false);
        TextView tvTest = view.findViewById(R.id.tv_test);
        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: 已经开启关闭程序了");
                EventBusUtils.post("1213");
//                pop();
            }
        });
        return view;
    }

}
