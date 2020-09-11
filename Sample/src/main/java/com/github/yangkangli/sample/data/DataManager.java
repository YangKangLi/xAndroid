package com.github.yangkangli.sample.data;

import android.util.Log;

import com.github.yangkangli.sample.data.local.IPreferHelper;
import com.github.yangkangli.sample.data.local.PreferHelper;
import com.github.yangkangli.sample.data.remote.IRemoteHelper;
import com.github.yangkangli.sample.data.remote.RemoteHelper;
import com.github.yangkangli.sample.model.UserAgreementBean;
import com.github.yangkangli.sample.model.UserBean;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DataManager {


    private IRemoteHelper remoteHelper;


    private IPreferHelper preferHelper;

    private static class SingletonHolder {
        private static final DataManager INSTANCE = new DataManager();
    }

    public static DataManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DataManager() {
        this.remoteHelper = new RemoteHelper();
        this.preferHelper = new PreferHelper();
    }


    /**
     * 用户是否同意了用户服务协议与隐私政策
     *
     * @return
     */
    public boolean isUserAgreementAgreed() {
        return preferHelper.isUserAgreementAgreed();
    }

    /**
     * 是否第一次运行
     *
     * @return
     */
    public boolean isFirstLaunch() {
        return preferHelper.isFirstLaunch();
    }

    /**
     * 是否用户已登录
     *
     * @return
     */
    public boolean isUserLogin() {
        return false;
    }

    /**
     * 获得用户协议内容（标题，内容，按钮文本等等）
     *
     * @return
     */
    public Observable<UserAgreementBean> getUserAgreementBean() {
        return Observable.just(1)
                .observeOn(Schedulers.io())
                .flatMap(new Function<Integer, Observable<UserAgreementBean>>() {
                    // 从网络获取数据
                    @Override
                    public Observable<UserAgreementBean> apply(Integer integer) throws Exception {
                        return remoteHelper.requestUserAgreement();
                    }
                }).onErrorReturn(new Function<Throwable, UserAgreementBean>() {
                    // 如果从网络获取失败，则使用本地数据
                    @Override
                    public UserAgreementBean apply(Throwable throwable) throws Exception {
                        Log.d("Adam", "DataManager -> BBBB -> " + Thread.currentThread().getName());
                        UserBean userBean = new UserBean();
                        userBean.setId("2223");
                        userBean.setFirstname("Adam");
                        userBean.setLastname("Yang");
                        userBean.setFormLocal(true);
                        return null;
                    }
                });
    }


    public Observable<UserBean> getUserInfo(final String key) {

        return Observable.just(1)
                .observeOn(Schedulers.io())
                .flatMap(new Function<Integer, Observable<UserBean>>() {
                    // 从网络获取数据
                    @Override
                    public Observable<UserBean> apply(Integer integer) throws Exception {
                        Log.d("Adam", "DataManager -> AAAA -> " + Thread.currentThread().getName());
                        Map<String, String> pathParams = new LinkedHashMap<>();
                        pathParams.put("id", key);
                        return remoteHelper.requestUserBean(pathParams);
                    }
                }).onErrorReturn(new Function<Throwable, UserBean>() {
                    // 如果从网络获取失败，则使用本地数据
                    @Override
                    public UserBean apply(Throwable throwable) throws Exception {
                        Log.d("Adam", "DataManager -> BBBB -> " + Thread.currentThread().getName());
                        UserBean userBean = new UserBean();
                        userBean.setId("2223");
                        userBean.setFirstname("Adam");
                        userBean.setLastname("Yang");
                        userBean.setFormLocal(true);
                        return userBean;
                    }
                });
    }
}
