package com.android.hackathon.HackathonAndroid.network.api;

import com.android.hackathon.HackathonAndroid.modle.ProductKindResult;
import com.android.hackathon.HackathonAndroid.modle.ProductResult;
import com.android.hackathon.HackathonAndroid.modle.RequestBody;
import com.android.hackathon.HackathonAndroid.modle.UserResult;



import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zzbpc on 2016/11/19.
 */

public interface MyApi  {
    @POST("login")
    Observable<UserResult> login(@Body RequestBody requestBody);

    @GET("kinds")
    Observable<ProductKindResult> getKinds();

    @GET("products")
    Observable<ProductResult> getAllProducts();

    @GET("kinds/{kindId}/products")
    Observable<ProductResult> getOneKindProducts(@Path("kindId") int kindId);


}
