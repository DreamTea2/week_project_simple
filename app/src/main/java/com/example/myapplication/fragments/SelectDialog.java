package com.example.myapplication.fragments;

import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.R;
import com.example.myapplication.weight.BaseDialogFragment;

/**
 * Create By shaodong on 2021/8/5 14:16
 */
public class SelectDialog extends BaseDialogFragment {

    @Override
    public int layoutId() {
        return R.layout.dialog_select;
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
        lp.x = 0;
        lp.y = 100;
        lp.width = lp.MATCH_PARENT;
        lp.height = 600;
        lp.alpha = 0.7f;
        dialogWindow.setAttributes(lp);

    }

    @Override
    public void initComponent(View view) {
    }

    @Override
    public void initData() {

    }

    @Override
    public void unBindObject() {

    }
}
