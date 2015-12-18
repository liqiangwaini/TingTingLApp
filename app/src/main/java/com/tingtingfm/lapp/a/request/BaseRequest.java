package com.tingtingfm.lapp.a.request;

import android.util.Log;

import com.tingtingfm.lapp.a.utils.AppUtils;
import com.tingtingfm.lapp.a.utils.DeviceUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2015/12/10.
 */
public class BaseRequest {
    private static String ENCODING = "UTF-8";

    private String client;
    private String version;
    private String url;

    public BaseRequest(String url) {
        this.url = url;
        client = DeviceUtils.getDeviceId();
        version = "android_" + AppUtils.getVersionName();
    }

    /**
     * 通过反射组装请求地址
     * @return 返回组装后的地址集合
     */
    private Map<String, String> secreteParams() {
        Map<String, String> params = new HashMap<String, String>();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                Object value = f.get(this);
                if (null != value && !"".equals(value)) {
                    params.put(f.getName(), value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
        for (Field f : superFields) {
            try {
                f.setAccessible(true);
                Object value = f.get(this);
                if (filterKey(f.getName()) && (null != value && !"".equals(value))) {
                    params.put(f.getName(), value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        params.put("api_sign", getSecreteToken(params));
        return params;
    }

    /**
     * 加密请求参数
     * @param maps 请求参数
     * @return 返回加密后的字符串
     */
    private String getSecreteToken(Map<String, String> maps) {
        String result = "";
        try {
            Map<String, String> posts = new HashMap<String, String>(maps);
            List<String> keys = new ArrayList<String>();
            for (String str : posts.keySet()) {
                keys.add(str);
            }
            Collections.sort(keys);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < keys.size(); i++) {
                sb.append(URLEncoder.encode(keys.get(i), ENCODING));
                sb.append("=");
                sb.append(URLEncoder.encode(posts.get(keys.get(i)), ENCODING));
                sb.append("&");
            }

            result = sb.toString().substring(0, sb.toString().length() - 1);
            result = dispatchStr(result);
            result += "_" + "bw(*ez$@]a.bokLi";
            Log.d("secrete key %1s", result + "/" + DeviceUtils.encryption(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return DeviceUtils.encryption(result);
    }

    /**
     * 对字符做处理，URLEncoder.encode对*不做转义，对空格做+号转义，*需要替换成%2A, +号需要替换成%20
     * Uri.encode *需要替换成%2A
     *
     * @param value
     * @return
     */
    private String dispatchStr(String value) {
        return value.replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    /**
     * 过滤请求参数
     * @param key 当前给定的参数
     * @return 当前给定的参数是否需要过滤 需要false 不需要true
     */
    private boolean filterKey(String key) {
        if (key.equals("ENCODING") || key.equals("url")) {
            return false;
        }

        return true;
    }

    /**
     * 返回最终的请求参数
     * @return
     */
    public String getRequestUrl() {
        Map<String, String> params = secreteParams();

        String paramStr;
        StringBuilder sb = new StringBuilder();
        for (String str : params.keySet()) {
            sb.append(str);
            sb.append("=");
            sb.append(params.get(str));
            sb.append("&");
        }


        paramStr = sb.toString().substring(0, sb.toString().length() - 1);
        return url + "?" + paramStr;
    }
}
