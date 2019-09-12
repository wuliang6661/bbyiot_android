package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;
import java.util.List;

public class GoodsReviewResponse implements Serializable {
    /**
     * brand :
     * goodsDescribe :
     * goodsTypeId : 0
     * id : 0
     * image :
     * items : [{"createDate":"","goodsInfoId":0,"id":0,"itemName":"","remark":"","reviewResult":"","updateDate":""}]
     * name :
     * names : []
     * performance : 0
     * price : 0
     * title :
     */

    private String brand;
    private String goodsDescribe;
    private int goodsTypeId;
    private int id;
    private String image;
    private String name;
    private String performance;
    private int price;
    private String title;
    private List<ItemsBean> items;
    private List<String> names;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public int getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(int goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public static class ItemsBean {
        /**
         * createDate :
         * goodsInfoId : 0
         * id : 0
         * itemName :
         * remark :
         * reviewResult :
         * updateDate :
         */

        private String createDate;
        private int goodsInfoId;
        private int id;
        private String itemName;
        private String remark;
        private String reviewResult;
        private String updateDate;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getGoodsInfoId() {
            return goodsInfoId;
        }

        public void setGoodsInfoId(int goodsInfoId) {
            this.goodsInfoId = goodsInfoId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getReviewResult() {
            return reviewResult;
        }

        public void setReviewResult(String reviewResult) {
            this.reviewResult = reviewResult;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }
    }
}
