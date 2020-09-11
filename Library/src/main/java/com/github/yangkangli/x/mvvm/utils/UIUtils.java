package com.github.yangkangli.x.mvvm.utils;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class UIUtils {

    public static final int WIDTH_DESIGNED_WIDTH_DP = 375;
    public static final int WIDTH_DESIGNED_HEIGHT_DP = 667;

    /**
     * 设置DrawableTop的尺寸
     *
     * @param textView
     * @param width    单位为db
     * @param height   单位为db
     */
    public static void setDrawableTopSize(TextView textView, int width, int height) {
        Drawable[] drawables = textView.getCompoundDrawables();
        try {

            Drawable drawable = drawables[1];
            drawable.setBounds(0, 0,
                    (int) (Resources.getSystem().getDisplayMetrics().density * width),
                    (int) (Resources.getSystem().getDisplayMetrics().density * height));
            textView.setCompoundDrawables(null, drawable, null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dp);
    }

    /**
     * 获得屏幕方法
     *
     * @return
     */
    public static int getScreenOrientation() {
        return ContextUtils.getApplication().getResources().getConfiguration().orientation;
    }

    /**
     * 是否横屏
     *
     * @return
     */
    public static boolean isScreenLandscape() {
        return Configuration.ORIENTATION_LANDSCAPE == getScreenOrientation();
    }

    public static int getScreenWidth() {
        DisplayMetrics dm = ContextUtils.getApplication().getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        return w_screen;
    }

    public static int getScreenHeight() {
        DisplayMetrics dm = ContextUtils.getApplication().getResources().getDisplayMetrics();
        int h_screen = dm.heightPixels;
        return h_screen;
    }
}
