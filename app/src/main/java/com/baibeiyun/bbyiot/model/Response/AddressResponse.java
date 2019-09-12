package com.baibeiyun.bbyiot.model.Response;

import java.io.Serializable;

public class AddressResponse implements Serializable{


    /**
     * address : 西港新界
     * area : 西湖区
     * city : 杭州市
     * id : 1
     * isDefault : 1
     * name : 海带
     * phone : 18368163483
     * postalCode : 310000
     * province : 浙江省
     */

    private String address;
    private String area;
    private String city;
    private int id;
    private int isDefault;
    private String name;
    private String phone;
    private String postalCode;
    private String province;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
