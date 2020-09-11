package com.github.yangkangli.sample.ui.guide;

import com.github.yangkangli.x.mvvm.IBaseView;

public interface IGuideView extends IBaseView {

    /**
     * 打开主界面
     */
    void entryMainActivity();

    /**
     * 打开登录界面
     */
    void entryLoginActivity();
}
