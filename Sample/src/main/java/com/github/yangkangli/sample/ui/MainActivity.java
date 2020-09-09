package com.github.yangkangli.sample.ui;

import android.os.Bundle;

import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.ActivityMainBinding;
import com.github.yangkangli.x.mvvm.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getBindingVariable() {
        return com.github.yangkangli.sample.BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected MainViewModel initViewModel() {
        return null;//new ViewModelProvider(this).get(MainViewModel.class);
    }
}
