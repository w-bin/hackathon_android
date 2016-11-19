package com.android.hackathon.HackathonAndroid.network;

import com.android.hackathon.HackathonAndroid.network.api.MyApi;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class NetWork {
    public static MyApi myApi;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static  MyApi getMyApi(){
        if(myApi ==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.31.73.143:3000/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            myApi = retrofit.create(MyApi.class);
        }
        return myApi;
    }
}
