package com.github.yangkangli.sample;

import com.github.yangkangli.x.mvvm.XApplication;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import okhttp3.OkHttpClient;

public class LocalApplication extends XApplication {
    @Override
    protected OkHttpClient initOKHttpClient() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
    }

    @Override
    protected int initDesignWidth() {
        return 375;
    }

    @Override
    protected int initDesignHeight() {
        return 667;
    }
}
