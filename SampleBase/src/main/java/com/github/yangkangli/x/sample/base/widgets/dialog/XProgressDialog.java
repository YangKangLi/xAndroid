package com.github.yangkangli.x.sample.base.widgets.dialog;

import androidx.fragment.app.FragmentManager;

import com.github.yangkangli.x.mvvm.R;
import com.github.yangkangli.x.mvvm.utils.ContextUtils;
import com.github.yangkangli.x.mvvm.widgets.dialog.XDialogManager;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.ViewHolder;
import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

public class XProgressDialog extends XBaseDialog {

    private String title;
    private String content;
    private String cancelText;
    private OnClickListener onClickListener;

    public static XProgressDialog init() {
        return new XProgressDialog();
    }

    public XProgressDialog() {
        this.title = ContextUtils.getApplication().getString(R.string.dialog_default_title);
        this.cancelText = ContextUtils.getApplication().getString(R.string.dialog_default_negative_text);
    }

    @Override
    protected int intLayoutId() {
        return R.layout.dialog_progress_layout;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public XProgressDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置内容
     *
     * @param content
     * @return
     */
    public XProgressDialog setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @param cancelText
     * @param onClickListener
     * @return
     */
    public XProgressDialog setCancel(String cancelText, XBaseDialog.OnClickListener onClickListener) {
        this.cancelText = cancelText;
        this.onClickListener = onClickListener;
        return this;
    }

    @Override
    public void convertView(ViewHolder holder, XBaseDialog dialog) {

    }

    /**
     * 请求显示（使用XDialogManager管理）
     *
     * @param manager
     * @param fm
     */
    public XProgressDialog requestShow(XDialogManager manager, FragmentManager fm) {
        manager.requestShow(this, fm);
        return this;
    }
}
