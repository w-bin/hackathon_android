package com.android.hackathon.HackathonAndroid.modle;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class ShoppingCard {
    private int productId;
    private int selectedNumber;
    private int productPrice;
    private String productName;
    private String productDescribe;
    private int cardId;
    private int productNumber;

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }

    private int buyNumber;

    private ArrayList<String> imgUrl;

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    private boolean isChoosed;
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescribe() {
        return productDescribe;
    }

    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe;
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

    public ShoppingCard(int productId, int selectedNumber, int productPrice, String productName, String productDescribe, int cardId, int productNumber, int buyNumber, ArrayList<String> imgUrl, boolean isChoosed) {
        this.productId = productId;
        this.selectedNumber = selectedNumber;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescribe = productDescribe;
        this.cardId = cardId;
        this.productNumber = productNumber;
        this.buyNumber = buyNumber;
        this.imgUrl = imgUrl;
        this.isChoosed = isChoosed;
    }
}
