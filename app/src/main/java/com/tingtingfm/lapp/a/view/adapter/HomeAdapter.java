package com.tingtingfm.lapp.a.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tingtingfm.lapp.a.R;
import com.tingtingfm.lapp.a.bean.VodBean;
import com.tingtingfm.lapp.a.utils.ImageUtils;
import com.tingtingfm.lapp.a.utils.TimeUtils;
import com.tingtingfm.lapp.a.utils.TimeUtils.TimeFormat;
import com.tingtingfm.lapp.a.utils.TransferUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private WeakReference<Context> context;
    private List<VodBean> values;

    public HomeAdapter(Context context, List<VodBean> values) {
        this.context = new WeakReference<Context>(context);
        this.values = values;
    }

    public void setData(List<VodBean> values) {
        this.values = values;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context.get()).inflate(R.layout.main_listview_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (values == null) {
            return;
        }

        VodBean vodBean = values.get(position);
        holder.tvTitle.setText(vodBean.getTitle());
        holder.tvIntro.setText(vodBean.getRecommendation());
        holder.tvPlayCount.setText(TransferUtils.getNum(vodBean.getPlaytimes()));
        holder.tvUpdateTime.setText(TimeUtils.getTimeForFormatAndDate(TimeFormat.TimeFormat1, vodBean.getMtime()));
        ImageUtils.displayImage(vodBean.getImg(), holder.ivImage);
    }

    @Override
    public int getItemCount() {
        if (values == null) {
            return 0;
        }

        return values.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.home_listview_item_album)
        ImageView ivImage;
        @Bind(R.id.home_listview_item_title)
        TextView tvTitle;
        @Bind(R.id.home_listview_item_message)
        TextView tvIntro;
        @Bind(R.id.home_listview_item_play)
        TextView tvPlayCount;
        @Bind(R.id.home_listview_item_time)
        TextView tvUpdateTime;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}