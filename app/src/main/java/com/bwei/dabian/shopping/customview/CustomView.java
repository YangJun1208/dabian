package com.bwei.dabian.shopping.customview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.dabian.R;
import com.bwei.dabian.shopping.adapter.ShopAdapter;
import com.bwei.dabian.shopping.bean.ShopBean;

import java.util.List;

public class CustomView extends LinearLayout {


    private int nums;
    private ShopAdapter shopAdapter;
    private List<ShopBean.ResultBean> list;
    private int position;
    private TextView jia;
    private TextView jian;
    private TextView shu;

    public CustomView(Context context) {
        super(context);
        initView(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        View view=View.inflate(context,R.layout.goods_item_sum,null);
        jia = view.findViewById(R.id.jia);
        shu = view.findViewById(R.id.shu);
        jian = view.findViewById(R.id.jian);
        addView(view);


        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nums++;
                if(nums>1){
                    jia.setTextColor(Color.parseColor("#666666"));
                }
                list.get(position).setCount(nums);
                shu.setText(nums+"");
                callBackListener.callback();
                shopAdapter.notifyItemChanged(position);
            }
        });


        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nums>1){
                    nums--;
                    list.get(position).setCount(nums);
                    shu.setText(nums+"");
                    callBackListener.callback();
                    shopAdapter.notifyItemChanged(position);
                    if(nums==1){
                        jian.setTextColor(Color.parseColor("#999999"));
                    }
                }
            }
        });
    }
    public void setData(ShopAdapter shopCarAdapter, List<ShopBean.ResultBean> list, int i){
        this.list=list;
        this.shopAdapter=shopAdapter;
        position=i;
        nums=list.get(position).getCount();
        shu.setText(nums+"");
        if(nums==1){
            jian.setTextColor(Color.parseColor("#999999"));
        }
    }

    private CallBackListener callBackListener;

    public void setOnCallBack(CallBackListener listener){
        callBackListener=listener;
    }
    public interface CallBackListener{
        void callback();
    }
}

