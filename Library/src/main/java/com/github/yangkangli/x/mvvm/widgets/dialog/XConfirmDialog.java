package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XConfirmDialog extends XBaseDialog {

    private static final String ARGS_TITLE = "args_title";
    private static final String ARGS_CONTENT = "args_content";
    private static final String ARGS_POSITIVE_TEXT = "args_positive_text";
    private static final String ARGS_NEGATIVE_TEXT = "args_negative_text";

    private String title;
    private String content;
    private String positiveText;
    private String negativeText;

    private OnClickListener positiveListener;
    private OnClickListener negativeListener;


    public static XConfirmDialog newInstance(String title, String content, String positiveText, String negativeText) {
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        args.putString(ARGS_CONTENT, content);
        args.putString(ARGS_POSITIVE_TEXT, positiveText);
        args.putString(ARGS_NEGATIVE_TEXT, negativeText);
        XConfirmDialog dialog = new XConfirmDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(ARGS_TITLE);
        content = getArguments().getString(ARGS_CONTENT);
        positiveText = getArguments().getString(ARGS_POSITIVE_TEXT);
        negativeText = getArguments().getString(ARGS_NEGATIVE_TEXT);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_confirm_layout;
    }

    @Override
    public void convertView(ViewHolder holder, XBaseDialog dialog) {
        holder.setText(R.id.tv_title, title);
        holder.setText(R.id.tv_content, content);
        holder.setText(R.id.btn_positive, positiveText);
        holder.setText(R.id.btn_negative, negativeText);

        holder.setOnClickListener(R.id.btn_positive, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positiveListener != null) {
                    positiveListener.onClick(XConfirmDialog.this, v.getId());
                }
            }
        });

        holder.setOnClickListener(R.id.btn_negative, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (negativeListener != null) {
                    negativeListener.onClick(XConfirmDialog.this, v.getId());
                }
            }
        });
    }

    public XConfirmDialog setPositiveListener(OnClickListener onClickListener) {
        this.positiveListener = onClickListener;
        return this;
    }

    public XConfirmDialog setNegativeListener(OnClickListener onClickListener) {
        this.negativeListener = onClickListener;
        return this;
    }
}
