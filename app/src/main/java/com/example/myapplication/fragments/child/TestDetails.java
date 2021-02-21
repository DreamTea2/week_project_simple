package com.example.myapplication.fragments.child;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.utils.EventBusUtils;

import me.yokeyword.fragmentation.SupportFragment;

public class TestDetails extends SupportFragment {
    private static final String TAG = "TestDetails";

    public static TestDetails newInstance() {

        Bundle args = new Bundle();

        TestDetails fragment = new TestDetails();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_layout, container, false);
        return view;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(TestDetailIm.class) == null) {
            loadRootFragment(R.id.fm_detail,TestDetailIm.newInstance(),true,false);
        }
    }

}
