package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.base.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作品人:create By shaoDong on 2021/1/27 09: 57
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 * 学习一些自定义View .
 *
 * @author
 **/
public class Fragment4 extends Fragment {

    private static final String TAG = "Fragment4";


    public static Fragment4 newInstance() {
        Bundle args = new Bundle();
        Fragment4 fragment = new Fragment4();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4_layout, container, false);
        RecyclerView rv = view.findViewById(R.id.rv_weight);

        LinearLayoutManager linear = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linear);

        WeightAdapter weightAdapter = new WeightAdapter(getData(), getContext(), (s, position, view1) -> {
            switch (s) {
                case "MyTitle":
                    WeightTitleActivity.newInstance(getActivity());
                    break;
                case "MyCircle":
                    CircleLoadingActivity.newInstance(getActivity());
                    break;
                case "MyRecyclerView":
                    MyRecyclerActivity.startActivity(getActivity());
                    break;
                case "SelectDialog" :
                    SelectActivity.startActivity(getActivity()) ;
                    break;
            }
        });
        rv.setAdapter(weightAdapter);
        return view;
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("MyTitle");
        data.add("MyCircle");
        data.add("MyRecyclerView");
        data.add("SelectDialog");
        return data;
    }
}
