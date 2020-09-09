package com.github.yangkangli.x.mvvm.utils;

import android.app.Application;

public class ContextUtils {

    /**
     * Application
     */
    private static Application sApplication;

    /**
     * 初始化Application对象
     *
     * @param application
     */
    public static void init(Application application) {
        ContextUtils.sApplication = application;
    }

    /**
     * 获得Application对象
     *
     * @return
     */
    public static Application getApplication() {
        if (ContextUtils.sApplication != null) {
            return ContextUtils.sApplication;
        }
        throw new NullPointerException("should be initialized in application");
    }
}
