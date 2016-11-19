package com.android.hackathon.HackathonAndroid.modle;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class ProductKindResult {
    public boolean isResult() {
        return result;
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

    public ArrayList<ProductKind> getKindList() {
        return kindsList;
    }

    public void setKindList(ArrayList<ProductKind> kindList) {
        this.kindsList = kindList;
    }

    public ProductKindResult(boolean result, String err, ArrayList<ProductKind> kindList) {

        this.result = result;
        this.err = err;
        this.kindsList = kindList;
    }

    private boolean result ;
    private String err;
    private ArrayList<ProductKind> kindsList;
}
