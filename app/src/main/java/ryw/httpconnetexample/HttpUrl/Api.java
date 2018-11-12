package ryw.httpconnetexample.HttpUrl;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class Api {

    public static final String TAG = "Example";
    private static final String BAIDU = "http://172.17.100.2:8080";//夜神模拟器访问本机的地址映射

    /**
     * 原生Get
     * @param name      参数Name
     * @param listener  回调监听
     */
    public static void httpGet(String name, final ApiResponseListener listener) {
        //组装参数name
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        final String baiduUrl = HttpUtil.buildUrlOrParams(BAIDU, map);
        /**
         * 实现异步操作类AsyncTask
         * 参数一：传入的参数
         * 参数二：进度
         * 参数三：返回值
         */
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPostExecute(String s) {
                if (TextUtils.isEmpty(s)) {
                    listener.onFailed("get failed");
                } else {
                    listener.onSuccess(s);
                }
             }

            @Override
            protected String doInBackground(String... strings) {
                return Http.get(strings[0]);
            }
        }.execute(baiduUrl);
    }

    /**
     * 原生Post
     * @param name      参数Name
     * @param listener  回调监听
     */
    public static void httpPost(String name, final ApiResponseListener listener) {
        //组装参数name
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        final String body = HttpUtil.buildUrlOrParams("", map);
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                return Http.postUrlencoded(BAIDU, strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                if (TextUtils.isEmpty(s)) {
                    listener.onFailed("post failed");
                } else {
                    listener.onSuccess(s);
                }
            }
        }.execute(body);
    }

}
