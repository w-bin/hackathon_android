package com.android.hackathon.HackathonAndroid.modle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class ShoppingResult   {
    private String err;
    private boolean result;
    private ArrayList<ShoppingCard> shoppingCardsList;

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ArrayList<ShoppingCard> getShoppingCardsList() {
        return shoppingCardsList;
    }

    public void setShoppingCardsList(ArrayList<ShoppingCard> shoppingCardsList) {
        this.shoppingCardsList = shoppingCardsList;
    }



}
