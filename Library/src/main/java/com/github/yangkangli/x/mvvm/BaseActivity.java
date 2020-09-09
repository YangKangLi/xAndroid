package com.github.yangkangli.x.mvvm;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.github.yangkangli.x.mvvm.utils.NetworkUtils;

public abstract class BaseActivity<Binding extends ViewDataBinding, ViewModel extends BaseViewModel> extends AppCompatActivity {

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
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    /**
     * 构建ViewModel对象
     *
     * @return
     */
    protected abstract ViewModel initViewModel();

    /**
     * 执行数据绑定
     */
    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = initViewModel();
        if (viewModel != null) {
            binding.setVariable(getBindingVariable(), viewModel);
            binding.executePendingBindings();
            getLifecycle().addObserver(viewModel);
        }
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
}
