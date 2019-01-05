package com.bwei.dabian.bean;

import java.util.List;

public class HotItemBean {

    /**
     * result : [{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg","price":88,"saleNum":0},{"commodityId":18,"commodityName":"白色经典 秋季新款简约百搭轻便休闲女鞋板鞋小白鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/1/1.jpg","price":79,"saleNum":0},{"commodityId":12,"commodityName":"Lara style美妆BB蛋","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/2/1.jpg","price":22,"saleNum":0},{"commodityId":4,"commodityName":"佩佩防晕染眼线液笔","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/2/1.jpg","price":19,"saleNum":845},{"commodityId":28,"commodityName":"秋季新款女鞋【牛皮】艾斯臣女鞋单鞋蝴蝶结平底单鞋豆豆鞋女加绒保暖小毛球平底女鞋单鞋豆豆鞋女冬女士单鞋毛毛鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/4/1.jpg","price":159,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 32
         * commodityName : 唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋
         * masterPic : http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg
         * price : 88
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
