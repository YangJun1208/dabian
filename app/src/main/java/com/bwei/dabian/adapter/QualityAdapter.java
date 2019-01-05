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
import com.bwei.dabian.bean.ShowBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QualityAdapter extends RecyclerView.Adapter<QualityAdapter.ViewHolder> {

    private Context context;
    private List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> mData;

    public QualityAdapter(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setDatas(List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_quality, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QualityAdapter.ViewHolder viewHolder, int i) {
        String masterPic = mData.get(i).getMasterPic();
        Glide.with(context).load(masterPic).into(viewHolder.quailty_image);
        viewHolder.quailty_price.setText("¥"+mData.get(i).getPrice()+"");
        viewHolder.quailty_title.setText(mData.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.quality_image_time)
        ImageView quailty_image;
        @BindView(R.id.quality_title_item)
        TextView quailty_title;
        @BindView(R.id.quality_price_item)
        TextView quailty_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
