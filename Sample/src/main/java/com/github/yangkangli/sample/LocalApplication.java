package com.github.yangkangli.sample;

import com.github.yangkangli.x.mvvm.BaseApplication;

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
    }
}
