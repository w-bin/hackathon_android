package com.android.hackathon.HackathonAndroid.modle;

import android.util.Log;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class RequestBody {
    private String username;
    private String password;

    public RequestBody(String username, String password){
        this.username = username;
        this.password = password;
        Log.d("Request",username+" "+password);
    }
}
