package com.bwei.dabian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.adapter.CircleAdapter;
import com.bwei.dabian.bean.AddBean;
import com.bwei.dabian.bean.CancleBean;
import com.bwei.dabian.bean.CircleBean;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.view.IView;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lenovo
 * 圈子的页面
 */
public class CircleFragment extends Fragment implements IView {

    @BindView(R.id.circle_RecyclerView)
    RecyclerView recyclerView;
    private CircleAdapter adapter;
    private IPersenterImpl iPersenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.circlefragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);
        iPersenter=new IPersenterImpl(this);



        loadData();

        adapter.setOnClicksLisenter(new CircleAdapter.OnClicksLisenter() {
            @Override
            public void onSuccess(String data, String num) {
                //当前登录用户是否点赞(2为否，1为是)
                Log.i("TAG",data);
                if(data.equals("2")){
                    Map<String,String> pamare=new HashMap<>();
                    pamare.put("circleId","1");
                    iPersenter.postRequest(Apis.TYPE_ADD_ZAN,pamare,AddBean.class);
                }else{
                    Map<String,String> pamare=new HashMap<>();
                    pamare.put("circleId","1");
                    iPersenter.deleteRequest(Apis.TYPE_CANCLE_ZAN,CancleBean.class);
                }
            }
        });


    }
    private void loadData() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CircleAdapter(getContext());
        recyclerView.setAdapter(adapter);
        iPersenter.getRequest(String.format(Apis.TYPE_CIRCLE),CircleBean.class);
    }
    @Override
    public void onSuccess(Object data) {
        if(data instanceof CircleBean){
            CircleBean circleBean= (CircleBean) data;
            if(circleBean.getMessage().equals("查询成功")){
                adapter.setDatas(circleBean.getResult());
            }else{
                Toast.makeText(getContext(), circleBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(data instanceof AddBean){
            AddBean addBean= (AddBean) data;
            if(addBean.getStatus().equals("0000")){
                Toast.makeText(getContext(), addBean.getMessage(), Toast.LENGTH_SHORT).show();
                loadData();
            }else{
                Toast.makeText(getContext(), addBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(data instanceof CancleBean){
            CancleBean cancleBean= (CancleBean) data;
            if (cancleBean.getStatus().equals("0000")){
                Toast.makeText(getContext(), cancleBean.getMessage(), Toast.LENGTH_SHORT).show();
                loadData();
            }else{
                Toast.makeText(getContext(), cancleBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
    }
}
