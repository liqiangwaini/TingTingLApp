package com.tingtingfm.lapp.a.presenter;

import com.tingtingfm.lapp.a.bean.VodBean;

import java.util.List;

/**
 * Created by lenovo on 2015/12/17.
 */
public interface OnLoadingListener {
    void success(List<VodBean> items);

    void fail();
}
