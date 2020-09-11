package com.github.yangkangli.sample.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.github.yangkangli.x.mvvm.XViewModel;

public class LoginViewModel extends XViewModel<ILoginView> {


    /**
     * 构造方法
     *
     * @param application
     */
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        getView().showLoginFailed("aaaa");
    }
}
