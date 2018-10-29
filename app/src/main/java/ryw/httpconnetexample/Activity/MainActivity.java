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
     * 原生连接
     * @param view
     */
    public void onConnectHttpUrl(View view) {
        Api.connectBaidu(new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                Log.i(Api.TAG, msg);
                Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(String msg) {
                Log.i(Api.TAG, msg);
                Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * volley连接
     * @param view
     */
    public void onConnectVolley(View view) {
    }

    /**
     * OkHttp连接
     * @param view
     */
    public void onConnectOkHttp(View view) {
    }
}
