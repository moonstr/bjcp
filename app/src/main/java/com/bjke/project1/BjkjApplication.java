package com.bjke.project1;

import android.app.Application;

import com.bjkj.library.greendao.DaoManager;
import com.bjkj.library.utils.SPUtils;
import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by liyou on 2018/3/29.
 */

public class BjkjApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initX5();
        SPUtils.init(this);
        DaoManager.getInstance().init(BjkjApplication.this);
    }

    private void initX5(){
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
            }

            @Override
            public void onCoreInitFinished() {
            }
        });
    }
}
