package com.github.yangkangli.sample.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.github.yangkangli.sample.BR;
import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.ActivityMainBinding;
import com.github.yangkangli.x.mvvm.BaseActivity;
import com.github.yangkangli.x.mvvm.BaseFragment;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements IMainView {


    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setStatusBar(R.color.colorBlack, false);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), generateFragments());
        getBinding().viewPager.setOffscreenPageLimit(1);
        getBinding().viewPager.setAdapter(viewPagerAdapter);
        setStatusBarVisible(false);
    }

    private List<BaseFragment> generateFragments() {
        List<BaseFragment> fragments = new LinkedList<>();
        fragments.add(FragmentItem.newInstance("第一个Fragment"));
        fragments.add(FragmentItem.newInstance("第二个Fragment"));
        fragments.add(FragmentItem.newInstance("第三个Fragment"));
        fragments.add(FragmentItem.newInstance("第四个Fragment"));
        return fragments;
    }

    @Override
    protected MainViewModel createViewModel() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(ContextUtils.getApplication())).get(MainViewModel.class);
        mainViewModel.setView(this);
        return mainViewModel;
    }

    @Override
    protected void initViewModel(MainViewModel viewModel) {
        viewModel.setView(this);
    }

    @Override
    public void setMainFullScreen() {
        setStatusBarVisible(true);
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {


        private List<BaseFragment> fragments;


        public ViewPagerAdapter(@NonNull FragmentManager fm, List<BaseFragment> fragments) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments != null ? fragments.size() : 0;
        }
    }
}
