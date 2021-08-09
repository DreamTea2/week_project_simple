package com.example.myapplication.fragments.child;

import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.R;
import com.example.myapplication.weight.BaseDialogFragment;
import com.example.myapplication.weight.SeekLoading;

/**
 * Create By shaodong on 2021/7/26 10:14
 */
public class DialogFragment extends BaseDialogFragment {

    private static final String TAG = "DialogFragment";
    private SeekLoading mLoading;
    private LottieAnimationView mLottie;


    @Override
    public int layoutId() {
        return R.layout.dialog_layout;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void initComponent(View view) {
        mLoading = view.findViewById(R.id.loading);
        mLoading.setSeek(100);
        mLottie = view.findViewById(R.id.lt_gift);
        mLottie.playAnimation();
    }

    @Override
    public void initData() {

    }

    @Override
    public void unBindObject() {

    }
}
