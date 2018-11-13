package ryw.httpconnetexample.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ryw.httpconnetexample.log.LogUtil;

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
                LogUtil.i("http get response=" + response);
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
                LogUtil.i("disconnect connection");
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
                LogUtil.i("http post response=" + response);
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

    /**
     * 上传文件
     * @param mUrl 地址
     * @param fileName  文件路径
     */
    public static String uploadFile(String mUrl, String fileName, InputStream inputStream) {
        HttpURLConnection conn = null;
        /// boundary就是request头和上传文件内容的分隔符(可自定义任意一组字符串)
        String BOUNDARY = "******";
        // 用来标识payLoad+文件流的起始位置和终止位置(相当于一个协议,告诉你从哪开始,从哪结束)
        String  preFix = ("\r\n--" + BOUNDARY + "--\r\n");
        try {
            URL url = new URL(mUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            // 设置header
            conn.setRequestProperty("Accept","*/*");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 获取写输入流
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            LogUtil.i("fileName=" + fileName);
            // 要上传的数据
            StringBuffer strBuf = new StringBuffer();
            // 标识payLoad + 文件流的起始位置
            strBuf.append(preFix);
            // 下面这三行代码,用来标识服务器表单接收文件的name和filename的格式
            // 在这里,我们是file和filename.后缀[后缀是必须的]。
            // 这里的fileName必须加个.jpg,因为后台会判断这个东西。
            // 这里的Content-Type的类型,必须与fileName的后缀一致。
            // 如果不太明白,可以问一下后台同事,反正这里的name和fileName得与后台协定！
            // 这里只要把.jpg改成.txt，把Content-Type改成上传文本的类型，就能上传txt文件了。
            strBuf.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(fileName).append("\"\r\n");
            strBuf.append("Content-Type: image/png"  + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            // 获取文件流
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            // 每次上传文件的大小(文件会被拆成几份上传)
            int bytes;
            // 计算上传进度
            float count = 0;
            // 获取文件总大小
            int fileSize = dataInputStream.available();
            // 每次上传的大小
            byte[] bufferOut = new byte[1024];
            // 上传文件
            while ((bytes = inputStream.read(bufferOut)) != -1) {
                // 上传文件(一份)
                out.write(bufferOut, 0, bytes);
                // 计算当前已上传的大小
                count += bytes;
                // 打印上传文件进度(已上传除以总大*100就是进度)
                LogUtil.i("progress:" +(count / fileSize * 100) +"%");
            }
            // 关闭文件流
            inputStream.close();
            // 标识payLoad + 文件流的结尾位置
            out.write(preFix.getBytes());
            // 至此上传代码完毕
            // 总结上传数据的流程：preFix + payLoad(标识服务器表单接收文件的格式) + 文件(以流的形式) + preFix
            // 文本与图片的不同,仅仅只在payLoad那一处的后缀的不同而已。
            // 输出所有数据到服务器
            out.flush();
            // 关闭网络输出流
            out.close();
            // 重新构造一个StringBuffer,用来存放从服务器获取到的数据
            strBuf = new StringBuffer();
            // 打开输入流 , 读取服务器返回的数据
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
            String line;
            // 一行一行的读取服务器返回的数据
            while ((line = reader.readLine()) != null) {
                strBuf.append(line).append("\n");
            }
            // 关闭输入流
            reader.close();
            // 打印服务器返回的数据
            String response = strBuf.toString();
            LogUtil.i("上传成功:" + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return "";
    }

}
