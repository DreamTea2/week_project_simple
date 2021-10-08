package com.example.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AlphaActivity;
import com.example.myapplication.AttributeActivity;
import com.example.myapplication.CardScaleActivity;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.base.BaseHolder;
import com.example.myapplication.base.ItemData;
import com.example.myapplication.base.onItemClickListener;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

/**
 * 作品人:create By shaoDong on 2021/1/27 09: 53
 * 邮箱：myu17635728243@163.com
 * note: 哪里没有朴素、善良和真理，哪里也就谈不上有伟大.
 * desc:
 *
 * @author
 **/
public class Fragment1 extends Fragment {

    public static Fragment1 newInstance() {
        Bundle args = new Bundle();
        Fragment1 fragment = new Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_layout, container, false);

        Button btn = view.findViewById(R.id.btn_test);
        btn.setOnClickListener(v -> CrashReport.testJavaCrash());

        List<ItemData> dataList = new ArrayList<>();
        ItemData data = new ItemData(0, "Loading anim", false);
        dataList.add(data);

        ItemData anim = new ItemData(1, "ItemAnim", false);
        dataList.add(anim);

        ItemData attribute = new ItemData(2, "Attribute animation（属性动画）", false);
        dataList.add(attribute);

        ItemData complement = new ItemData(3, "Complementary animation（补间动画）", false);
        dataList.add(complement);

        ItemData framAnim = new ItemData(4, "Frame animation（帧动画）", false);
        dataList.add(framAnim);

        MyAdapter adapter = new MyAdapter(dataList, getContext(), (itemData, position, v) -> {
            switch (itemData.getId()) {
                case 0:
                    // 渐变
                    AlphaActivity.newInstance(getActivity());
                    break;
                case 1:
                    // 卡片翻转动画
                    CardScaleActivity.newInstance(getActivity());
                    break;
                case 2:
                    // 属性动画
                    AttributeActivity.newInstance(getActivity());
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemViewCacheSize(Integer.MAX_VALUE);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));
        return view;


    }

    private class MyAdapter extends BaseAdapter<BaseHolder, ItemData> {

        public MyAdapter(List<ItemData> dataList, Context context) {
            super(dataList, context);
        }

        public MyAdapter(List<ItemData> dataList, Context context,
                         onItemClickListener<ItemData> clickListener) {
            super(dataList, context, clickListener);
        }

        @Override
        protected int layoutId(int viewType) {
            return R.layout.adapter_item_layout;
        }

        @Override
        protected BaseHolder getViewHolder(View itemView, int viewType, ViewGroup parent) {
            return new BaseHolder(itemView);
        }

        @Override
        protected void setValue(BaseHolder holder, ItemData data) {
            holder.setText(R.id.tv_text, data.getItemContent());
            holder.startScalAnim(R.id.iv_play);
        }
    }

}
