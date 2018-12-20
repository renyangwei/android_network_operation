package ryw.httpconnetexample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import ryw.httpconnetexample.R;
import ryw.httpconnetexample.api.Api;
import ryw.httpconnetexample.api.ApiFileResponseListener;
import ryw.httpconnetexample.api.ApiResponseListener;
import ryw.httpconnetexample.utils.LogUtil;
import ryw.httpconnetexample.utils.ToastUtil;

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
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), msg);
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
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), msg);
            }
        });
    }

    /**
     * 原生上传文件
     * @param view  视图
     */
    public void onUploadFile(View view) {
        String fileName = "";
        try {
            fileName = getAssets().list("")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        Api.httpUploadFile(this, fileName, new ApiFileResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), msg);
            }

            @Override
            public void onProgress(String percent) {
                LogUtil.i(percent);
            }
        });
    }

    /**
     * volley Get
     * @param view  视图
     */
    public void onVolleyGet(View view) {
        Api.volleyGet(this, "ren", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), msg);
            }
        });

    }

    /**
     * Volley Post
     * @param view  视图
     */
    public void onVolleyPost(View view) {
        Api.volleyPost(this,"volleyPost", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), msg);
            }
        });
    }

  

    /**
     * Okhttp Get
     * @param view  视图
     */
    public void onOkhttpGet(View view) {
        Api.okhttpGet("okhttp get", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                ToastUtil.toast(getApplicationContext(), msg);
            }
        });
    }
}
