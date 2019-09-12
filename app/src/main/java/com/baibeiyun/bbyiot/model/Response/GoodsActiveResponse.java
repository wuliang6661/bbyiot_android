package com.baibeiyun.bbyiot.model.Response;

import java.util.List;

public class GoodsActiveResponse {


    /**
     * brand : 品牌1
     * id : 1
     * image : http://thirdwx.qlogo.cn/mmopen/vi_32/6p7YWGXJhrHUbHvLxibwklgLM1ZGWT1uM2lDyjHTj44Dd6uDNjwNU8oibX7k5OiabdwcZcHIic8HibXW090ico6JOibxw/132
     * names : []
     * title : 模拟器
     */

    private String brand;
    private int id;
    private String image;
    private String title;
    private List<?> names;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<?> getNames() {
        return names;
    }

    public void setNames(List<?> names) {
        this.names = names;
    }
}
