package com.github.yangkangli.sample.ui.login;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yangkangli.sample.BR;
import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.ActivityLoginBinding;
import com.github.yangkangli.sample.ui.main.MainActivity;
import com.github.yangkangli.x.mvvm.XActivity;
import com.github.yangkangli.x.sample.base.router.RouterPathActivity;

@Route(path = RouterPathActivity.Sample.PAGE_LOGIN)
public class LoginActivity extends XActivity<ActivityLoginBinding, LoginViewModel> implements ILoginView {

    /**
     * 打开Activity
     */
    public static void openActivity() {
        ARouter.getInstance().build(RouterPathActivity.Sample.PAGE_LOGIN).navigation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected LoginViewModel initViewModel() {
        return createSimpleViewModel(LoginViewModel.class, this);
    }

    // =============================================================================================
    // ILoginView实现方法
    // =============================================================================================

    @Override
    public void showLoginFailed(String reason) {
        Log.d("Adam", "showLoginFailed -> reason = " + reason);
    }

    @Override
    public void entryMainActivity() {
        MainActivity.openActivity();
        finish();
    }
}
