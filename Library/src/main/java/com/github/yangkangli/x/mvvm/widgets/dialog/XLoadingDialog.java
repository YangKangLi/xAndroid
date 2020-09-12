package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XLoadingDialog extends XBaseDialog {

    private static final String ARGS_CONTENT = "args_content";

    private String content;


    public static XLoadingDialog newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARGS_CONTENT, content);
        XLoadingDialog dialog = new XLoadingDialog();
        dialog.setArguments(args);
        dialog.setOutCancel(false);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content = getArguments().getString(ARGS_CONTENT);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_loading_layout;
    }

    @Override
    public void convertView(ViewHolder holder, XBaseDialog dialog) {
        holder.setText(R.id.tv_content, content);
    }
}
