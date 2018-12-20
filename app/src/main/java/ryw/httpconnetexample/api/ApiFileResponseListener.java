package ryw.httpconnetexample.api;

/**
 * 上传文件的监听
 */
public interface ApiFileResponseListener {

    void onSuccess(String msg);
    void onFailed(String msg);
    void onProgress(String percent);//百分比
}
