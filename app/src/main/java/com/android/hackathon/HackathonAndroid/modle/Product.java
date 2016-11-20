package com.android.hackathon.HackathonAndroid.modle;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class Product {
    private String price;
    private String name;

    private ArrayList<String> imgUrl;
    private String describe;
    private String productId;
    private String kindId;
    private int number;
    private int cardId;
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

    public ArrayList<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(ArrayList<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Product(String price, String name, ArrayList<String> imgUrl, String describe, String productId, String kindId, int number, int cardId) {

        this.price = price;
        this.name = name;
        this.imgUrl = imgUrl;
        this.describe = describe;
        this.productId = productId;
        this.kindId = kindId;
        this.number = number;
        this.cardId = cardId;
    }
}
