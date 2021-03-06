package com.github.yangkangli.sample.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.yangkangli.sample.BR;
import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.ActivityMainBinding;
import com.github.yangkangli.x.mvvm.XActivity;
import com.github.yangkangli.x.mvvm.XFragment;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.widgets.dialog.XDialog;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewConvertListener;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;
import com.github.yangkangli.x.sample.base.widgets.dialog.XAlertDialog;
import com.github.yangkangli.x.sample.base.widgets.dialog.XConfirmDialog;
import com.github.yangkangli.x.mvvm.widgets.dialog.XDialogManager;
import com.github.yangkangli.x.sample.base.widgets.dialog.XLoadingDialog;
import com.github.yangkangli.x.sample.base.router.RouterPathActivity;

import java.util.LinkedList;
import java.util.List;

@Route(path = RouterPathActivity.Sample.PAGE_MAIN)
public class MainActivity extends XActivity<ActivityMainBinding, MainViewModel> implements IMainView {


    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    /**
     * 打开Activity
     */
    public static void openActivity() {
        ARouter.getInstance().build(RouterPathActivity.Sample.PAGE_MAIN).navigation();
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

    private List<XFragment> generateFragments() {
        List<XFragment> fragments = new LinkedList<>();
        fragments.add(FragmentItem.newInstance("第一个Fragment"));
        fragments.add(FragmentItem.newInstance("第二个Fragment"));
        fragments.add(FragmentItem.newInstance("第三个Fragment"));
        fragments.add(FragmentItem.newInstance("第四个Fragment"));
        return fragments;
    }

    @Override
    protected MainViewModel initViewModel() {
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(ContextUtils.getApplication())).get(MainViewModel.class);
        mainViewModel.setView(this);
        return mainViewModel;
    }


    @Override
    public void setMainFullScreen() {

//        XAlertDialog.init()
//                .setContent("我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容" +
//                        "我是内容我是内容我是内容我是内容我是内容我是内容")
//                .setWidth(300)
//                .setHeight(450)
//                .setOutCancel(false)
//                .show();
        XAlertDialog.init().setTitle("警告对话框框")
                .setContent("我是警告对话框的内容")
                .requestShow(XDialogManager.getInstance(), getSupportFragmentManager());

       // final XLoadingDialog loadingDialog = XLoadingDialog.init().setContent("请稍等").requestShow(XDialogManager.getInstance(), getSupportFragmentManager());

        XConfirmDialog.init().setTitle("确认对话框")
                .setContent("我是确认对话框的内容")

                .requestShow(XDialogManager.getInstance(), getSupportFragmentManager());


        XBaseDialog baseDialog = XDialog.init()
                .setLayout(R.layout.dialog_alert_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final XBaseDialog dialog) {
                        Log.d("Adam", "XDialog -> my convertView");
                        holder.setText(R.id.tv_content, "啊实打实打算");
                        holder.setOnClickListener(R.id.btn_positive, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .setHeight(300)
                .setOutCancel(true)
                .setHMargin(30);

        XDialogManager.getInstance().requestShow(baseDialog, getSupportFragmentManager());

//        new Handler(getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadingDialog.dismiss();
//            }
//        }, 1000 * 5);


    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {


        private List<XFragment> fragments;


        public ViewPagerAdapter(@NonNull FragmentManager fm, List<XFragment> fragments) {
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
