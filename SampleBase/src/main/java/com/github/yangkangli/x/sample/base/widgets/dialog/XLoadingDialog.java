package com.github.yangkangli.x.sample.base.widgets.dialog;

import androidx.fragment.app.FragmentManager;

import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.widgets.dialog.XDialogManager;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;
import com.github.yangkangli.x.sample.base.R;

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

    /**
     * 请求显示（使用XDialogManager管理）
     *
     * @param manager
     * @param fm
     */
    public XLoadingDialog requestShow(XDialogManager manager, FragmentManager fm) {
        manager.requestShow(this, fm);
        return this;
    }
}
