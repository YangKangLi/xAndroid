package com.github.yangkangli.sample.data.local;

import com.github.yangkangli.sample.model.UserBean;

import io.reactivex.Observable;

public interface IPreferHelper {


    /**
     * 用户是否同意了用户服务协议与隐私政策
     *
     * @return
     */
    boolean isUserAgreementAgreed();


    /**
     * 是否第一次运行
     *
     * @return
     */
    boolean isFirstLaunch();

    Observable<UserBean> readUserAgreement();
}
