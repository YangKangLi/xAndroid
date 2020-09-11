package com.github.yangkangli.sample.data.remote;

import com.github.yangkangli.sample.model.UserAgreementBean;
import com.github.yangkangli.sample.model.UserBean;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.Map;

import io.reactivex.Observable;

public class RemoteHelper implements IRemoteHelper {


    @Override
    public Observable<UserAgreementBean> requestUserAgreement() {
        return Rx2AndroidNetworking
                .get(ApiEndPoint.ENDPOINT_USER_AGREEMENT)
                .build()
                .getObjectObservable(UserAgreementBean.class);
    }

    @Override
    public Observable<UserBean> requestUserBean(Map<String, String> pathParams) {

        return Rx2AndroidNetworking
                .get(ApiEndPoint.ENDPOINT_USER_AGREEMENT)
                .addPathParameter(pathParams)
                .build()
                .getObjectObservable(UserBean.class);
    }
}