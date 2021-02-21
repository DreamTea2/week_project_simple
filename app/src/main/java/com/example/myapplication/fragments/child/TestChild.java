package com.example.myapplication.fragments.child;

import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportFragment;

public class TestChild extends SupportFragment {

    private static final String TAG = "TestChild";

    public static TestChild newInstance() {

        Bundle args = new Bundle();

        TestChild fragment = new TestChild();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_layout, container, false);
        TextView textView = view.findViewById(R.id.text_child);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(TestDetailIm.newInstance());
            }
        });
        return view;
    }


}
