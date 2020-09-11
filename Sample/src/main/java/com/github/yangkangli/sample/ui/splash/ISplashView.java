package com.github.yangkangli.sample.ui.splash;

import com.github.yangkangli.x.mvvm.IBaseView;

public interface ISplashView extends IBaseView {

    /**
     * 显示用户协议对话框
     */
    void showUserAgreementDialog();

    /**
     * 进入向导页面
     */
    void entryGuideActivity();

    /**
     * 进入登录界面
     */
    void entryLoginActivity();

    /**
     * 进入主界面
     */
    void entryMainActivity();
}
