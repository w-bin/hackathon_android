package com.android.hackathon.HackathonAndroid.modle;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class Product {
    private String price;
    private String name;
    private String imgurl;
    private String describe;
    private String productId;
    private String kindId;
    private int number;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Product(String price, String name, String imgurl, String describe, String productId, String kindId, int number) {

        this.price = price;
        this.name = name;
        this.imgurl = imgurl;
        this.describe = describe;
        this.productId = productId;
        this.kindId = kindId;
        this.number = number;
    }
}
