package com.github.yangkangli.x.mvvm.http;

public abstract class HttpCallback<T> {

    /**
     * 请求开始
     */
    public void onStart() {
        // 子类具体实现
    }

    /**
     * 当需要在返回结果后做耗时的异步操作是，可以实现或者重写该方法
     *
     * @param result
     */
    public T doInAsync(T result) {
        return result;
    }

    /**
     * 上传或下载 进度变化回调方法
     *
     * @param finishedBytes
     * @param totalBytes
     */
    public void onProgress(long finishedBytes, long totalBytes) {

    }

    /**
     * 成功回调方法
     *
     * @param result
     */
    public abstract void onSuccess(T result);

    /**
     * 服务器返回错误码
     *
     * @param failureResult
     */
    public void onFailure(T failureResult) {
        // 子类具体实现
    }

    /**
     * 失败（超时，404等错误）
     *
     * @param throwable
     */
    public void onError(Throwable throwable) {
        // 子类具体实现
    }

    /**
     * 请求完成
     */
    public void onComplete() {
        // 子类具体实现
    }


}
