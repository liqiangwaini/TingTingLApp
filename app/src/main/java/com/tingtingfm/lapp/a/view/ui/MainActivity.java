package com.tingtingfm.lapp.a.view.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tingtingfm.lapp.a.R;
import com.tingtingfm.lapp.a.bean.VodBean;
import com.tingtingfm.lapp.a.presenter.VodPresenter;
import com.tingtingfm.lapp.a.request.VodRequest;
import com.tingtingfm.lapp.a.view.IView.IVodView;
import com.tingtingfm.lapp.a.view.adapter.HomeAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, IVodView {
    @Bind(R.id.home_rv)
    RecyclerView recyclerView;
    @Bind(R.id.home_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private HomeAdapter mAdapter;
    private List<VodBean> values;

    private VodPresenter vodPresenter = null;
    private VodRequest vodRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        vodPresenter = new VodPresenter(this);

        initView();
        vodRequest = new VodRequest("http://api1.tingtingfm.com/discover/get_discover_vod_list");
        onRefresh();
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new HomeAdapter(this, values));
    }

    @Override
    public void onRefresh() {
        vodRequest.setPage(1);
        vodPresenter.excu();
    }

    @Override
    public String getRequestUrl() {
        return vodRequest.getRequestUrl();
    }

    @Override
    public void setVodItems(List<VodBean> items) {
        System.out.println("MainActivity.setVodItems");
        values = items;
        mAdapter.setData(values);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "error...", Toast.LENGTH_SHORT).show();
    }
}
