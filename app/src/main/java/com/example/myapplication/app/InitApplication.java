package com.example.myapplication.app;

import android.app.Application;

import com.example.myapplication.base.Constance;
import com.tencent.bugly.crashreport.CrashReport;

import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

public class InitApplication extends Application {

    private static final String TAG = "InitApplication";


    @Override
    public void onCreate() {
        super.onCreate();

        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();

        // Bugly 测试阶段更改为false 发布阶段改为true
        CrashReport.initCrashReport(getApplicationContext());
    }
}
