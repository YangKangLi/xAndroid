package com.github.yangkangli.sample.ui.main;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.github.yangkangli.sample.BR;
import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.FragmentItemBinding;
import com.github.yangkangli.x.mvvm.BaseFragment;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;

public class FragmentItem extends BaseFragment<FragmentItemBinding, ItemViewModel> {

    private static final String ARGS_CONTENT = "ARGS_CONTENT";

    public static FragmentItem newInstance(String content) {
        FragmentItem fragment = new FragmentItem();
        Bundle args = new Bundle();
        args.putString(ARGS_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_item;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void initView() {
       // setStatusBar(false);
    }

    @Override
    protected ItemViewModel initViewModel() {
        String content = getArguments().getString(ARGS_CONTENT);
        ItemViewModel itemViewModel = new ViewModelProvider(
                this,
                new ItemViewModel.ModelFactory(ContextUtils.getApplication(), content)).get(content, ItemViewModel.class);
        return itemViewModel;
    }
}
