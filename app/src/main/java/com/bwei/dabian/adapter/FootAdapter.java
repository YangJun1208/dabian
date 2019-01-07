package com.bwei.dabian.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.dabian.R;
import com.bwei.dabian.bean.FootBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootAdapter extends RecyclerView.Adapter<FootAdapter.ViewHolder> {

    private Context mContext;
    private List<FootBean.ResultBean> mData;

    public FootAdapter(Context mContext) {
        this.mContext = mContext;
        mData=new ArrayList<>();
    }

    public void setDatas(List<FootBean.ResultBean> data) {
        //this.mData = mData;
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addDatas(List<FootBean.ResultBean> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FootAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.foot_adapter,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FootAdapter.ViewHolder viewHolder, int i) {

        String masterPic = mData.get(i).getMasterPic();
        Glide.with(mContext).load(masterPic).into(viewHolder.imageView);
        viewHolder.textView_title.setText(mData.get(i).getCommodityName());
        viewHolder.textView_price.setText(mData.get(i).getPrice()+"");
        viewHolder.textView_count.setText("已浏览"+mData.get(i).getBrowseNum()+"次");

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                new java.util.Date(mData.get(i).getBrowseTime()));
        viewHolder.textView_time.setText(date);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.foot_image)
        ImageView imageView;
        @BindView(R.id.foot_title)
        TextView textView_title;
        @BindView(R.id.foot_price)
        TextView textView_price;
        @BindView(R.id.foot_count)
        TextView textView_count;
        @BindView(R.id.foot_time)
        TextView textView_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
