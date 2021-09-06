package com.example.myapplication.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dialog.BaseDialog;
import com.example.myapplication.fragments.bean.Assort;
import com.example.myapplication.utils.ScreenUtils;
import com.example.myapplication.weight.BaseDialogFragment;
import com.example.myapplication.weight.SimpleRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By shaodong on 2021/8/10 13:43
 * 三级
 */
public class AssortDialog extends BaseDialogFragment implements SimpleRecyclerView.OnItemChooseClickListener {

    private static final String TAG = "AssortDialog";

    private SimpleRecyclerView parent;
    private SimpleRecyclerView child;
    private SimpleRecyclerView childNext;
    private List<Assort> data;
    /* 占据 */
    private LinearLayout view;
    private int transY;
    private int transX;

    public static AssortDialog newInstance(Bundle bundle) {
        AssortDialog assortDialog = new AssortDialog();
        assortDialog.setArguments(bundle);
        return assortDialog;
    }

    @Override
    public int layoutId() {
        return R.layout.assort_dialog;
    }

    @Override
    public void initComponent(View view) {
        Bundle bundle = getArguments();
        transY = bundle.getInt("transY");
        transX = bundle.getInt("transX");

        parent = view.findViewById(R.id.rv_parent);
        child = view.findViewById(R.id.rv_child);
        childNext = view.findViewById(R.id.rv_next);
        view = view.findViewById(R.id.empty_view);
        parent.setOnSortClickListener(this::onItemClickListener);
        child.setOnSortClickListener(this::onItemClickListener);
        childNext.setOnSortClickListener(this::onItemClickListener);

        /* 当前父容器的高度*/
        int screenHeight = ScreenUtils.getScreenHeight(getContext()) + ScreenUtils.getStatusHeight(getContext());

        int emptyHeight = screenHeight - transY - parent.getHeight();
        Log.d("SimpleRecyclerView", "高度:" + screenHeight);
        Log.d("SimpleRecyclerView", "剩余多少高度:" + emptyHeight);

        int i = ScreenUtils.px2dip(getContext(), emptyHeight);
        Log.d("SimpleRecyclerView", "转换后的高:" + i);
        /* 重新绘制占位View的高度*/
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = i;
        if (view != null) {
            view.setLayoutParams(layoutParams);
        }
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Assort father = new Assort();
            father.setSubjectName("爸爸" + i + "号");
            List<Assort> assortList = new ArrayList<>();
            for (int u = 0; u < 10; u++) {
                Assort assort = new Assort();
                assort.setSubjectName("儿子" + u + "号");
                assortList.add(assort);
                List<Assort> nextData = new ArrayList<>();
                for (int o = 0; o < 9; o++) {
                    Assort nextAssort = new Assort();
                    nextAssort.setSubjectName("孙子" + o + "号");
                    nextData.add(nextAssort);
                }
                assort.setChildSubjectList(nextData);
            }
            father.setChildSubjectList(assortList);
            data.add(father);
        }
        Log.d("SimpleRecyclerView", "开始渲染数据" + data.size());
        parent.update(data, SimpleRecyclerView.Type.PARENT);
        parent.show();


    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 1.0f), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Window dialogWindow = getDialog().getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
        Log.d("SimpleRecyclerView", "X 轴间距:" + transX);
        Log.d("SimpleRecyclerView", "Y 轴间距:" + transY);
        lp.x = transX;
        lp.y = transY;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.alpha = 1.0f;
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void unBindObject() {
    }

    @Override
    public void onItemClickListener(int position, Assort assort, SimpleRecyclerView.Type type) {
        switch (type) {
            case PARENT:
                child.update(getChildData(assort), SimpleRecyclerView.Type.CHILD);
                child.show();
                break;
            case CHILD:
                childNext.update(getChildData(assort), SimpleRecyclerView.Type.NEXT);
                childNext.show();
                break;
            case NEXT:
                Toast.makeText(getContext(), "大家好,我是孙子" + assort.getSubjectName(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private List<Assort> getChildData(Assort assort) {
        return assort.getChildSubjectList();
    }
}
