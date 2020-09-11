package com.github.yangkangli.x.mvvm.widgets.dialog;

import androidx.databinding.ViewDataBinding;

import com.github.yangkangli.x.mvvm.XViewModel;

public class XDialog<Binding extends ViewDataBinding, ViewModel extends XViewModel> extends XBaseDialog<Binding, ViewModel> {
    @Override
    protected int getLayoutId() {
        return layoutId;
    }

    @Override
    protected int getBindingVariable() {
        return bindingVariable;
    }

    @Override
    protected ViewModel initViewModel() {
        return null;
    }

    @Override
    protected void initView(ViewDataBinding binding) {

    }

    public XDialog setLayoutId(int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public XDialog setBindingVariable(int bindingVariable) {
        this.bindingVariable = bindingVariable;
        return this;
    }
}
