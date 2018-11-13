package ryw.httpconnetexample.net;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import ryw.httpconnetexample.log.LogUtil;

public class HttpUtil {

    /**
     * 获取url地址或者参数
     * @param urlPath   地址，可以为""
     * @param params    参数
     * @return          发起请求的参数
     */
    public static String buildUrlOrParams(String urlPath, Map<String, Object> params) {
        if (urlPath == null || params == null || params.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            if (params.get(key) == null) {
                continue;
            }
            sb = sb.append(key).append("=").append(URLEncoder.encode(params.get(key).toString())).append("&");
        }
        String urlParams = sb.substring(0, sb.length() - 1);
        if (urlPath.length() == 0) {
            LogUtil.i("buildUrlOrParams = " + urlParams);
            return urlParams;
        } else {
            if (!urlPath.endsWith("?")) {
                urlPath += "?";
            }
            String urlString = urlPath + urlParams;
            LogUtil.i("buildUrlOrParams = " + urlString);
            return urlString;
        }
    }

}
