package com.bwei.dabian.activity;

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
import com.bwei.dabian.adapter.HotAdapter;
import com.bwei.dabian.bean.HotItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyHotMore extends RecyclerView.Adapter<MyHotMore.ViewHolder> {

    private Context context;
    private List<HotItemBean.ResultBean> mData;

    public MyHotMore(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setDatas(List<HotItemBean.ResultBean> data) {
        //this.mData = mData;
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addDatas(List<HotItemBean.ResultBean> data) {
        //this.mData = mData;
        //mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.activity_hot_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String masterPic = mData.get(i).getMasterPic();
        Glide.with(context).load(masterPic).into(viewHolder.imageView);
        viewHolder.textView_price.setText("¥"+mData.get(i).getPrice()+"");
        viewHolder.textView_title.setText(mData.get(i).getCommodityName());
        viewHolder.seller_item_hot.setText("已售"+mData.get(i).getSaleNum()+"件");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hot_pager_item_childener)
        ImageView imageView;
        @BindView(R.id.hot_text_title_childener)
        TextView textView_title;
        @BindView(R.id.hot_text_price_childener)
        TextView textView_price;
        @BindView(R.id.seller_item_hot)
        TextView seller_item_hot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
