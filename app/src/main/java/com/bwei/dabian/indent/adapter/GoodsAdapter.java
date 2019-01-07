package com.bwei.dabian.indent.adapter;

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
import com.bwei.dabian.indent.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    private Context mContext;
    private List<OrderBean.OrderListBean.DetailListBean> mData;

    public GoodsAdapter(Context mContext) {
        this.mContext = mContext;
        mData=new ArrayList<>();
    }

    public void setaData(List<OrderBean.OrderListBean.DetailListBean> data) {
        //this.mData = mData;
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addaData(List<OrderBean.OrderListBean.DetailListBean> data) {
        //this.mData = mData;
       // mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.goods_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdapter.ViewHolder viewHolder, int i) {
        String pic = mData.get(i).getCommodityPic();
        Glide.with(mContext).load(pic).into(viewHolder.imageView_goods);
        viewHolder.textView_title.setText(mData.get(i).getCommodityName());
        viewHolder.textView_price.setText(mData.get(i).getCommodityPrice()+"");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_imag)
        ImageView imageView_goods;
        @BindView(R.id.goods_title)
        TextView textView_title;
        @BindView(R.id.goods_price)
        TextView textView_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
