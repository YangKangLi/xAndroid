package com.github.yangkangli.x.mvvm;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;

import okhttp3.OkHttpClient;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient httpClient = initOKHttpClient();
        if (httpClient != null) {
            AndroidNetworking.initialize(getApplicationContext(), initOKHttpClient());
        } else {
            AndroidNetworking.initialize(getApplicationContext());
        }
    }

    public static synchronized void setApplication(Application application) {
        ContextUtils.init(application);
    }

    /**
     * BaseApplication中使用了AndroidNetworking库，本方法用于在初始化AndroidNetworking库时，传递OkHttpClient参数
     *
     * @return
     */
    protected abstract OkHttpClient initOKHttpClient();
}
