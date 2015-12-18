package com.tingtingfm.lapp.a.view.IView;

import com.tingtingfm.lapp.a.bean.VodBean;

import java.util.List;

/**
 * Created by lenovo on 2015/12/17.
 */
public interface IVodView {
    String getRequestUrl();

    void setVodItems(List<VodBean> items);

    void showLoading();

    void hideLoading();

    void showFailedError();
}
