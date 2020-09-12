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

import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.utils.UIUtils;

import me.jessyan.autosize.internal.CustomAdapt;

public abstract class XFragment<Binding extends ViewDataBinding, ViewModel extends XViewModel> extends Fragment implements CustomAdapt {

    private static final String TAG = "XFragment";

    private XActivity activity;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof XActivity) {
            XActivity activity = (XActivity) context;
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
        return binding.getRoot();
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
    protected XActivity getBaseActivity() {
        return activity;
    }

    protected ViewModel getViewModel() {
        return viewModel;
    }


    @Override
    public boolean isBaseOnWidth() {
        return !UIUtils.isScreenLandscape();
    }

    @Override
    public float getSizeInDp() {
//        if (isBaseOnWidth()) {
//            return ContextUtils.getApplication().getDesignWidth();
//        }
//        return ContextUtils.getApplication().getDesignHeight();

        return ContextUtils.getApplication().getDesignWidth();
    }
}
