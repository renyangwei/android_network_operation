package ryw.httpconnetexample.HttpUrl;

/**
 * http请求监听接口
 */
public interface ApiResponse {

    void onSuccess(String msg);
    void onFailed(String msg);

}
