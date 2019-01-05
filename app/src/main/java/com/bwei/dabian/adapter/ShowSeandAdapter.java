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
import com.bwei.dabian.bean.ShowZhanBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowSeandAdapter extends RecyclerView.Adapter<ShowSeandAdapter.ViewHolder> {

    private List<ShowZhanBean.ResultBean> mDatas;
    private Context mContext;

    public ShowSeandAdapter(Context mContext) {
        this.mContext = mContext;
        mDatas=new ArrayList<>();
    }

    public void setDatas(List<ShowZhanBean.ResultBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDatas(List<ShowZhanBean.ResultBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowSeandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.activity_hot_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShowSeandAdapter.ViewHolder viewHolder, int i) {
        String masterPic = mDatas.get(i).getMasterPic();
        Glide.with(mContext).load(masterPic).into(viewHolder.imageView);
        viewHolder.textView_price.setText("¥"+mDatas.get(i).getPrice()+"");
        viewHolder.textView_title.setText(mDatas.get(i).getCommodityName());
        viewHolder.seller_item_hot.setText("已售"+mDatas.get(i).getSaleNum()+"件");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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
