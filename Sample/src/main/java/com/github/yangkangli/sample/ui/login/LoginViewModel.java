package com.github.yangkangli.sample.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;

import com.github.yangkangli.x.mvvm.BaseViewModel;

public class LoginViewModel extends BaseViewModel<ILoginView> {


    /**
     * 构造方法
     *
     * @param application
     */
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
}
