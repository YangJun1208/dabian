package com.bwei.dabian.shopping.adapter;

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
import com.bwei.dabian.shopping.bean.ShopBean;
import com.bwei.dabian.shopping.customview.CustomView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private List<ShopBean.ResultBean> mData;
    private Context mContext;

    public ShopAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    public void setDatass(List<ShopBean.ResultBean> mData) {
        this.mData = mData;

    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.goods_shop,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder viewHolder, int i) {
        String pic = mData.get(i).getPic();
        Glide.with(mContext).load(pic).into(viewHolder.imageView_shop);
        viewHolder.textView_title.setText(mData.get(i).getCommodityName());
        viewHolder.textView_price.setText(mData.get(i).getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_image)
        ImageView imageView_shop;
        @BindView(R.id.shop_title)
        TextView textView_title;
        @BindView(R.id.shop_price)
        TextView textView_price;
        @BindView(R.id.shop_custom_num)
        CustomView customViewNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
