package com.github.yangkangli.sample.data.local;

import com.github.yangkangli.sample.model.UserBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class PreferHelper implements IPreferHelper {

    @Override
    public boolean isUserAgreementAgreed() {
        return true;
    }

    @Override
    public boolean isFirstLaunch() {
        return false;
    }

    @Override
    public Observable<UserBean> readUserAgreement() {

        Observable<UserBean> single = Observable.create(new ObservableOnSubscribe<UserBean>() {

            @Override
            public void subscribe(ObservableEmitter<UserBean> emitter) throws Exception {
                UserBean userBean = new UserBean();
                userBean.setId("1");
                userBean.setFirstname("Adam");
                userBean.setLastname("Yang11");
                userBean.setFormLocal(true);
                emitter.onNext(userBean);
                emitter.onComplete();
            }
        });
        return single;
    }
}
