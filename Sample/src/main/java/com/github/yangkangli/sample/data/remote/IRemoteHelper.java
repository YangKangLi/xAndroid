package com.github.yangkangli.sample.data.remote;

import com.github.yangkangli.sample.model.UserAgreementBean;
import com.github.yangkangli.sample.model.UserBean;

import java.util.Map;

import io.reactivex.Observable;

public interface IRemoteHelper {

    /**
     * 请求服务器获得用户协议
     *
     * @return
     */
    Observable<UserAgreementBean> requestUserAgreement();

    /**
     * 获得用户协议
     *
     * @return
     */
    Observable<UserBean> requestUserBean(Map<String, String> pathParams);
}
