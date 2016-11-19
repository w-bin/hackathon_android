package com.android.hackathon.HackathonAndroid.network.api;

import com.android.hackathon.HackathonAndroid.modle.RequestBody;
import com.android.hackathon.HackathonAndroid.modle.Result;



import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zzbpc on 2016/11/19.
 */

public interface MyApi  {
    @POST("login")
    Observable<Result> login(@Body RequestBody requestBody);
}
