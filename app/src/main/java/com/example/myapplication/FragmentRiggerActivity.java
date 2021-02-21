package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.StrongBoxUnavailableException;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.fragments.Test1;
import com.example.myapplication.fragments.Test2;
import com.example.myapplication.fragments.Test3;
import com.example.myapplication.fragments.Test4;
import com.example.myapplication.fragments.child.TestChild;
import com.example.myapplication.fragments.child.TestDetails;
import com.example.myapplication.utils.EventBusUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class FragmentRiggerActivity extends SupportActivity {

    private static final String TAG = "FragmentationActivity";

    SupportFragment[] mFragments = new SupportFragment[4];

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    int mSelectPosition = 0;
    int mOldSelectPosition = 0;
    private SupportFragment currentFragment;

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, FragmentRiggerActivity.class));
    }

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        navigationView = findViewById(R.id.nav_bottom);

        SupportFragment firstFragment = findFragment(Test1.class);

        currentFragment = Test1.newInstance("Test_Fragmentation_1");

        if (firstFragment == null) {
            mFragments[FIRST] = Test1.newInstance("Test_Fragmentation_1");
            mFragments[SECOND] = Test2.newInstance();
            mFragments[THIRD] = Test3.newInstance();
            mFragments[FOURTH] = Test4.newInstance();

            loadMultipleRootFragment(R.id.fm_content, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);

        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(Test2.class);
            mFragments[THIRD] = findFragment(Test3.class);
            mFragments[FOURTH] = findFragment(Test4.class);
        }


        Test1 t = (Test1) mFragments[FIRST];
        t.onDismiss(new Test1.onDisimissListener() {
            @Override
            public void OnDismissListener() {
                Log.d(TAG, "c:currentFragment " + currentFragment + "--");

                final SupportFragment currentFragment = mFragments[mSelectPosition];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof Test1) {
                        currentFragment.popToChild(TestChild.class, false);
                    } else if (currentFragment instanceof Test2) {
                    } else if (currentFragment instanceof Test3) {
                    } else if (currentFragment instanceof Test4) {
                    }
                    return;
                }


//                if (currentFragment instanceof Test1) {
//                    currentFragment.popToChild(TestChild.class, false);
//                }
            }
        });

        navigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.item1:
                    mSelectPosition = FIRST;
                    break;
                case R.id.item2:
                    mSelectPosition = SECOND;
                    break;
                case R.id.item3:
                    mSelectPosition = THIRD;
                    break;
                case R.id.item4:
                    mSelectPosition = FOURTH;
                    break;
                default:
            }

            currentFragment = mFragments[mSelectPosition];
            showHideFragment(currentFragment,mFragments[mOldSelectPosition]);
            mOldSelectPosition = mSelectPosition;
            return true;
        });


    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

}
