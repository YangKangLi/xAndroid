package com.github.yangkangli.x.mvvm.widgets.dialog;

import androidx.fragment.app.FragmentManager;

import com.github.yangkangli.x.mvvm.widgets.dialog.core.XBaseDialog;

import java.util.concurrent.ConcurrentLinkedQueue;

public class XDialogManager {

    //是否有dialog在展示
    private volatile boolean showing = false;

    // 对话框队列
    private ConcurrentLinkedQueue<XBaseDialog> dialogQueue;

    /**
     * 单例对象持有类
     */
    private static class SingletonHolder {
        private static final XDialogManager INSTANCE = new XDialogManager();
    }

    /**
     * 获得单例对象
     *
     * @return
     */
    public static XDialogManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 构造方法
     */
    private XDialogManager() {
        this.dialogQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * 请求显示对话框
     *
     * @param dialog
     * @param fm
     * @return
     */
    public boolean requestShow(XBaseDialog dialog, FragmentManager fm) {
        boolean b = dialogQueue.offer(dialog);
        checkAndDispatch(fm);
        return b;
    }

    /**
     * 检查是否要显示
     *
     * @param fm
     */
    private synchronized void checkAndDispatch(FragmentManager fm) {
        if (!showing) {
            showDialog(fm);
        }
    }

    /**
     * 显示对话框
     */
    private synchronized void showDialog(FragmentManager fm) {
        XBaseDialog dialog = dialogQueue.poll();
        if (dialog != null) {
            showing = true;
            dialog.show(fm);
        }
    }

    /**
     * 关闭对话框，并检查是否有下一个对话框要显示
     */
    public synchronized void dismissDialog(FragmentManager fm) {
        showing = false;
        checkAndDispatch(fm);
    }
}
