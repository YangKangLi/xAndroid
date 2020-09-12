package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XAlertDialog extends XBaseDialog {

    private static final String ARGS_TITLE = "args_title";
    private static final String ARGS_CONTENT = "args_content";
    private static final String ARGS_POSITIVE_TEXT = "args_positive_text";

    private String title;
    private String content;
    private String positiveText;

    private OnClickListener onClickListener;


    public static XAlertDialog newInstance(String title, String content, String positiveText) {
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        args.putString(ARGS_CONTENT, content);
        args.putString(ARGS_POSITIVE_TEXT, positiveText);
        XAlertDialog dialog = new XAlertDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(ARGS_TITLE);
        content = getArguments().getString(ARGS_CONTENT);
        positiveText = getArguments().getString(ARGS_POSITIVE_TEXT);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_alert_layout;
    }

    @Override
    public void convertView(ViewHolder holder, XBaseDialog dialog) {
        holder.setText(R.id.tv_title, title);
        holder.setText(R.id.tv_content, content);
        holder.setText(R.id.btn_positive, positiveText);

        holder.setOnClickListener(R.id.btn_positive, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(XAlertDialog.this, v.getId());
                }
            }
        });
    }

    public XAlertDialog setPositiveListener(XBaseDialog.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }
}
