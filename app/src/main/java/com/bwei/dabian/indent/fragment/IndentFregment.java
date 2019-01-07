package com.bwei.dabian.indent.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.fragment.BaseFragment;
import com.bwei.dabian.indent.adapter.OrderAdapter;
import com.bwei.dabian.indent.bean.OrderBean;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lenovo
 * 订单页面
 */
public class IndentFregment extends BaseFragment implements IView,View.OnClickListener {

    @BindView(R.id.indent_order_Layout)
    LinearLayout indent_order_Layout;
    @BindView(R.id.intent_payment_layout)
    LinearLayout intent_payment_layout;
    @BindView(R.id.intent_receiving_layout)
    LinearLayout intent_receiving_layout;
    @BindView(R.id.intent_remain_layout)
    LinearLayout intent_remain_layout;
    @BindView(R.id.intent_stocks_layout)
    LinearLayout intent_stocks_layout;

    @BindView(R.id.recycle_order)
    XRecyclerView recyclerView_goods;
    @BindView(R.id.relative_order)
    RelativeLayout relativeLayout_intent;
    private IPersenterImpl iPersenter;
    private OrderAdapter orderAdapter;
    private int mPage;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
        iPersenter=new IPersenterImpl(this);
        mPage=1;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.indentfregment;
    }

    @OnClick({R.id.indent_order_Layout,R.id.intent_payment_layout,R.id.intent_receiving_layout,R.id.intent_remain_layout,R.id.intent_stocks_layout})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.indent_order_Layout:
                relativeLayout_intent.setVisibility(View.VISIBLE);
                //获取数据
                loadDataIntent();
                break;
            case R.id.intent_payment_layout:

                break;
            case R.id.intent_receiving_layout:
                break;
            case R.id.intent_remain_layout:
                break;
            case R.id.intent_stocks_layout:
                break;
            default:break;
        }
    }

    private void loadDataIntent() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_goods.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(getContext());
        recyclerView_goods.setAdapter(orderAdapter);
        recyclerView_goods.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadData();
            }
        });

    }

    private void loadData() {
        iPersenter.getRequest(String.format(Apis.TYPE_INTENT_SELECT,0,mPage),OrderBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof OrderBean){
            OrderBean orderBean= (OrderBean) data;
            if(orderBean.getStatus().equals("0000")){
                if(mPage==1){

                }
            }
        }
    }
}
