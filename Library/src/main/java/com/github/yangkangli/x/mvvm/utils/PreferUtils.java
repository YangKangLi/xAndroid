package com.github.yangkangli.x.mvvm.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferUtils {

    private static SharedPreferences sp() {
        return PreferenceManager.getDefaultSharedPreferences(ContextUtils.getApplication());
    }

    // *********************************************************************************************
    //   boolean 类型
    // *********************************************************************************************

    public static boolean getBoolean(String key) {
        return sp().getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sp().getBoolean(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value) {
        sp().edit().putBoolean(key, value).apply();
    }

    // *********************************************************************************************
    //   String 类型
    // *********************************************************************************************

    public static String getString(String key) {
        return sp().getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        return sp().getString(key, defaultValue);
    }

    public static void setString(String key, String value) {
        sp().edit().putString(key, value).apply();
    }

    // *********************************************************************************************
    //   int 类型
    // *********************************************************************************************

    public static int getInt(String key) {
        return sp().getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return sp().getInt(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void setInt(String key, int value) {
        sp().edit().putInt(key, value).apply();
    }

    // *********************************************************************************************
    //   long 类型
    // *********************************************************************************************

    public static long getLong(String key) {
        return sp().getLong(key, 0);
    }

    public static long getLong(String key, long defaultValue) {
        return sp().getLong(key, defaultValue);
    }

    public static void setLong(String key, long value) {
        sp().edit().putLong(key, value).apply();
    }

    // *********************************************************************************************
    //   float 类型
    // *********************************************************************************************

    public static float getFloat(String key) {
        return sp().getFloat(key, 0);
    }

    public static float getFloat(String key, float defaultValue) {
        return sp().getFloat(key, defaultValue);
    }

    public static void setFloat(String key, float value) {
        sp().edit().putFloat(key, value).apply();
    }
}
