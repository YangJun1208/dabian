package com.bwei.dabian.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.dabian.R;
import com.bwei.dabian.bean.BelowBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BelowAdapter extends RecyclerView.Adapter<BelowAdapter.ViewHolder> {

    private Context context;
    private List<BelowBean.ResultBean> mData;

    public BelowAdapter(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setDatsa(List<BelowBean.ResultBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BelowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.below_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BelowAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.below_text.setText(mData.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListeners!=null){
                    onClickListeners.onSuccess(mData.get(i).getId()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       @BindView(R.id.below_textView)
        TextView below_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    OnClickListeners onClickListeners;

    public void setOnClickListeners(OnClickListeners listeners){
        this.onClickListeners=listeners;
    }

    public interface OnClickListeners {
        void onSuccess(String id);
    }
}
