package com.android.hackathon.HackathonAndroid.modle;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class OrderList {
    private int orderId;
    private ArrayList<ShoppingResult> ordersList;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<ShoppingResult> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(ArrayList<ShoppingResult> ordersList) {
        this.ordersList = ordersList;
    }

    public OrderList(int orderId, ArrayList<ShoppingResult> ordersList) {

        this.orderId = orderId;
        this.ordersList = ordersList;
    }
}
