package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewConvertListener;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XDialog extends XBaseDialog {

    private static final String CONVERT_LISTENER = "convert_listener";

    private ViewConvertListener convertListener;


    public static XDialog init() {
        return new XDialog();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener = savedInstanceState.getParcelable(CONVERT_LISTENER);
        }
    }

    /**
     * 保存接口
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CONVERT_LISTENER, convertListener);
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(final ViewHolder holder, final XBaseDialog dialog) {
        ViewConvertListener listener = convertListener;
        if (listener != null) {
            listener.convertView(holder, dialog);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        convertListener = null;
    }

    /**
     * 设置ConvertListener
     *
     * @param convertListener
     * @return
     */
    public XDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    /**
     * 设置布局资源
     *
     * @param layoutId
     * @return
     */
    public XDialog setLayout(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }
}
