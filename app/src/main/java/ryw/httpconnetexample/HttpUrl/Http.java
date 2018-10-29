package ryw.httpconnetexample.HttpUrl;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Http {

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

    public static void post(String url, String body) {

    }

}
