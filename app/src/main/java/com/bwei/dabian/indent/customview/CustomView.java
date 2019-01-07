package com.bwei.dabian.indent.customview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwei.dabian.R;
import com.bwei.dabian.indent.adapter.GoodsAdapter;
import com.bwei.dabian.indent.adapter.OrderAdapter;
import com.bwei.dabian.indent.bean.OrderBean;

import java.util.List;

public class CustomView extends LinearLayout {

    private Context context;
    private EditText shu;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    int num = 0;

    private void init(final Context context) {
        View view = View.inflate(context, R.layout.goods_item_sum, null);
        Button jia = view.findViewById(R.id.jia);
        Button jian = view.findViewById(R.id.jian);
        shu = view.findViewById(R.id.shu);
        addView(view);

        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                shu.setText(num + "");
                list.get(position).setCommodityCount(num);
                onClicksListeners.CallBack();
                goodsAdapter.notifyItemChanged(position);
            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num > 1) {
                    num--;
                    shu.setText(num + "");
                    list.get(position).setCommodityCount(num);
                    onClicksListeners.CallBack();
                    goodsAdapter.notifyItemChanged(position);
                } else {
                    Toast.makeText(context, "有底线的", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num = Integer.parseInt(String.valueOf(s));
                try {
                    list.get(position).setCommodityCount(num);
                } catch (Exception e) {
                    list.get(position).setCommodityCount(1);
                }
                if (onClicksListeners != null) {
                    onClicksListeners.CallBack();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    OnClicksListeners onClicksListeners;

    public void setOnClicksListeners(OnClicksListeners onClicksListeners) {
        this.onClicksListeners = onClicksListeners;
    }


    public interface OnClicksListeners {
        void CallBack();
    }

    private int position;
    private GoodsAdapter goodsAdapter;
    private List<OrderBean.OrderListBean.DetailListBean> list;

    public void setData(int position, GoodsAdapter
            goodsAdapter, List<OrderBean.OrderListBean.DetailListBean> list) {
        this.goodsAdapter = goodsAdapter;
        this.position = position;
        this.list = list;
        num = list.get(position).getCommodityCount();
        shu.setText(num + "");
    }

}
