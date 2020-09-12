package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XLoadingDialog extends XBaseDialog {

    private String content;


    public static XLoadingDialog init() {
        return new XLoadingDialog();
    }

    public XLoadingDialog() {
        this.content = ContextUtils.getApplication().getString(R.string.dialog_default_loading_text);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_loading_layout;
    }

    public XLoadingDialog setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public void convertView(ViewHolder holder, XBaseDialog dialog) {
        holder.setText(R.id.tv_content, content);
    }
}
