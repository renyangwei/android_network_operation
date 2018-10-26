package ryw.httpconnetexample.HttpUrl;

public class Api {

    public static void connectBaidu(ApiResponse response) {
        ConnectTask connectTask = new ConnectTask();
        connectTask.execute("https://www.baidu.com");
    }
}
