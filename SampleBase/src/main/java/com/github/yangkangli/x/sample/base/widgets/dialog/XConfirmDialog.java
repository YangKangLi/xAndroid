package com.github.yangkangli.x.sample.base.widgets.dialog;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.widgets.dialog.XDialogManager;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;
import com.github.yangkangli.x.sample.base.R;

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
     * @return
     */
    public XConfirmDialog setPositive(String positiveText) {
        return setPositive(positiveText, null);
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
     * @return
     */
    public XConfirmDialog setNegative(String negativeText) {
        return setNegative(negativeText, null);
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

    /**
     * 请求显示（使用XDialogManager管理）
     *
     * @param manager
     * @param fm
     */
    public XConfirmDialog requestShow(XDialogManager manager, FragmentManager fm) {
        manager.requestShow(this, fm);
        return this;
    }
}
