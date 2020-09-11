package com.github.yangkangli.x.mvvm.http;


import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.io.File;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HttpManager {

    // 单例对象持有类
    private static final class SingletonHolder {
        private static final HttpManager INSTANCE = new HttpManager();
    }

    // 获得单例对象
    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private HttpManager() {

    }

    /**
     * Http的Get请求
     *
     * @param url
     * @param queryParams
     * @param clazz
     * @param callback
     * @param <T>
     * @return
     */
    public <T> Disposable get(String url, Map<String, String> queryParams, Class<T> clazz, final HttpCallback<T> callback, final String requesttag) {
        return Rx2AndroidNetworking
                .get(url)
                .addQueryParameter(queryParams)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(clazz)
                .subscribeOn(Schedulers.computation())
                .map(new Function<T, T>() {
                    @Override
                    public T apply(T result) throws Exception {
                        if (callback != null) {
                            return callback.doInAsync(requesttag, result);
                        }
                        return result;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(requesttag);
                        }
                    }
                })
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T result) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(requesttag, result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback != null) {
                            callback.onError(requesttag, throwable);
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (callback != null) {
                            callback.onComplete(requesttag);
                        }
                    }
                });
    }

    /**
     * Http的Post请求
     *
     * @param url
     * @param queryParams
     * @param bodyParams
     * @param clazz
     * @param callback
     * @param <T>
     * @return
     */
    public <T> Disposable post(String url, Map<String, String> queryParams, Map<String, String> bodyParams, Class<T> clazz, final HttpCallback<T> callback, final String requestTag) {
        return Rx2AndroidNetworking
                .post(url)
                .addQueryParameter(queryParams)
                .addBodyParameter(bodyParams)
                .setPriority(Priority.MEDIUM)
                .build()
                .getObjectObservable(clazz)
                .subscribeOn(Schedulers.computation())
                .map(new Function<T, T>() {
                    @Override
                    public T apply(T result) throws Exception {
                        if (callback != null) {
                            return callback.doInAsync(requestTag, result);
                        }
                        return result;
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(requestTag);
                        }
                    }
                })
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T result) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(requestTag, result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback != null) {
                            callback.onError(requestTag, throwable);
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (callback != null) {
                            callback.onComplete(requestTag);
                        }
                    }
                });
    }

    /**
     * 下载文件
     *
     * @param url
     * @param dirPath
     * @param fileName
     * @param callback
     * @return
     */
    public Disposable download(String url, String dirPath, String fileName, final HttpCallback<File> callback, final String requestTag) {
        return Rx2AndroidNetworking
                .download(url, dirPath, fileName)
                .setPriority(Priority.MEDIUM)
                .build()
                .setDownloadProgressListener(new DownloadProgressListener() {
                    @Override
                    public void onProgress(long finishedBytes, long totalBytes) {
                        if (callback != null) {
                            callback.onProgress(requestTag, finishedBytes, totalBytes);
                        }
                    }
                })
                .getDownloadObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(requestTag);
                        }
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(requestTag, new File(s));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback != null) {
                            callback.onError(requestTag, throwable);
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (callback != null) {
                            callback.onComplete(requestTag);
                        }
                    }
                });
    }

    /**
     * 上传文件
     *
     * @param url
     * @param fileKey
     * @param file
     * @param multiParams
     * @param clazz
     * @param callback
     * @param <T>
     * @return
     */
    public <T> Disposable upload(String url, String fileKey, File file, Map<String, String> multiParams, Class<T> clazz, final HttpCallback<T> callback, final String requestTag) {
        return Rx2AndroidNetworking
                .upload(url)
                .addMultipartFile(fileKey, file)
                .addMultipartParameter(multiParams)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        if (callback != null) {
                            callback.onProgress(requestTag, bytesUploaded, totalBytes);
                        }
                    }
                })
                .getObjectObservable(clazz)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (callback != null) {
                            callback.onStart(requestTag);
                        }
                    }
                })
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (callback != null) {
                            callback.onSuccess(requestTag, t);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback != null) {
                            callback.onError(requestTag, throwable);
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (callback != null) {
                            callback.onComplete(requestTag);
                        }
                    }
                });
    }
}
