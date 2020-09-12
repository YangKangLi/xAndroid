package com.github.yangkangli.x.mvvm.widgets.dialog.core;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.utils.UIUtils;

import me.jessyan.autosize.internal.CustomAdapt;

public abstract class XBaseDialog extends DialogFragment implements CustomAdapt {

    private static final String V_MARGIN = "v_margin";
    private static final String H_MARGIN = "h_margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String GRAVITY = "gravity";
    private static final String CANCEL = "out_cancel";
    private static final String THEME = "theme";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";

    @StyleRes
    protected int theme = R.style.XBaseDialogStyle; // dialog默认主题

    @LayoutRes
    protected int layoutId;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, initTheme());

        //恢复保存的数据
        if (savedInstanceState != null) {
            vMargin = savedInstanceState.getInt(V_MARGIN);
            hMargin = savedInstanceState.getInt(H_MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            gravity = savedInstanceState.getInt(GRAVITY);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            theme = savedInstanceState.getInt(THEME);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(V_MARGIN, vMargin);
        outState.putInt(H_MARGIN, hMargin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putInt(GRAVITY, gravity);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(THEME, theme);
        outState.putInt(LAYOUT, layoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutId = intLayoutId();
        View rootView = inflater.inflate(layoutId, container, false);
        convertView(ViewHolder.create(rootView), this);
        return rootView;
    }

    /**
     * 初始化LayoutId
     *
     * @return
     */
    protected abstract int intLayoutId();

    /**
     * 转换视图
     *
     * @param holder
     * @param dialog
     */
    public abstract void convertView(ViewHolder holder, XBaseDialog dialog);

    /**
     * 初始化主题
     *
     * @return
     */
    private int initTheme() {
        return theme;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d("Adam", "XBaseDialog -> onStart");

        // 在这里初始化参数（得到Dialog的Window对象）

        Window window = getDialog().getWindow();

        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            //设置对话框的宽度
            switch (width) {
                case WindowManager.LayoutParams.MATCH_PARENT:
                    lp.width = UIUtils.getScreenWidth() - 2 * UIUtils.dp2px(hMargin);
                    break;
                case WindowManager.LayoutParams.WRAP_CONTENT:
                    lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                    break;
                default:
                    Log.d("Adam", "UIUtils.dp2px(" + width + ") = " + UIUtils.dp2px(width));
                    Log.d("Adam", "UIUtils.getScreenWidth() = " + UIUtils.getScreenWidth());
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

            // 设置对话框的位置
            if (gravity != 0) {
                lp.gravity = gravity;
            }

            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;

            // 最后设置参数
            window.setAttributes(lp);
        }

        setCancelable(outCancel);
    }

    /**
     * 显示对话框
     *
     * @param manager
     * @return
     */
    public XBaseDialog show(FragmentManager manager) {
        FragmentTransaction ft = manager.beginTransaction();
        if (this.isAdded()) {
            ft.remove(this).commit();
        }
        ft.add(this, String.valueOf(System.currentTimeMillis()));
        ft.commitAllowingStateLoss();
        return this;
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

    // =============================================================================================
    // 对话框属性
    // =============================================================================================

    // 左右外边距
    protected int hMargin = 0;

    // 上下外边距
    protected int vMargin = 0;

    // 宽度，默认包裹内容
    protected int width = WindowManager.LayoutParams.WRAP_CONTENT;

    // 高度，默认包裹内容
    protected int height = WindowManager.LayoutParams.WRAP_CONTENT;

    // 灰度深浅，默认0.5
    protected float dimAmount = 0.5f;

    // 显示的位置，默认剧中
    protected int gravity = Gravity.CENTER;

    // 是否点击外部取消
    protected boolean outCancel = true;


    /**
     * 设置主题
     *
     * @param theme
     * @return
     */
    public XBaseDialog setTheme(@StyleRes int theme) {
        this.theme = theme;
        return this;
    }

    /**
     * 设置布局资源
     *
     * @param layoutId
     * @return
     */
    public XBaseDialog setLayout(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public XBaseDialog setHMargin(int hMargin) {
        this.hMargin = hMargin;
        return this;
    }

    public XBaseDialog setVMargin(int vMargin) {
        this.vMargin = vMargin;
        return this;
    }

    public XBaseDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public XBaseDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public XBaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public XBaseDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public XBaseDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public interface OnClickListener {
        void onClick(XBaseDialog dialog, int id);
    }
}
