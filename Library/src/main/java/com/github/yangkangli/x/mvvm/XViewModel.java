package com.github.yangkangli.x.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;


public class XViewModel<View extends IXView> extends AndroidViewModel implements LifecycleObserver {

    /**
     * IBaseView引用，用于在ViewModel中调用Activity，Fragment中的方法
     */
    private WeakReference<View> viewReference;

    /**
     * 存放Disposable对象，在系统回调ViewModel的onCleared方法时，取消所有的订阅
     */
    private CompositeDisposable compositeDisposable;

    /**
     * 构造方法
     *
     * @param application
     */
    public XViewModel(@NonNull Application application) {
        super(application);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    /**
     * 设置IBaseView
     *
     * @param view
     */
    public void setView(View view) {
        viewReference = new WeakReference<>(view);
    }

    /**
     * 获得IBaseView
     *
     * @return
     */
    public View getView() {
        return viewReference.get();
    }
    
    /**
     * 得到CompositeDisposable对象
     *
     * @return
     */
    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
