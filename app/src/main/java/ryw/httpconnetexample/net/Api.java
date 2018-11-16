package ryw.httpconnetexample.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import ryw.httpconnetexample.volley.VolleySingleton;

/**
 * API类，给Activity提供接口
 */
public class Api {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static final String LOCALHOST = "http://172.17.100.2:8082";//夜神模拟器访问本机的地址映射

    @SuppressLint("StaticFieldLeak")
    private static Api instance;

    private Api() {}

    public static Api getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    /**
     * 原生Get
     * @param name      参数Name
     * @param listener  回调监听
     */
    @SuppressLint("StaticFieldLeak")
    public void httpGet(String name, final ApiResponseListener listener) {
        //组装参数name
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        final String baiduUrl = HttpUtil.buildUrlOrParams(LOCALHOST, map);
        /*
          实现异步操作类AsyncTask
          参数一：传入的参数
          参数二：进度
          参数三：返回值
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
                return Http.getInstance().get(strings[0]);
            }
        }.execute(baiduUrl);
    }

    /**
     * 原生Post
     * @param name      参数Name
     * @param listener  回调监听
     */
    @SuppressLint("StaticFieldLeak")
    public void httpPost(String name, final ApiResponseListener listener) {
        //组装参数name
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        final String body = HttpUtil.buildUrlOrParams("", map);
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                return Http.getInstance().postUrlencoded(LOCALHOST, strings[0]);
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

    /**
     * 上传文件
     * @param fileName  文件路径
     * @param listener  回调监听
     */
    @SuppressLint("StaticFieldLeak")
    public void httpUploadFile(String fileName, final ApiFileResponseListener listener) {
        InputStream inputStream = null;
        try {
            inputStream = mContext.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new AsyncTask<Object, String, String>() {

            @Override
            protected void onProgressUpdate(String... values) {
                //后面想想怎么实现比较好
                listener.onProgress(values[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                if (TextUtils.isEmpty(s)) {
                    listener.onFailed("upload file failed");
                } else {
                    listener.onSuccess(s);
                }
            }

            @Override
            protected String doInBackground(Object... objects) {
                publishProgress("100%");
                return Http.getInstance().uploadFile(LOCALHOST, (String)objects[0], (InputStream)objects[1]);
            }
        }.execute(fileName, inputStream);
    }

    /**
     * Voley Get
     * @param name      参数
     * @param listener  回调监听
     */
    public void volleyGet(String name, final ApiResponseListener listener) {
        //组装参数name
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        final String baiduUrl = HttpUtil.buildUrlOrParams(LOCALHOST, map);
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest request = new StringRequest(Request.Method.GET, baiduUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error.getMessage());
            }
        });
        queue.add(request);
    }

    /**
     * Volley Post
     * @param name      参数
     * @param listener  回调
     */
    public void volleyPost(String name, final ApiResponseListener listener) {
        //组装参数name
        final Map<String, String> map = new HashMap<>();
        map.put("name", name);
        StringRequest request = new StringRequest(Request.Method.POST, LOCALHOST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                return map;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(request);
    }

}
