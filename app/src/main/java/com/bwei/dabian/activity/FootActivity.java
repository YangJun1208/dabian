package com.bwei.dabian.activity;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.adapter.FootAdapter;
import com.bwei.dabian.bean.FootBean;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootActivity extends BaseActivity implements IView {


   @BindView(R.id.xRecycle_foot)
    XRecyclerView xRecyclerView_foot;
   private IPersenterImpl iPersenter;
   private int mPage;
    private FootAdapter footAdapter;


    @Override
    protected void initData() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        xRecyclerView_foot.setLayoutManager(layoutManager);
        footAdapter = new FootAdapter(this);
        xRecyclerView_foot.setAdapter(footAdapter);

        xRecyclerView_foot.setLoadingMoreEnabled(true);
        xRecyclerView_foot.setPullRefreshEnabled(true);
        xRecyclerView_foot.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        loadData();

    }

    private void loadData() {
        iPersenter.getRequest(String.format(Apis.TYPE_FOOT_PWD,mPage),FootBean.class);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        iPersenter=new IPersenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_foot;
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof FootBean){
            FootBean footBean= (FootBean) data;
            if(footBean.getStatus().equals("0000")) {
                if (mPage == 1) {
                    footAdapter.setDatas(footBean.getResult());
                } else {
                    footAdapter.addDatas(footBean.getResult());
                }
                xRecyclerView_foot.loadMoreComplete();
                xRecyclerView_foot.refreshComplete();
            }else{
                Toast.makeText(this, footBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
