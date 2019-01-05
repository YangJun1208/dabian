package com.bwei.dabian.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.activity.LoginActivity;
import com.bwei.dabian.bean.AddBean;
import com.bwei.dabian.bean.CircleBean;
import com.bwei.dabian.eventbus.Message;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.view.IView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> implements IView{

    private Context mContext;
    private List<CircleBean.ResultBean> mData;

    private IPersenterImpl iPersenter;

    public CircleAdapter(Context mContext) {
        this.mContext = mContext;
        mData=new ArrayList<>();
    }

    public void setDatas(List<CircleBean.ResultBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.circle_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        ButterKnife.bind(viewHolder,view);
        iPersenter=new IPersenterImpl(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        String image = mData.get(i).getHeadPic();
        viewHolder.circle_image.setImageURI(Uri.parse(image));

        RoundingParams roundingParams=new RoundingParams();
        roundingParams.setRoundAsCircle(true);
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setRoundingParams(roundingParams)
                .build();
        viewHolder.circle_image.setHierarchy(hierarchy);

        viewHolder.circle_name.setText(mData.get(i).getNickName());

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                new java.util.Date(mData.get(i).getCreateTime()));
        viewHolder.text_time.setText(date);
        String image1 = mData.get(i).getImage();
        Glide.with(mContext).load(image1).into(viewHolder.circle_big_image);
        viewHolder.circle_title.setText(mData.get(i).getContent());
        viewHolder.circle_shou_zan.setText(mData.get(i).getGreatNum()+"");

        viewHolder.circle_shou_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onClicksLisenter!=null){
                    onClicksLisenter.onSuccess(mData.get(i).getWhetherGreat()+"",mData.get(i).getGreatNum()+"");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onSuccess(Object data) {
        if (data instanceof AddBean){
            AddBean addBean= (AddBean) data;
            Toast.makeText(mContext, addBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        SimpleDraweeView circle_image;
        @BindView(R.id.circle_name)
        TextView circle_name;
        @BindView(R.id.circle_time)
        TextView text_time;
        @BindView(R.id.circle_title)
        TextView circle_title;
        @BindView(R.id.circle_big_image)
        ImageView circle_big_image;
        @BindView(R.id.circle_shou_image)
        ImageView circle_shou_image;
        @BindView(R.id.circle_shou_zan)
        TextView circle_shou_zan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circle_image=itemView.findViewById(R.id.circle_image);

        }
    }

    OnClicksLisenter onClicksLisenter;

    public void setOnClicksLisenter(OnClicksLisenter onClicksLisenter){
        this.onClicksLisenter=onClicksLisenter;
    }

    public interface OnClicksLisenter {
        void onSuccess(String data,String num);
    }
}
