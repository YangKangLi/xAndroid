package com.github.yangkangli.sample.ui.guide;

import com.github.yangkangli.x.mvvm.IXView;

public interface IGuideView extends IXView {

    /**
     * 打开主界面
     */
    void entryMainActivity();

    /**
     * 打开登录界面
     */
    void entryLoginActivity();
}
