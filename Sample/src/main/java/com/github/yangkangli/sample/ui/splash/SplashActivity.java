package com.github.yangkangli.sample.ui.splash;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yangkangli.sample.BR;
import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.ActivitySplashBinding;
import com.github.yangkangli.sample.ui.guide.GuideActivity;
import com.github.yangkangli.sample.ui.login.LoginActivity;
import com.github.yangkangli.x.mvvm.BaseActivity;
import com.github.yangkangli.x.sample.base.router.RouterPathActivity;
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
    protected SplashViewModel initViewModel() {
        return createSimpleViewModel(SplashViewModel.class, this);
    }


    // =============================================================================================
    // ISplashView接口实现方法
    // =============================================================================================


    @Override
    public void showUserAgreementDialog() {

    }

    @Override
    public void entryGuideActivity() {
        GuideActivity.openActivity();
        finish();
    }

    @Override
    public void entryLoginActivity() {
        LoginActivity.openActivity();
        finish();
    }

    @Override
    public void entryMainActivity() {
        ARouter.getInstance().build(RouterPathActivity.Sample.PAGE_MAIN).navigation();
        finish();
    }
}
