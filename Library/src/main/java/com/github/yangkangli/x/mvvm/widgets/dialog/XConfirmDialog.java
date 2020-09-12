package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XConfirmDialog extends XBaseDialog {

    private String title;
    private String content;
    private String positiveText;
    private OnClickListener positiveListener;
    private String negativeText;
    private OnClickListener negativeListener;


    public static XConfirmDialog init() {
        return new XConfirmDialog();
    }

    public XConfirmDialog() {
        this.title = ContextUtils.getApplication().getString(R.string.dialog_default_title);
        this.positiveText = ContextUtils.getApplication().getString(R.string.dialog_default_positive_text);
        this.negativeText = ContextUtils.getApplication().getString(R.string.dialog_default_negative_text);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_confirm_layout;
    }


    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public XConfirmDialog setTitle(String title) {
        this.title = title;
        return this;
    }


    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public XConfirmDialog setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 设置确认按钮
     *
     * @param positiveText
     * @param onClickListener
     * @return
     */
    public XConfirmDialog setPositive(String positiveText, XBaseDialog.OnClickListener onClickListener) {
        this.positiveText = positiveText;
        this.positiveListener = onClickListener;
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @param negativeText
     * @param onClickListener
     * @return
     */
    public XConfirmDialog setNegative(String negativeText, XBaseDialog.OnClickListener onClickListener) {
        this.negativeText = negativeText;
        this.negativeListener = onClickListener;
        return this;
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
                } else {
                    dismiss();
                }
            }
        });

        holder.setOnClickListener(R.id.btn_negative, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (negativeListener != null) {
                    negativeListener.onClick(XConfirmDialog.this, v.getId());
                } else {
                    dismiss();
                }
            }
        });
    }
}
