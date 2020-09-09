package com.github.yangkangli.x.mvvm;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<Binding extends ViewDataBinding, ViewModel extends BaseViewModel> extends Fragment implements IBaseView {


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
}
