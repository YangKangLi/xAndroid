package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XProgressDialog extends XBaseDialog {

    public static XProgressDialog newInstance() {


        XProgressDialog dialog = new XProgressDialog();
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_progress_layout;
    }

    @Override
    public void convertView(ViewHolder holder, XBaseDialog dialog) {

    }
}
