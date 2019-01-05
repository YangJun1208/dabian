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

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    private Context context;
    private List<ShowBean.ResultBean.RxxpBean.CommodityListBean> mData;

    public HotAdapter(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setDatas(List<ShowBean.ResultBean.RxxpBean.CommodityListBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.activity_hot,viewGroup,false);
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListsener!=null){
                    onClickListsener.onSuccess(mData.get(i).getCommodityId()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hot_pager_item)
        ImageView imageView;
        @BindView(R.id.hot_text_title)
        TextView textView_title;
        @BindView(R.id.hot_text_price)
        TextView textView_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    OnClickListsener onClickListsener;

    public void setOnClickListsener(OnClickListsener onClickListsener){
        this.onClickListsener=onClickListsener;
    }
    public interface OnClickListsener {
        void onSuccess(String i);
    }
}
