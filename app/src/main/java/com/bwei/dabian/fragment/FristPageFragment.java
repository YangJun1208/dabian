package com.bwei.dabian.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.activity.MyHotMore;
import com.bwei.dabian.adapter.AboveRecycle;
import com.bwei.dabian.adapter.BelowAdapter;
import com.bwei.dabian.adapter.HotAdapter;
import com.bwei.dabian.adapter.MyPagerAdapter;
import com.bwei.dabian.adapter.PurpleAdapter;
import com.bwei.dabian.adapter.QualityAdapter;
import com.bwei.dabian.adapter.SearchAdapter;
import com.bwei.dabian.adapter.ShowSeandAdapter;
import com.bwei.dabian.bean.AboveBean;
import com.bwei.dabian.bean.BannerBean;
import com.bwei.dabian.bean.BelowBean;
import com.bwei.dabian.bean.HotItemBean;
import com.bwei.dabian.bean.SearchBean;
import com.bwei.dabian.bean.ShowBean;
import com.bwei.dabian.bean.ShowZhanBean;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.utils.PagerTransformer;
import com.bwei.dabian.utils.SpacesItemDecoration;
import com.bwei.dabian.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lenovo
 */
public class FristPageFragment extends Fragment implements View.OnClickListener,IView {

    @BindView(R.id.image_frist_pager)
    ImageView imageView_frist_page;
    @BindView(R.id.layout_search)
    LinearLayout linearLayout;
    @BindView(R.id.Edit_editText)
    EditText editText_edit;
    @BindView(R.id.frist_pager_viewpager)
    ViewPager frist_pager;
    @BindView(R.id.hot_recycle)
    RecyclerView hot_recyclerView;
    private IPersenterImpl iPersenter;
    private MyPagerAdapter adapter;
    private int currentItem;

    @BindView(R.id.imeView_tab)
    RelativeLayout image_tab;

