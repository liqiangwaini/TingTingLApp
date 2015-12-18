package com.tingtingfm.lapp.a.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tingtingfm.lapp.a.bean.VodBean;
import com.tingtingfm.lapp.a.presenter.OnLoadingListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2015/12/17.
 */
public class IVodItemsImpl implements IVodItemsModel {
    @Override
    public void execu(final String url, final OnLoadingListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();

                Request request = new Request.Builder().url(url).build();

                try {
                    Response response = okHttpClient.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        System.out.println("body = " + body);

                        JSONObject object = new JSONObject(body);
                        body = object.getString("data");

                        System.out.println("body = " + body);

                        List<VodBean> values = new Gson().fromJson(body, new TypeToken<List<VodBean>>(){}.getType());
                        if (null != listener) {
                            listener.success(values);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
