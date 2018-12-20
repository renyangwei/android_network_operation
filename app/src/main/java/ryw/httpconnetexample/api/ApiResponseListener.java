package ryw.httpconnetexample.api;

/**
 * http请求监听接口
 */
public interface ApiResponseListener {

    void onSuccess(String msg);
    void onFailed(String msg);

}
