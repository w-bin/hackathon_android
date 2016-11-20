package com.android.hackathon.HackathonAndroid.modle;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class OrderListResult {
    private String err;
    private Boolean result;
    private ArrayList<OrderList> ordersList;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }


    public OrderListResult(String err, Boolean result, ArrayList<OrderList> ordersList) {
        this.err = err;
        this.result = result;
        this.ordersList = ordersList;
    }

    public ArrayList<OrderList> getOrdersList() {

        return ordersList;
    }

    public void setOrdersList(ArrayList<OrderList> ordersList) {
        this.ordersList = ordersList;
    }
}
