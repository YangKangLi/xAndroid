package com.github.yangkangli.sample.ui.splash;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.github.yangkangli.sample.data.DataManager;
import com.github.yangkangli.sample.model.UserAgreementBean;
import com.github.yangkangli.x.mvvm.XViewModel;
import com.github.yangkangli.x.mvvm.adapter.ObserverAdapter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class SplashViewModel extends XViewModel<ISplashView> {

    /**
     * 构造方法
     *
     * @param application
     */
    public SplashViewModel(@NonNull Application application) {
        super(application);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        // 判断是否用户同意了用户服务协议和隐私政策
        if (!DataManager.getInstance().isUserAgreementAgreed()) {
            getUserAgreement();
        } else {
            entryNextActivity();
        }
    }

    /**
     * 获取用户服务协议&隐私政策，并在获得后展示对话框
     */
    private void getUserAgreement() {
        DataManager.getInstance().getUserAgreementBean()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ObserverAdapter<UserAgreementBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(UserAgreementBean userAgreementBean) {
                        getView().showUserAgreementDialog();
                    }
                });
    }

    /**
     * 进入下一个界面
     */
    private void entryNextActivity() {
        Log.d("Adam", "entryNextActivity");
        Observable.just(1).observeOn(Schedulers.computation())
                .map(new Function<Integer, Object>() {
                    @Override
                    public Object apply(Integer integer) throws Exception {
                        Thread.sleep(500);
                        return new Object();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ObserverAdapter<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                        Log.d("Adam", "entryNextActivity -》 onSubscribe");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d("Adam", "entryNextActivity -》 onNext");
                        if (DataManager.getInstance().isFirstLaunch()) {
                            getView().entryGuideActivity();
                        } else if (!DataManager.getInstance().isUserLogin()) {
                            getView().entryLoginActivity();
                        } else {
                            getView().entryMainActivity();
                        }
                    }
                });
    }
}