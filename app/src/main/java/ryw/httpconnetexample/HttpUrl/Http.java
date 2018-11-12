package ryw.httpconnetexample.HttpUrl;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Http {

    /**
     * Http Get
     * @param host  地址
     * @return  Http响应
     */
    public static String get(String host) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(host);
            connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuffer sb = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    sb = sb.append(line).append("\n");
                }
                String response = sb.toString();
                Log.i(Api.TAG, "http get response=" + response);
                return response;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                Log.i(Api.TAG, "disconnect connection");
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * Http Post
     * @param url           地址
     * @param body          请求主体
     */
    public static String postUrlencoded(String url, String body) {
        HttpURLConnection connection = null;
        try {
            URL mUrl = new URL(url);
            connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod("POST");//设置方法
            connection.setDoOutput(true);//设置可输出
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//如果是JSON则填application/json
            connection.setChunkedStreamingMode(0);
            OutputStream out = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(out, "UTF-8"));//封装输出流
            writer.write(body);
            writer.flush();
            writer.close();
            out.close();
            //获得响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuffer sb = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    sb = sb.append(line).append("\n");
                }
                String response = sb.toString();
                Log.i(Api.TAG, "http post response=" + response);
                return response;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

}
