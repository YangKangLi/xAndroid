package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.XViewModel;
import com.github.yangkangli.x.mvvm.utils.UIUtils;

public abstract class XBaseDialog<Binding extends ViewDataBinding, ViewModel extends XViewModel> extends DialogFragment {

    private Binding binding;

    private ViewModel viewModel;

    protected int layoutId;

    protected int bindingVariable;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Adam", "XBaseDialog -> onCreate");
        setStyle(STYLE_NO_TITLE, R.style.XBaseDialogStyle);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Adam", "XBaseDialog -> onCreateView");
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("Adam", "XBaseDialog -> onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        viewModel = initViewModel();
        if (viewModel != null) {
            binding.setVariable(getBindingVariable(), viewModel);
            binding.executePendingBindings();
            getLifecycle().addObserver(viewModel);
        }
        initView(binding);
    }

    /**
     * 构建ViewModel对象
     *
     * @return
     */
    protected abstract ViewModel initViewModel();

    protected abstract void initView(Binding binding);

    @Override
    public void onDestroyView() {
        Log.d("Adam", "XBaseDialog -> onDestroyView");
        super.onDestroyView();
        binding.unbind();
        binding = null;
        if (viewModel != null) {
            getLifecycle().removeObserver(viewModel);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("Adam", "XBaseDialog -> onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Adam", "XBaseDialog -> onStart");

        // 在这里初始化参数（得到Dialog的Window对象）

        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();

            //设置对话框的宽度
            switch (width) {
                case WindowManager.LayoutParams.MATCH_PARENT:
                    lp.width = UIUtils.getScreenWidth() - 2 * UIUtils.dp2px(hMargin);
                    break;
                case WindowManager.LayoutParams.WRAP_CONTENT:
                    lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    break;
                default:
                    lp.width = UIUtils.dp2px(width);
                    break;
            }

            //设置dialog高度
            switch (height) {
                case WindowManager.LayoutParams.MATCH_PARENT:
                    lp.height = UIUtils.getScreenHeight() - 2 * UIUtils.dp2px(vMargin);
                    break;
                case WindowManager.LayoutParams.WRAP_CONTENT:
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    break;
                default:
                    lp.height = UIUtils.dp2px(height);
                    break;
            }
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.height = UIUtils.dp2px(height);
            }

            // 设置对话框的位置
            if (gravity != 0) {
                lp.gravity = gravity;
            }


            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;


            // 最后设置参数
            window.setAttributes(lp);
        }

        setOutCancel(outCancel);
    }

    /**
     * 显示对话框
     *
     * @param manager
     * @return
     */
    public XBaseDialog show(FragmentManager manager) {
        Log.d("Adam", "XBaseDialog -> show");
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
    }

    // =============================================================================================
    // 对话框属性
    // =============================================================================================

    // 左右外边距
    private int hMargin;

    // 上下外边距
    private int vMargin;

    // 宽度，默认包裹内容
    private int width = WindowManager.LayoutParams.WRAP_CONTENT;

    // 高度，默认包裹内容
    private int height = WindowManager.LayoutParams.WRAP_CONTENT;

    // 灰度深浅，默认0.5
    private float dimAmount = 0.5f;

    // 显示的位置，默认剧中
    private int gravity = Gravity.CENTER;

    // 是否点击外部取消
    private boolean outCancel = true;

    /**
     * 设置对话框尺寸
     *
     * @param width
     * @param height
     * @return
     */
    public XBaseDialog setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * 设置左右外边距
     *
     * @param margin
     * @return
     */
    public XBaseDialog setHMargin(int margin) {
        this.hMargin = margin;
        return this;
    }

    /**
     * 设置上下外边距
     *
     * @param margin
     * @return
     */
    public XBaseDialog setVMargin(int margin) {
        this.vMargin = margin;
        return this;
    }

    /**
     * 设置显示位置
     *
     * @param gravity
     * @return
     */
    public XBaseDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置点击对话框外部能否取消
     *
     * @param outCancel
     * @return
     */
    public XBaseDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    /**
     * 设置灰色背景深浅
     *
     * @param dimAmount
     * @return
     */
    public XBaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }
}
