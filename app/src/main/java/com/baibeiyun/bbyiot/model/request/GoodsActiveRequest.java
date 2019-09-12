package com.baibeiyun.bbyiot.model.request;

public class GoodsActiveRequest extends BaseRequest {

    /**
     * id : 0
     * pageNum : 0
     * pageSize : 10
     */

    //private int id;
    private int pageNum;
    private int pageSize;

    //public void setId(int id) {
//        this.id = id;
//    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
