package com.github.yangkangli.x.mvvm.widgets.dialog;

import androidx.lifecycle.ViewModelProvider;

import com.github.yangkangli.x.mvvm.BR;
import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.XViewModel;
import com.github.yangkangli.x.mvvm.databinding.DialogAlertLayoutBinding;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;

public class XAlertDialog extends XBaseDialog<DialogAlertLayoutBinding, XViewModel> implements IXDialog {


    @Override
    protected int getLayoutId() {
        return R.layout.dialog_alert_layout;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected XViewModel initViewModel() {
        return new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(ContextUtils.getApplication()))
                .get(XViewModel.class);
    }

    @Override
    protected void initView(DialogAlertLayoutBinding binding) {

    }
}
