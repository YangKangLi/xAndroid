package com.github.yangkangli.sample.ui.splash;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.github.yangkangli.sample.BR;
import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.ActivitySplashBinding;
import com.github.yangkangli.x.mvvm.BaseActivity;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements ISplashView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_BAR).init();
    }

    @Override
    protected SplashViewModel createViewModel() {
        return new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(ContextUtils.getApplication())).get(SplashViewModel.class);
    }

    @Override
    protected void initViewModel(SplashViewModel viewModel) {
        viewModel.setView(this);
    }

    @Override
    public void showUserAgreementDialog() {

    }

    @Override
    public void entryGuideActivity() {
        Log.d("Adam", "进入引导界面");
    }

    @Override
    public void entryLoginActivity() {
        Log.d("Adam", "进入登录界面");
    }

    @Override
    public void entryMainActivity() {
        Log.d("Adam", "进入主界面");
    }
}
