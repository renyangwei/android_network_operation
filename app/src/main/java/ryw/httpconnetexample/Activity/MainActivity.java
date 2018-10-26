package ryw.httpconnetexample.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
