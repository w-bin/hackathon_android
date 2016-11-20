package com.android.hackathon.HackathonAndroid.modle;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class RequestBody {
    private String username;
    private String password;
    private int userId;
    private int productId;
    private int selectedNumber;
    private ArrayList<ShoppingCard> productsList;

    public RequestBody(int userId, ArrayList<ShoppingCard> productsList) {
        this.userId = userId;
        this.productsList = productsList;

    }

    public RequestBody(int userId, int productId, int selectNumber) {
        this.userId = userId;
        this.productId = productId;
        this.selectedNumber = selectNumber;
    }

    public RequestBody(String username, String password){
        this.username = username;
        this.password = password;
        Log.d("Request",username+" "+password);
    }


}
