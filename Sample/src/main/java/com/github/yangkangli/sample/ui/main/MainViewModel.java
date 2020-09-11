package com.github.yangkangli.sample.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;

import com.github.yangkangli.x.mvvm.XViewModel;

public class MainViewModel extends XViewModel<IMainView> {


    /**
     * 构造方法
     *
     * @param application
     */
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void onFullScreenClicked() {
        getView().setMainFullScreen();
    }
}
