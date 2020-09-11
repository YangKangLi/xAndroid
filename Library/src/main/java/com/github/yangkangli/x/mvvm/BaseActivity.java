package com.github.yangkangli.x.mvvm;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.github.yangkangli.x.mvvm.utils.NetworkUtils;
import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseActivity<Binding extends ViewDataBinding, ViewModel extends BaseViewModel> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    private Binding binding;

    private ViewModel viewModel;

    /**
     * 获得布局资源ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 获得布局资源文件中ViewModel的变量名
     *
     * @return
     */
    protected abstract int getBindingVariable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //immersionBar = ImmersionBar.with(this);
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 构建ViewModel对象
     *
     * @return
     */
    protected abstract ViewModel createViewModel();

    protected abstract void initViewModel(ViewModel viewModel);

    /**
     * 执行数据绑定
     */
    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = createViewModel();
        if (viewModel != null) {
            initViewModel(viewModel);
            binding.setVariable(getBindingVariable(), viewModel);
            binding.executePendingBindings();
            getLifecycle().addObserver(viewModel);
        }
    }

    protected Binding getBinding() {
        return binding;
    }


    // ============================================================================================
    // 辅助方法
    // ============================================================================================


    /**
     * 判断是否授权
     *
     * @param permission
     * @return
     */
    public boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

    /**
     * 判断当前网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    /**
     * 设置状态栏
     *
     * @param colorId 状态栏的颜色
     * @param dark    状态栏图标文字是否使用暗色
     */
    protected void setStatusBar(@ColorRes int colorId, boolean dark) {
        ImmersionBar.with(this)
                .statusBarColor(colorId).statusBarDarkFont(dark).init();
    }

    protected void setStatusBarVisible(boolean visible) {
        if (visible) {
            ImmersionBar.showStatusBar(getWindow());
        } else {
            ImmersionBar.hideStatusBar(getWindow());
        }
    }

    /**
     * 获得状态栏的高度
     *
     * @return
     */
    protected int getStatusBarheight() {
        return ImmersionBar.getStatusBarHeight(this);
    }

    /**
     * 获得导航栏的高度（若没有导航栏，则返回0）
     *
     * @return
     */
    protected int getNavigationBarHeight() {
        if (ImmersionBar.hasNavigationBar(this)) {
            return ImmersionBar.getNavigationBarHeight(this);
        }
        return 0;
    }
}
