    private boolean isShow=false;
    @BindView(R.id.purple_recycle)
    RecyclerView recyclerView_purple;
    @BindView(R.id.quality_recycle)
    RecyclerView quality_recycle;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.hot_new)
    ImageView hot_new_item;
    @BindView(R.id.Text_hot_new)
    TextView textView_hot_new;
    @BindView(R.id.hot_image_item)
    ImageView imageView_hot_item;
    @BindView(R.id.hot_item1_show)
    RelativeLayout relativeLayout_hot;
    @BindView(R.id.purple_item2_show)
    RelativeLayout relativeLayout_purple;

    @BindView(R.id.above_recycle)
    RecyclerView above_recycle;
    @BindView(R.id.Search_recycle)
    XRecyclerView search_recycle;
    @BindView(R.id.quality_item3_show)
    RelativeLayout quality_show;

    @BindView(R.id.quality_item_recycle)
    XRecyclerView quality_item_recycle;
    @BindView(R.id.purple_item_recycle)
    XRecyclerView purple_item_recycle;
    @BindView(R.id.hot_item_recycle)
    XRecyclerView hot_item_recycle;
    private int mPage;
    @BindView(R.id.frist_imageView)
    ImageView frist_imageView;
    @BindView(R.id.none_goods)
    ImageView imageView_none_goods;
    @BindView(R.id.none_goods_textView)
    TextView none_goods_textView;
    @BindView(R.id.below_recycle)
    RecyclerView below_recycle;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currentItem++;
            frist_pager.setCurrentItem(currentItem);
            sendEmptyMessageDelayed(0,2000);
        }
    };
    private List<BannerBean.ResultBean> result;
    private HotAdapter hotAdapter;
    private PurpleAdapter purpleAdapter;
    private QualityAdapter qualityAdapter;
    private int id_hot;
    private MyHotMore hotMore;
    private int purple_id;
    private int quality_id;
    private String goods;
    private SearchAdapter searchAdapter;
    private AboveRecycle aboveRecycle;
    private BelowAdapter belowAdapter;
    private ShowSeandAdapter showSeandAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fristpagefragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPage=1;
        int spacingInPixels = 10;
        iPersenter=new IPersenterImpl(this);
        ButterKnife.bind(this,view);
        //viewpager的3D轮播
        loadData();
        //热销新品
        hotData(spacingInPixels);
        //魔力时尚
        purpleData(spacingInPixels);
        //品质生活
        qualityData(spacingInPixels);

    }

    //热销新品
    private void hotData(int spacingInPixels) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        hot_recyclerView.setLayoutManager(layoutManager);
        hot_recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        hotAdapter = new HotAdapter(getContext());
        hot_recyclerView.setAdapter(hotAdapter);
        iPersenter.getRequest(Apis.TYPE_HOT_SHOW,ShowBean.class);
    }

    private void qualityData(int spacingInPixels) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        quality_recycle.setLayoutManager(gridLayoutManager);
        qualityAdapter = new QualityAdapter(getContext());
        quality_recycle.setAdapter(qualityAdapter);

        quality_recycle.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

    //魔力时尚
    private void purpleData(int spacingInPixels) {
        LinearLayoutManager layoutMessage = new LinearLayoutManager(getActivity());
        layoutMessage.setOrientation(OrientationHelper.VERTICAL);
        recyclerView_purple.setLayoutManager(layoutMessage);
        purpleAdapter = new PurpleAdapter(getContext());
        recyclerView_purple.setAdapter(purpleAdapter);
        recyclerView_purple.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

    //viewpager的3D轮播
    private void loadData() {
        frist_pager.setPageMargin(10);
        frist_pager.setOffscreenPageLimit(4);
        frist_pager.setPageTransformer(true, new PagerTransformer());
        currentItem = frist_pager.getCurrentItem();
        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(currentItem,1000);
        iPersenter.getRequest(Apis.TYPE_BANNER,BannerBean.class);
    }
    @OnClick({R.id.image_frist_pager,R.id.image_quality,R.id.search_textView,R.id.layout_search,R.id.frist_imageView,R.id.image_purple,R.id.hot_image_item})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_frist_pager:
                    imageView_frist_page.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.search_textView:
                if(editText_edit.getText().length()==0){
                    imageView_frist_page.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.INVISIBLE);
                }else{
                    goods = editText_edit.getText().toString();
                    SearchGoods();
                    HotSearch(goods);
                    editText_edit.setText("");
                }
                break;
            case R.id.frist_imageView:
                if(!isShow) {
                    image_tab.setVisibility(View.VISIBLE);
                    //上边的数据加载
                   aboveRecaycle();
                }else{
                    image_tab.setVisibility(View.INVISIBLE);
                }
                isShow=!isShow;
                break;
            case R.id.hot_image_item:
                scrollView.setVisibility(View.GONE);
                relativeLayout_hot.setVisibility(View.VISIBLE);
                //热销新品的加载更多
                itemHotData();
                break;
            case R.id.image_purple:
                scrollView.setVisibility(View.GONE);
                relativeLayout_purple.setVisibility(View.VISIBLE);
                //魔力时尚的加载更多
                itemPurpleData();
                break;
            case R.id.image_quality:
                scrollView.setVisibility(View.GONE);
                quality_show.setVisibility(View.VISIBLE);
                //品质生活的加载更多
                itemQuailtyData();
                break;
                default:
                    break;
        }
    }

    private void aboveRecaycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        above_recycle.setLayoutManager(layoutManager);
        aboveRecycle = new AboveRecycle(getContext());
        above_recycle.setAdapter(aboveRecycle);
        iPersenter.getRequest(Apis.TYPE_ONELIST_SHOW,AboveBean.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        below_recycle.setLayoutManager(linearLayoutManager);
        belowAdapter = new BelowAdapter(getContext());
        below_recycle.setAdapter(belowAdapter);
        iPersenter.getRequest(String.format(Apis.TYPE_TWOLIST_SHOW,"1001002"),BelowBean.class);

        aboveRecycle.setOnClickListener(new AboveRecycle.OnClicksListener() {
            @Override
            public void onSuccess(String id) {
                //两级列表的数据
              belowData(id);
            }
        });

    }

    private void belowData(String id) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        below_recycle.setLayoutManager(linearLayoutManager);
        belowAdapter = new BelowAdapter(getContext());
        below_recycle.setAdapter(belowAdapter);
        iPersenter.getRequest(String.format(Apis.TYPE_TWOLIST_SHOW,id),BelowBean.class);
        belowAdapter.setOnClickListeners(new BelowAdapter.OnClickListeners() {
            @Override
            public void onSuccess(String id){
                //二级列表的数据展示
                ShowData(id);
                scrollView.setVisibility(View.GONE);
                search_recycle.setVisibility(View.VISIBLE);
                image_tab.setVisibility(View.GONE);
                relativeLayout_hot.setVisibility(View.GONE);
                relativeLayout_purple.setVisibility(View.GONE);
                quality_show.setVisibility(View.GONE);
            }
        });
    }


    //二级列表的数据展示
    private void ShowData(final String id) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        search_recycle.setLayoutManager(layoutManager);

        showSeandAdapter = new ShowSeandAdapter(getContext());
        search_recycle.setAdapter(showSeandAdapter);

        iPersenter.getRequest(String.format(Apis.TYPE_TWOSHOWLIST_SHOW,id,mPage),ShowZhanBean.class);

        search_recycle.setLoadingMoreEnabled(true);
        search_recycle.setPullRefreshEnabled(true);
        search_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                iPersenter.getRequest(String.format(Apis.TYPE_TWOSHOWLIST_SHOW,id,mPage),ShowZhanBean.class);

            }

            @Override
            public void onLoadMore() {
                mPage++;
                iPersenter.getRequest(String.format(Apis.TYPE_TWOSHOWLIST_SHOW,id,mPage),ShowZhanBean.class);
            }
        });


       }

    //搜索出来的商品
    private void SearchGoods() {

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        search_recycle.setLayoutManager(layoutManager);

        search_recycle.setLoadingMoreEnabled(true);
        search_recycle.setPullRefreshEnabled(true);
        search_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                HotSearch(goods);
            }

            @Override
            public void onLoadMore() {
                HotSearch(goods);
            }

        });
        searchAdapter = new SearchAdapter(getContext());
        search_recycle.setAdapter(searchAdapter);
        HotSearch(goods);
    }

    private void HotSearch(String goods) {
        iPersenter.getRequest(String.format(Apis.TYPE_SEARCH_SHOW,goods,mPage),SearchBean.class);
    }

    //品质生活的加载更多数据
    private void itemQuailtyData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        quality_item_recycle.setLayoutManager(layoutManager);

        quality_item_recycle.setLoadingMoreEnabled(true);
        quality_item_recycle.setPullRefreshEnabled(true);
        quality_item_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,quality_id,mPage),HotItemBean.class);

            }

            @Override
            public void onLoadMore() {
                mPage++;
                iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,quality_id,mPage),HotItemBean.class);
            }
        });

        hotMore = new MyHotMore(getContext());
        quality_item_recycle.setAdapter(hotMore);
        iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,quality_id,mPage),HotItemBean.class);
    }

    //魔力时尚的加载更多
    private void itemPurpleData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        purple_item_recycle.setLayoutManager(layoutManager);
        purple_item_recycle.setLoadingMoreEnabled(true);
        purple_item_recycle.setPullRefreshEnabled(true);
        purple_item_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,purple_id,mPage),HotItemBean.class);

            }

            @Override
            public void onLoadMore() {
                mPage++;
                Log.i("TAG","222");
                iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,purple_id,mPage),HotItemBean.class);

            }
        });
        hotMore = new MyHotMore(getContext());
        purple_item_recycle.setAdapter(hotMore);
        iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,purple_id,mPage),HotItemBean.class);
    }

    //热销新品的加载更多
    private void itemHotData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        hot_item_recycle.setLayoutManager(layoutManager);

        hot_item_recycle.setLoadingMoreEnabled(true);
        hot_item_recycle.setPullRefreshEnabled(true);
        hot_item_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,id_hot,mPage),HotItemBean.class);

            }

            @Override
            public void onLoadMore() {
                mPage++;
                iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,id_hot,mPage),HotItemBean.class);

            }
        });

        hotMore = new MyHotMore(getContext());
        hot_item_recycle.setAdapter(hotMore);
        iPersenter.getRequest(String.format(Apis.TYPE_MORE_SHOW,id_hot,mPage),HotItemBean.class);

    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof BannerBean){
            BannerBean bannerBean= (BannerBean) data;
            if(bannerBean.getStatus().equals("0000")){
                result = bannerBean.getResult();
                adapter = new MyPagerAdapter(getContext(),result);
                frist_pager.setAdapter(adapter);
            }else{
                Toast.makeText(getContext(), bannerBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(data instanceof ShowBean){
            ShowBean showBean= (ShowBean) data;
            if(showBean.getStatus().equals("0000")){
                //热销新品的数据
                id_hot = showBean.getResult().getRxxp().get(0).getId();
                List<ShowBean.ResultBean.RxxpBean.CommodityListBean> commodityListBeans = showBean.getResult().getRxxp().get(0).getCommodityList();
                hotAdapter.setDatas(commodityListBeans);
                //魔力时尚的数据
                purple_id = showBean.getResult().getMlss().get(0).getId();
                List<ShowBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = showBean.getResult().getMlss().get(0).getCommodityList();
                purpleAdapter.setmData(commodityList);
                //品质生活的数据
                quality_id = showBean.getResult().getPzsh().get(0).getId();
                List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanXES = showBean.getResult().getPzsh().get(0).getCommodityList();
                qualityAdapter.setDatas(commodityListBeanXES);
            }else{
                Toast.makeText(getContext(), showBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(data instanceof HotItemBean){
            HotItemBean hotItemBean= (HotItemBean) data;
            if(mPage==1) {
                hotMore.setDatas(hotItemBean.getResult());
            }else{
                hotMore.addDatas(hotItemBean.getResult());
            }
            quality_item_recycle.refreshComplete();
            quality_item_recycle.loadMoreComplete();
            hot_item_recycle.loadMoreComplete();
            hot_item_recycle.refreshComplete();
            purple_item_recycle.loadMoreComplete();
            purple_item_recycle.refreshComplete();
        }else if(data instanceof SearchBean){
            SearchBean searchBean= (SearchBean) data;
            if (mPage == 1) {
                if(searchBean.getResult().size()==0){
                    scrollView.setVisibility(View.GONE);
                    imageView_none_goods.setVisibility(View.VISIBLE);
                    none_goods_textView.setVisibility(View.VISIBLE);
                }else{
                    imageView_none_goods.setVisibility(View.GONE);
                    none_goods_textView.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    search_recycle.setVisibility(View.VISIBLE);
                    relativeLayout_hot.setVisibility(View.GONE);
                    relativeLayout_purple.setVisibility(View.GONE);
                    quality_show.setVisibility(View.GONE);
                    SearchGoods();
                }
                searchAdapter.setDatas(searchBean.getResult());
            } else {
                if(searchBean.getResult().size()==0) {
                    Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
                }
                searchAdapter.addDatas(searchBean.getResult());
            }
            mPage++;
            search_recycle.refreshComplete();
            search_recycle.loadMoreComplete();
        }else if(data instanceof AboveBean){
            AboveBean aboveBean= (AboveBean) data;
            aboveRecycle.setDatas(aboveBean.getResult());

        }else if(data instanceof BelowBean){
            BelowBean belowBean= (BelowBean) data;
            belowAdapter.setDatsa(belowBean.getResult());

        }else if(data instanceof ShowZhanBean){
            ShowZhanBean showZhanBean= (ShowZhanBean) data;
            if(mPage==1){
                showSeandAdapter.setDatas(showZhanBean.getResult());
            }else{
                showSeandAdapter.addDatas(showZhanBean.getResult());
            }
            search_recycle.refreshComplete();
            search_recycle.loadMoreComplete();
        }

    }

    //监听返回键
    public void getBackData(boolean back){
        if(back){
            search_recycle.setVisibility(View.INVISIBLE);
            relativeLayout_hot.setVisibility(View.INVISIBLE);
            relativeLayout_purple.setVisibility(View.INVISIBLE);
            quality_show.setVisibility(View.INVISIBLE);
            scrollView.setVisibility(View.VISIBLE);
            imageView_none_goods.setVisibility(View.INVISIBLE);
            none_goods_textView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPersenter.deatch();
    }
}
