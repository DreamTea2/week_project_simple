package com.example.myapplication.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.data.Tag;
import com.example.myapplication.fragments.child.TestChild;
import com.example.myapplication.utils.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;

public class Test1 extends SupportFragment {

    private static final String TAG = "Test1";

    public static Test1 newInstance(String text) {

        Bundle args = new Bundle();
        args.putString(Tag.TAG_TEXT, text);
        Test1 fragment = new Test1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_1, container, false);
        EventBusUtils.register(this);
        return view;

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(TestChild.class) == null) {
            loadRootFragment(R.id.fm_test_child, TestChild.newInstance());
        }
    }

    public interface onDisimissListener {
        void OnDismissListener();
    }

    public onDisimissListener disimissListener;

    public void onDismiss(onDisimissListener onDisimissListener) {
        this.disimissListener = onDisimissListener;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
     public void onEvent(Object object) {
        if (disimissListener!=null) {
            disimissListener.OnDismissListener();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtils.unregister(this);
    }
}
