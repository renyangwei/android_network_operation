package ryw.httpconnetexample.HttpUrl;

import android.os.AsyncTask;

/**
 * 实现异步操作类AsyncTask
 * URL：传入的参数
 * Integer: 进度
 * Long：返回值
 */
public class ConnectTask extends AsyncTask<String, Integer, Long> {

    @Override
    protected Long doInBackground(String... urls) {
        return null;
    }

    //执行完毕
    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

    //执行的进度
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
