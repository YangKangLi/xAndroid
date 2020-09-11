package com.github.yangkangli.sample.ui.login;

import com.github.yangkangli.x.mvvm.IBaseView;

public interface ILoginView extends IBaseView {


    /**
     * 显示登录失败信息
     *
     * @param reason
     */
    void showLoginFailed(String reason);

    /**
     * 登录成功，进入主界面
     */
    void entryMainActivity();
}
