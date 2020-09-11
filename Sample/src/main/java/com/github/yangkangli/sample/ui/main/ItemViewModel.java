package com.github.yangkangli.sample.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.github.yangkangli.x.mvvm.BaseViewModel;

public class ItemViewModel extends BaseViewModel {

    private boolean isDataLoaded = false;

    private final ObservableField<String> content = new ObservableField<>();

    /**
     * 构造方法
     *
     * @param application
     */
    public ItemViewModel(@NonNull Application application, String content) {
        super(application);
        this.content.set(content);
    }

    public ObservableField<String> getContent() {
        return content;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.d("FragmentItem", "ItemViewModel -> onCreate -> " + content.get());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d("FragmentItem", "ItemViewModel -> onResume -> " + content.get());
        if (!isDataLoaded) {
//            String url = "https://fierce-cove-29863.herokuapp.com/getAnUserDetail/2";
//            getCompositeDisposable().add(
//                    HttpManager.getInstance().get(url, null, UserBean.class,
//                            new HttpCallback<UserBean>() {
//
//                                @Override
//                                public void onSuccess(UserBean result) {
//                                    Log.d("FragmentItem", "ItemViewModel -> onSuccess -> " + content.get() + " -> " + result.toString());
//                                    isDataLoaded = true;
//                                }
//                            }));
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(){
        Log.d("FragmentItem", "ItemViewModel -> onDestroy -> " + content.get());
    }

    /**
     * ItemViewModel工厂类
     */
    public static class ModelFactory extends ViewModelProvider.AndroidViewModelFactory {

        private Application application;

        private String content;

        /**
         * Creates a {@code AndroidViewModelFactory}
         *
         * @param application an application to pass in AndroidViewModel
         */
        public ModelFactory(@NonNull Application application, String content) {
            super(application);
            this.application = application;
            this.content = content;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ItemViewModel(application, content);
        }
    }
}
