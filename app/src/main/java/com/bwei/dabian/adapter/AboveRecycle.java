package com.bwei.dabian.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.dabian.R;
import com.bwei.dabian.bean.AboveBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboveRecycle extends RecyclerView.Adapter<AboveRecycle.ViewHolder> {

    private List<AboveBean.ResultBean> mData;
    private Context context;

    public AboveRecycle(Context context) {
        this.context = context;
        mData=new ArrayList<>();
    }

    public void setDatas(List<AboveBean.ResultBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AboveRecycle.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.above_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AboveRecycle.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(mData.get(i).getName());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener!=null){
                    onClickListener.onSuccess(mData.get(i).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.above_textView)
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    OnClicksListener onClickListener;

    public void setOnClickListener (OnClicksListener listener){
        this.onClickListener=listener;
    }
    public interface OnClicksListener {
        void onSuccess(String id);
    }
}
