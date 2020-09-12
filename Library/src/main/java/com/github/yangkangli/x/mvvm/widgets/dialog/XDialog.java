package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewConvertListener;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

import java.lang.ref.WeakReference;

public class XDialog extends XBaseDialog {

    private static final String CONVERT_LISTENER = "convert_listener";

    private WeakReference<ViewConvertListener> convertListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener = new WeakReference<>((ViewConvertListener) savedInstanceState.getParcelable(CONVERT_LISTENER));
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
        outState.putParcelable(CONVERT_LISTENER, convertListener.get());
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(ViewHolder holder, XBaseDialog dialog) {
        ViewConvertListener listener = convertListener.get();
        if (listener != null) {
            listener.convertView(holder, dialog);
        }
    }

    /**
     * 设置ConvertListener
     *
     * @param convertListener
     * @return
     */
    public XDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = new WeakReference<>(convertListener);
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
