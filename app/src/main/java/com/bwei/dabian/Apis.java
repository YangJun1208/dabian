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
    public static final String TYPE_CANCLE_ZAN="circle/verify/v1/cancelCircleGreat?circleId=%s";
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
    //commodity/v1/findCommodityByCategory 二级列表展示kk
    public static final String TYPE_TWOSHOWLIST_SHOW="commodity/v1/findCommodityByCategory?categoryId=%s&page=%d&count=10";

    //http://172.17.8.100/small/user/verify/v1/modifyUserNick 修改名字
    public static final String TYPE_UPDATE_NAME="user/verify/v1/modifyUserNick";
    //http://172.17.8.100/small/user/verify/v1/modifyUserPwd  修改密码
    public static final String TYPE_UPDATE_PWD="user/verify/v1/modifyUserPwd";
    //http://172.17.8.100/small/commodity/verify/v1/browseList  我的足迹
    public static final String TYPE_FOOT_PWD="commodity/verify/v1/browseList?page=%d&count=10";
    //http://172.17.8.100/small/circle/verify/v1/findMyCircleById  我的圈子
    public static final String TYPE_CIRCLE_MINE="circle/verify/v1/findMyCircleById?page=%d&page=10";

    //order/verify/v1/findShoppingCart  查询购物车  GET
    public static final String TYPE_SHOW_SHOP="order/verify/v1/findShoppingCart";

    //order/verify/v1/findOrderListByStatus  订单查询
    public static final String TYPE_INTENT_SELECT="order/verify/v1/findOrderListByStatus?status=%d&page=%d&count=5";


}
