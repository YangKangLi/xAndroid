package com.github.yangkangli.sample.ui;

import android.app.Application;

import androidx.annotation.NonNull;

import com.github.yangkangli.x.mvvm.BaseViewModel;

public class MainViewModel extends BaseViewModel<IMainView> {


    /**
     * 构造方法
     *
     * @param application
     */
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
