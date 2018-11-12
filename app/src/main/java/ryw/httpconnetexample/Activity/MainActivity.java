package ryw.httpconnetexample.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import ryw.httpconnetexample.HttpUrl.Api;
import ryw.httpconnetexample.HttpUrl.ApiResponseListener;
import ryw.httpconnetexample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 原生Get
     * @param view  视图
     */
    public void onHttpGet(View view) {
        Api.httpGet("Android Http Get", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                Log.i(Api.TAG, msg);
                toast(R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                Log.i(Api.TAG, msg);
                toast(msg);
            }
        });
    }

    /**
     * 原生Post
     * @param view  视图
     */
    public void onHttpPost(View view) {
        Api.httpPost("Android Http Post", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                Log.i(Api.TAG, msg);
                toast(R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                Log.i(Api.TAG, msg);
                toast(msg);
            }
        });
    }

    /**
     * volley连接
     * @param view  视图
     */
    public void onConnectVolley(View view) {
    }

    /**
     * OkHttp连接
     * @param view  视图
     */
    public void onConnectOkHttp(View view) {
    }

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void toast(int id) {
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
    }
}
