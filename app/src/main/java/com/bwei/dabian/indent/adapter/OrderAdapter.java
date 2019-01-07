package com.bwei.dabian.indent.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.dabian.R;
import com.bwei.dabian.indent.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<OrderBean.OrderListBean> mData;

    private Context mContext;
    private GoodsAdapter goodsAdapter;

    public OrderAdapter(Context mContext) {
        this.mContext = mContext;
        mData=new ArrayList();
    }

    public void setDsata(List<OrderBean.OrderListBean> data) {
        //this.mData = mData;
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addDsata(List<OrderBean.OrderListBean> data) {
        //this.mData = mData;
        //mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.indent_order,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.recyclerView_order.setLayoutManager(linearLayoutManager);
        goodsAdapter = new GoodsAdapter(mContext);
        viewHolder.recyclerView_order.setAdapter(goodsAdapter);
        viewHolder.textView_coding.setText(mData.get(i).getOrderId());
        viewHolder.textView_time.setText(mData.get(i).getOrderTime());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_ding)
        RecyclerView recyclerView_order;
        @BindView(R.id.intent_sum)
        TextView textView_sum;
        @BindView(R.id.intent_sum_price)
        TextView textView_price;
        @BindView(R.id.order_coding)
        TextView textView_coding;
        @BindView(R.id.order_time)
        TextView textView_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
