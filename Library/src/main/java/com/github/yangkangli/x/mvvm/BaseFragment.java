package com.github.yangkangli.x.mvvm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

public abstract class BaseFragment<Binding extends ViewDataBinding, ViewModel extends BaseViewModel> extends Fragment implements IBaseView {

    private static final String TAG = "BaseFragment";

    private BaseActivity activity;

    private View rootView;

    private Binding binding;

    private ViewModel viewModel;

    //private ImmersionBar immersionBar;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.activity = activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        rootView = binding.getRoot();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        //immersionBar = ImmersionBar.with(this);
        super.onViewCreated(view, savedInstanceState);
        viewModel = initViewModel();
        if (viewModel != null) {
            binding.setVariable(getBindingVariable(), viewModel);
            binding.executePendingBindings();
            getLifecycle().addObserver(viewModel);
        }
        initView();
    }

    protected void initView() {

    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
        rootView = null;
        binding.unbind();
        binding = null;
        if (viewModel != null) {
            getLifecycle().removeObserver(viewModel);
        }
    }

    /**
     * 构建ViewModel对象
     *
     * @return
     */
    protected abstract ViewModel initViewModel();

    /**
     * 获得BaseActivity
     *
     * @return
     */
    protected BaseActivity getBaseActivity() {
        return activity;
    }

    protected ViewModel getViewModel() {
        return viewModel;
    }

    /**
     * 设置状态栏
     *
     * @param fillInStatusBar 是否将布局的内容填充到状态栏
     */
    protected void setStatusBar(boolean fillInStatusBar) {
        //immersionBar.statusBarColor(android.R.color.transparent).fitsSystemWindows(!fillInStatusBar).init();
    }
}
