package com.github.yangkangli.sample;

import com.github.yangkangli.x.mvvm.BaseApplication;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import okhttp3.OkHttpClient;

public class LocalApplication extends BaseApplication {
    @Override
    protected OkHttpClient initOKHttpClient() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);


        AutoSizeConfig.getInstance().setCustomFragment(true);
        AutoSize.initCompatMultiProcess(this);
    }
}
