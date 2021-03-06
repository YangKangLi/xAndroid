package com.github.yangkangli.x.mvvm.utils;

import android.app.Application;

import com.github.yangkangli.x.mvvm.XApplication;

public class ContextUtils {

    /**
     * Application
     */
    private static XApplication sApplication;

    private ContextUtils() {
        throw new UnsupportedOperationException("u can't initial here");
    }

    /**
     * 初始化Application对象
     *
     * @param application
     */
    public static void init(XApplication application) {
        ContextUtils.sApplication = application;
    }

    /**
     * 获得Application对象
     *
     * @return
     */
    public static XApplication getApplication() {
        if (ContextUtils.sApplication != null) {
            return ContextUtils.sApplication;
        }
        throw new NullPointerException("should be initialized in application");
    }
}
