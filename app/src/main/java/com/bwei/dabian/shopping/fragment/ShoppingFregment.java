package com.bwei.dabian.shopping.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.fragment.BaseFragment;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.shopping.adapter.ShopAdapter;
import com.bwei.dabian.shopping.bean.ShopBean;
import com.bwei.dabian.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lenovo
 * 购物车的逻辑
 */
public class ShoppingFregment extends BaseFragment implements IView {


    @BindView(R.id.shopping_recycle)
    RecyclerView shopping_recycle;
    private IPersenterImpl iPersenter;
    private ShopAdapter shopAdapter;

    @Override
    protected void initData() {
        iPersenter=new IPersenterImpl(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shopping_recycle.setLayoutManager(linearLayoutManager);

        shopAdapter = new ShopAdapter(getContext());
        shopping_recycle.setAdapter(shopAdapter);

        loadData();
    }

    private void loadData() {
        iPersenter.getRequest(Apis.TYPE_SHOW_SHOP,ShopBean.class);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.shoppingfregment;
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof ShopBean){
            ShopBean shopBean= (ShopBean) data;
            if(shopBean.getStatus().equals("0000")){
                shopAdapter.setDatass(shopBean.getResult());
            }
        }
    }
}
