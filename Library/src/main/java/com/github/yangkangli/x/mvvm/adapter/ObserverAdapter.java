package com.github.yangkangli.x.mvvm.adapter;

import io.reactivex.Observer;

public abstract class ObserverAdapter<T> implements Observer<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
