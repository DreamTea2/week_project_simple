package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MotionActivity;
import com.example.myapplication.MotionToolbarActivity;
import com.example.myapplication.R;

/**
 * 作品人:create By shaoDong on 2021/1/27 09: 56
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author
 **/
public class Fragment3 extends Fragment {

    public static Fragment3 newInstance ( ) {
        Bundle args = new Bundle ( );
        Fragment3 fragment = new Fragment3 ( );
        fragment.setArguments ( args );
        return fragment;
    }

    TextView tvMotion, tvMotion1, tvMotion3, tvMotion4, tvMotion5;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_3_layout, container, false );
        tvMotion = view.findViewById ( R.id.tv_motion );
        tvMotion.setOnClickListener ( v -> onTextViewClick ( 0 ) );
        tvMotion1 = view.findViewById ( R.id.tv_motion_1 );
        tvMotion1.setOnClickListener ( v -> onTextViewClick ( 1 ) );
        tvMotion3 = view.findViewById ( R.id.tv_motion_2 );
        tvMotion3.setOnClickListener ( v -> onTextViewClick ( 2 ) );
        tvMotion4 = view.findViewById ( R.id.tv_motion_3 );
        tvMotion4.setOnClickListener ( v -> onTextViewClick ( 3 ) );
        tvMotion5 = view.findViewById ( R.id.tv_motion_4 );
        tvMotion5.setOnClickListener ( v -> onMotionWithToolbar ( ) );
        return view;
    }

    public void onTextViewClick ( int position ) {
        MotionActivity.newInstance ( getActivity ( ), position );
    }

    public void onMotionWithToolbar ( ) {
        MotionToolbarActivity.startActivity ( getActivity ( ) );
    }
}
