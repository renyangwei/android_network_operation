package ryw.httpconnetexample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import ryw.httpconnetexample.R;
import ryw.httpconnetexample.log.LogUtil;
import ryw.httpconnetexample.net.Api;
import ryw.httpconnetexample.net.ApiFileResponseListener;
import ryw.httpconnetexample.net.ApiResponseListener;

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
        Api.getInstance(this).httpGet("Android Http Get", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                toast(R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                toast(msg);
            }
        });
    }

    /**
     * 原生Post
     * @param view  视图
     */
    public void onHttpPost(View view) {
        Api.getInstance(this).httpPost("Android Http Post", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                toast(R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                toast(msg);
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
        Api.getInstance(this).httpUploadFile(fileName, new ApiFileResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                toast(R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                toast(msg);
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
        Api.getInstance(this).volleyGet("ren", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                toast(R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                toast(msg);
            }
        });

    }

    /**
     * Volley Post
     * @param view  视图
     */
    public void onVolleyPost(View view) {
        Api.getInstance(this).volleyPost("volleyPost", new ApiResponseListener() {
            @Override
            public void onSuccess(String msg) {
                LogUtil.i(msg);
                toast(R.string.response_success);
            }

            @Override
            public void onFailed(String msg) {
                LogUtil.i(msg);
                toast(msg);
            }
        });
    }

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void toast(int id) {
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
    }
}
