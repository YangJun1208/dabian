package com.bwei.dabian;

public class Apis {
    public static final String TYPE_REGRITER="user/v1/register";
    //user/v1/login?phone=13501084753&pwd=123456
    public static final String TYPE_LOGIN="user/v1/login";
    //circle/v1/findCircleList  529
    public static final String TYPE_CIRCLE="circle/v1/findCircleList?page=1&count=5";
    //    //circle/verify/v1/addCircleGreat
    public static final String TYPE_ADD_ZAN="circle/verify/v1/addCircleGreat";
    //circle/verify/v1/cancelCircleGreat取消点赞
    public static final String TYPE_CANCLE_ZAN="circle/verify/v1/cancelCircleGreat";
    //http://172.17.8.100/small/commodity/v1/bannerShow
    public static final String TYPE_BANNER="commodity/v1/bannerShow";
    //commodity/v1/bannerShow
    public static final String TYPE_HOT_SHOW="commodity/v1/commodityList";

    //http://172.17.8.100/small/commodity/v1/findCommodityListByLabel
    public static final String TYPE_MORE_SHOW="commodity/v1/findCommodityListByLabel?labelId=%s&page=%d&count=10";

    //commodity/v1/findCommodityByKeyword 点击搜索查询商品
    public static final String TYPE_SEARCH_SHOW="commodity/v1/findCommodityByKeyword?keyword=%s&page=%d&count=10";

    //commodity/v1/findFirstCategory一级的列表
    public static final String TYPE_ONELIST_SHOW="commodity/v1/findFirstCategory";
    //commodity/v1/findSecondCategory二级列表
    public static final String TYPE_TWOLIST_SHOW="commodity/v1/findSecondCategory?firstCategoryId=%s";
    //commodity/v1/findCommodityByCategory 二级列表展示
    public static final String TYPE_TWOSHOWLIST_SHOW="commodity/v1/findCommodityByCategory?categoryId=%s&page=%d&count=10";

}
