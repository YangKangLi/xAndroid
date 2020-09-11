package com.github.yangkangli.sample.ui.guide;

import android.app.Application;

import androidx.annotation.NonNull;

import com.github.yangkangli.x.mvvm.BaseViewModel;

public class GuideViewModel extends BaseViewModel<IGuideView> {

    /**
     * 构造方法
     *
     * @param application
     */
    public GuideViewModel(@NonNull Application application) {
        super(application);
    }
}
