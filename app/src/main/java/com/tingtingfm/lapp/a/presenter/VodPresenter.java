package com.tingtingfm.lapp.a.presenter;

import android.os.Handler;

import com.tingtingfm.lapp.a.bean.VodBean;
import com.tingtingfm.lapp.a.model.IVodItemsImpl;
import com.tingtingfm.lapp.a.model.IVodItemsModel;
import com.tingtingfm.lapp.a.view.IView.IVodView;

import java.util.List;

/**
 * Created by lenovo on 2015/12/17.
 */
public class VodPresenter {
    private IVodItemsModel iVodItemsModel;
    private IVodView vodView;
    private Handler handler = new Handler();

    public VodPresenter(IVodView vodView) {
        this.vodView = vodView;
        iVodItemsModel = new IVodItemsImpl();
    }

    public void excu() {
        vodView.showLoading();
        iVodItemsModel.execu(vodView.getRequestUrl(), new OnLoadingListener() {
            @Override
            public void success(final List<VodBean> items) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        vodView.hideLoading();
                        //TODO
                        vodView.setVodItems(items);
                    }
                });
            }

            @Override
            public void fail() {
                //TODO
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        vodView.showFailedError();
                    }
                });

            }
        });
    }
}
