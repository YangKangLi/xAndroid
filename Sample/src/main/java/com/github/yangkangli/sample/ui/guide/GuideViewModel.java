package com.github.yangkangli.sample.ui.guide;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.github.yangkangli.sample.data.DataManager;
import com.github.yangkangli.x.mvvm.BaseViewModel;

public class GuideViewModel extends BaseViewModel<IGuideView> {

    // 是否显示跳过，默认显示
    private ObservableField<Boolean> showSkip = new ObservableField<>(true);

    // 是否显示立即体验。默认不显示
    private ObservableField<Boolean> showEntryNow = new ObservableField<>(false);

    /**
     * 构造方法
     *
     * @param application
     */
    public GuideViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<Boolean> getShowSkip() {
        return showSkip;
    }

    public void setShowSkip(Boolean showSkip) {
        this.showSkip.set(showSkip);
    }

    public ObservableField<Boolean> getShowEntryNow() {
        return showEntryNow;
    }

    public void setShowEntryNow(Boolean showEntryNow) {
        this.showEntryNow.set(showEntryNow);
    }

    /**
     * 点击了“跳过”
     */
    public void onClickSkip() {
        openMainOrLogin();
    }

    /**
     * 点击了“立即进入”
     */
    public void onClickEntryNow() {
        openMainOrLogin();
    }

    /**
     * 如果已登录，则打开主界面，否则打开登录界面
     */
    private void openMainOrLogin() {
        if (DataManager.getInstance().isUserLogin()) {
            getView().entryMainActivity();
        } else {
            getView().entryLoginActivity();
        }
    }
}
