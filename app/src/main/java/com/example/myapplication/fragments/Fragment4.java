package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

/**
 * 作品人:create By shaoDong on 2021/1/27 09: 57
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author
 **/
public class Fragment4 extends Fragment {

    public static Fragment4 newInstance ( ) {
        Bundle args = new Bundle ( );
        Fragment4 fragment = new Fragment4 ( );
        fragment.setArguments ( args );
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                               @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate ( R.layout.fragment_4_layout, container, false );
        return view;
    }
}
