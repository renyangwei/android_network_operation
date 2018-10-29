package ryw.httpconnetexample.HttpUrl;

import android.os.AsyncTask;
import android.text.TextUtils;

public class Api {

    public static final String TAG = "Example";
    private static final String baidu = "http://www.baidu.com";

    public static void connectBaidu(final ApiResponseListener listener) {
        /**
         * 实现异步操作类AsyncTask
         * 参数一：传入的参数
         * 参数二：进度
         * 参数三：返回值
         */
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPostExecute(String s) {
                if (TextUtils.isEmpty(s)) {
                    listener.onFailed("get failed");
                } else {
                    listener.onSuccess(s);
                }
             }

            @Override
            protected String doInBackground(Void... voids) {
                return Http.get(baidu);
            }
        }.execute();
    }
}
