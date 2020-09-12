package com.github.yangkangli.x.mvvm.widgets.dialog;

import android.view.View;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XAlertDialog extends XBaseDialog {

    private String title;
    private String content;
    private String positiveText;
    private OnClickListener onClickListener;


    public static XAlertDialog init() {
        return new XAlertDialog();
    }

    public XAlertDialog() {
        this.title = ContextUtils.getApplication().getString(R.string.dialog_default_title);
        this.positiveText = ContextUtils.getApplication().getString(R.string.dialog_default_positive_text);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_alert_layout;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public XAlertDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public XAlertDialog setContent(String content) {
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
    public XAlertDialog setPositive(String positiveText, XBaseDialog.OnClickListener onClickListener) {
        this.positiveText = positiveText;
        this.onClickListener = onClickListener;
        return this;
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
                } else {
                    dismiss();
                }
            }
        });
    }
}
