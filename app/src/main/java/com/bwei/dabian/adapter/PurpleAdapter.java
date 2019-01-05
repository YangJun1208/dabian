package com.bwei.dabian.adapter;

import android.content.Context;
import android.media.Image;
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

public class PurpleAdapter extends RecyclerView.Adapter<PurpleAdapter.ViewHolder> {
    private Context context;
    private List<ShowBean.ResultBean.MlssBean.CommodityListBeanXX> mData;


    public PurpleAdapter(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setmData(List<ShowBean.ResultBean.MlssBean.CommodityListBeanXX> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurpleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.activity_purple,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PurpleAdapter.ViewHolder viewHolder, int i) {
        String masterPic = mData.get(i).getMasterPic();
        Glide.with(context).load(masterPic).into(viewHolder.imageView_purple);
        viewHolder.price_purple.setText("¥"+mData.get(i).getPrice());
        viewHolder.title_purple.setText(mData.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_purple)
        ImageView imageView_purple;
        @BindView(R.id.title_purple)
        TextView title_purple;
        @BindView(R.id.price_purple)
        TextView price_purple;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
