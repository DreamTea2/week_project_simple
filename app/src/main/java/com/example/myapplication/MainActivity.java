package com.example.myapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.fragments.Fragment1;
import com.example.myapplication.fragments.Fragment2;
import com.example.myapplication.fragments.Fragment3;
import com.example.myapplication.fragments.Fragment4;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Fragment mCurreFragment = new Fragment ( );
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        WindowManager.LayoutParams layoutParams = getWindow ( ).getAttributes ( );
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ) {
            // 设置圆角屏偏移量
            layoutParams.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER;
            getWindow ( ).setAttributes ( layoutParams );
        }
        setContentView ( R.layout.activity_main );

        Toolbar toolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        Fragment mFragment1 = Fragment1.newInstance ( );
        Fragment mFragment2 = Fragment2.newInstance ( );
        Fragment mFragment3 = Fragment3.newInstance ( );
        Fragment mFragment4 = Fragment4.newInstance ( );

        BottomNavigationView navigationView = findViewById ( R.id.bottom_navigation );
        // 还原图标颜色
        navigationView.setItemIconTintList ( null );
        navigationView.setOnNavigationItemSelectedListener ( item -> {
            switch ( item.getItemId ( ) ) {
                case R.id.item1:
                    showHideFragment ( mFragment1, mFragment1.getClass ( ).getName ( ), true );
                    return true;
                case R.id.item2:
                    showHideFragment ( mFragment2, mFragment2.getClass ( ).getName ( ), true );
                    return true;
                case R.id.item3:
                    showHideFragment ( mFragment3, mFragment3.getClass ( ).getName ( ), true );
                    return true;
                case R.id.item4:
                    showHideFragment ( mFragment4, mFragment4.getClass ( ).getName ( ), true );
                    return true;
                default:
            }
            return false;
        } );
        BadgeDrawable badge = navigationView.getOrCreateBadge ( R.id.item1 );
        badge.setNumber ( 1000 );

        showHideFragment ( mFragment1, mFragment1.getClass ( ).getName ( ), false );

        drawerLayout = findViewById ( R.id.drawer_layout );
        NavigationView nv = findViewById ( R.id.nav_view );
        ViewGroup.LayoutParams params = nv.getLayoutParams ( );
        params.width = getResources ( ).getDisplayMetrics ( ).widthPixels / 2;
        nv.setLayoutParams ( params );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer );
        drawerLayout.setDrawerListener ( toggle );
        toggle.syncState ( );

        nv.setNavigationItemSelectedListener ( item -> {
            int itemId = item.getItemId ( );
            Toast.makeText ( MainActivity.this, item.getTitle ( ), Toast.LENGTH_SHORT ).show ( );
            drawerLayout.close ( );
            switch ( itemId ) {
                case R.id.drawer_1:
                    break;
                case R.id.drawer_2:
                    break;
                case R.id.drawer_3:
                    break;
                case R.id.drawer_4:
                    break;
                default:
            }
            return true;
        } );
    }

    private void showHideFragment ( Fragment fragment, String tag, boolean isOpenAnim ) {
        FragmentManager supportFragmentManager = getSupportFragmentManager ( );
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction ( );
        if ( isOpenAnim ) {
            fragmentTransaction.setCustomAnimations ( R.anim.fragment_enter, R.anim.fragment_exit );
        }
        // 获取所有的Fragment
        List< Fragment > fragments = supportFragmentManager.getFragments ( );
        boolean isHave = false;
        for ( int i = 0; i < fragments.size ( ); i++ ) {
            Fragment allFramgent = fragments.get ( i );
            // 检查是否拥有该标记
            String allTag = allFramgent.getTag ( );
            // 当前显示的Fragment
            String curreFragmentTag = fragment.getTag ( );
            if ( curreFragmentTag != null && allTag != null ) {
                if ( allTag.equals ( curreFragmentTag ) ) {
                    isHave = true;
                }
            }
        }
        if ( ! isHave ) {
            // 添加Fragment
            fragmentTransaction.add ( R.id.nav_host_fragment, fragment, tag );
            // 加入回退栈中
            fragmentTransaction.addToBackStack ( fragment.getClass ( ).getName ( ) );
            // 并且显示
            fragmentTransaction.show ( fragment );
            fragmentTransaction.hide ( mCurreFragment );
        } else {
            fragmentTransaction.hide ( mCurreFragment );
            fragmentTransaction.show ( fragment );
        }
        mCurreFragment = fragment;
        fragmentTransaction.commit ( );
    }


    private long mCurreTime = 0;
    private final long exitTime = 2000;

    @Override
    public void onBackPressed ( ) {
        goBackHome ( );
    }

    CountDownTimer countDownTimer = null;

    private void goBackHome ( ) {
        if ( mCurreTime < exitTime ) {
            mCurreTime = exitTime;
            Toast.makeText ( this, "请在按一次退出", Toast.LENGTH_SHORT ).show ( );
            if ( countDownTimer == null ) {
                countDownTimer = new CountDownTimer ( 2000, 1 ) {
                    @Override
                    public void onTick ( long millisUntilFinished ) {
                    }

                    @Override
                    public void onFinish ( ) {
                        mCurreTime = 0;
                        countDownTimer = null;
                    }
                };
                countDownTimer.start ( );
            }
        } else {
            System.exit ( 0 );
        }
    }

    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent event ) {
        if ( keyCode == KeyEvent.KEYCODE_BACK ) {
            // 侧滑栏的返回键处理
            if ( drawerLayout.isDrawerOpen ( GravityCompat.START ) ) {
                drawerLayout.closeDrawers ( );
                return false;
            }
        }
        return super.onKeyDown ( keyCode, event );
    }
}