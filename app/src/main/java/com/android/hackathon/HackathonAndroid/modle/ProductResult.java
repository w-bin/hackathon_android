package com.android.hackathon.HackathonAndroid.modle;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class ProductResult {
    private boolean result;
    private String err;
    private ArrayList<Product> productsList;

    public boolean isResult() {
        return result;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }




    public ProductResult(boolean result, String err, ArrayList<Product> products) {

        this.result = result;
        this.err = err;
        this.productsList = products;
    }
}
