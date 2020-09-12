package com.github.yangkangli.x.mvvm.widgets.dialog.core;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

public class ViewHolder {

    /**
     * 根视图
     */
    private View rootView;

    /**
     * 子视图
     */
    private SparseArray<View> childViews;

    /**
     * 创建ViewHolder
     *
     * @param rootView
     * @return
     */
    public static ViewHolder create(View rootView) {
        return new ViewHolder(rootView);
    }

    /**
     * 私有构造方法
     *
     * @param rootView
     */
    private ViewHolder(View rootView) {
        this.rootView = rootView;
        this.childViews = new SparseArray<>();
    }


    /**
     * 通过ID获得View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = childViews.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            childViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, int textId) {
        TextView textView = getView(viewId);
        textView.setText(textId);
    }

    public void setText(int viewId, CharSequence text){
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener clickListener) {
        View view = getView(viewId);
        view.setOnClickListener(clickListener);
    }

    public void setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }

    public void setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
    }
}
