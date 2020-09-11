package com.github.yangkangli.sample.ui.guide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.bumptech.glide.Glide;
import com.github.yangkangli.sample.BR;
import com.github.yangkangli.sample.R;
import com.github.yangkangli.sample.databinding.ActivityGuideBinding;
import com.github.yangkangli.sample.ui.login.LoginActivity;
import com.github.yangkangli.sample.ui.main.MainActivity;
import com.github.yangkangli.x.mvvm.XActivity;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.utils.UIUtils;
import com.github.yangkangli.x.sample.base.router.RouterPathActivity;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPathActivity.Sample.PAGE_GUIDE)
public class GuideActivity extends XActivity<ActivityGuideBinding, GuideViewModel> implements IGuideView, OnPageChangeListener {

    /**
     * 打开Activity
     */
    public static void openActivity() {
        ARouter.getInstance().build(RouterPathActivity.Sample.PAGE_GUIDE).navigation();
    }

    // 引导界面的图片资源列表
    private static final List<Integer> GUIDE_IMAGE_RES_IDS = new ArrayList<>();

    // 引导界面的图片张数
    private static final int GUIDE_IMAGE_COUNT;

    static {
        GUIDE_IMAGE_RES_IDS.add(R.drawable.app_guide_01);
        GUIDE_IMAGE_RES_IDS.add(R.drawable.app_guide_02);
        GUIDE_IMAGE_RES_IDS.add(R.drawable.app_guide_03);
        // 初始化图片数量
        GUIDE_IMAGE_COUNT = GUIDE_IMAGE_RES_IDS.size();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarDarkFont(true).hideBar(BarHide.FLAG_HIDE_BAR).init();
        initGuide();
        optionalInitGuide();
    }

    @Override
    protected GuideViewModel initViewModel() {
        return createSimpleViewModel(GuideViewModel.class, this);
    }

    private void initGuide() {
        // 使用ConvenientBanner实现引导界面
        CBViewHolderCreator creator = new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new GuideImageHolder(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.activity_guide_image_layout;
            }
        };
        getBinding().convenientBanner
                .setPages(creator, GUIDE_IMAGE_RES_IDS)
                // 设置标识器图片以及排放方式
                .setPageIndicator(new int[]{R.drawable.app_guide_point_normal, R.drawable.app_guide_point_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                // 设置显示底部的标识器
                .setPointViewVisible(true)
                //设置不循环
                .setCanLoop(false)
                // 设置监听
                .setOnPageChangeListener(this);
    }

    /**
     * 初始化Guide（可选）：
     * 1）设置RecyclerView的OverScrollMode
     * 2）调整底部Point的位置
     */
    private void optionalInitGuide() {
        for (int i = 0; i < getBinding().convenientBanner.getChildCount(); i++) {
            View childAt = getBinding().convenientBanner.getChildAt(i);
            if (childAt instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) childAt;
                recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
            } else if (childAt instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) childAt;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
                params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, UIUtils.dp2px(37));
                linearLayout.setLayoutParams(params);
                setPointParams(linearLayout);
            }
        }
    }

    /**
     * 设置点的参数
     *
     * @param linearLayout
     */
    private void setPointParams(LinearLayout linearLayout) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childAt = linearLayout.getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            params.setMargins(UIUtils.dp2px(4), params.topMargin, UIUtils.dp2px(4), params.bottomMargin);
            childAt.setLayoutParams(params);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    }

    @Override
    public void onPageSelected(int index) {
        // 设置“跳过”，“立即体验”按钮的可见性
        getViewModel().setShowSkip(index != GUIDE_IMAGE_COUNT - 1);
        getViewModel().setShowEntryNow(index == GUIDE_IMAGE_COUNT - 1);
    }

    // =============================================================================================
    // IGuideView实现方法
    // =============================================================================================

    @Override
    public void entryMainActivity() {
        MainActivity.openActivity();
        finish();
    }

    @Override
    public void entryLoginActivity() {
        LoginActivity.openActivity();
        finish();
    }


    // =============================================================================================
    // 内部类
    // =============================================================================================

    private static class GuideImageHolder extends Holder<Integer> {

        private ImageView imageView;

        public GuideImageHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.iv_guide);
        }

        @Override
        public void updateUI(Integer imageResId) {
            Glide.with(ContextUtils.getApplication())
                    .load(imageResId)
                    .into(imageView);
        }
    }
}
