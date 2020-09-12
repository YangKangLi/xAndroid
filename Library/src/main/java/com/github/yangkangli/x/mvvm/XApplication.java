package com.github.yangkangli.x.mvvm;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.androidnetworking.AndroidNetworking;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;

import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;
import okhttp3.OkHttpClient;

public abstract class XApplication extends Application {

    private static final boolean IS_ROUTER_DEBUG = true;

    // 美工设计图的宽度尺寸
    private static int sDesignWidth;
    private static int sDesignHeight;

    @Override
    public void onCreate() {
        super.onCreate();

        sDesignWidth = initDesignWidth();
        sDesignHeight = initDesignHeight();
        AutoSizeConfig.getInstance().setCustomFragment(true);
        AutoSize.initCompatMultiProcess(this);


        OkHttpClient httpClient = initOKHttpClient();
        if (httpClient != null) {
            AndroidNetworking.initialize(getApplicationContext(), initOKHttpClient());
        } else {
            AndroidNetworking.initialize(getApplicationContext());
        }

        // 这两行必须写在ARouter.init()之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }

    public static synchronized void setApplication(XApplication application) {
        ContextUtils.init(application);
    }

    protected abstract int initDesignWidth();

    protected abstract int initDesignHeight();

    public int getDesignWidth() {
        return sDesignWidth;
    }

    public int getDesignHeight() {
        return sDesignHeight;
    }

    /**
     * BaseApplication中使用了AndroidNetworking库，本方法用于在初始化AndroidNetworking库时，传递OkHttpClient参数
     *
     * @return
     */
    protected abstract OkHttpClient initOKHttpClient();
}
