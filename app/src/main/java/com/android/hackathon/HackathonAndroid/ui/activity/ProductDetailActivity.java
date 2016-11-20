package com.android.hackathon.HackathonAndroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hackathon.HackathonAndroid.R;

import com.android.hackathon.HackathonAndroid.modle.RequestBody;
import com.android.hackathon.HackathonAndroid.modle.ShoppingResult;
import com.android.hackathon.HackathonAndroid.network.NetWork;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class ProductDetailActivity extends AppCompatActivity {
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.productName)
    TextView productname;
    @Bind(R.id.productPrice)
    TextView productprice;
    @Bind(R.id.productDes)
    TextView productdescription;
    @Bind(R.id.productRemain)
    TextView productremain;
    @Bind(R.id.submitbtn)
    Button submitbutton;


    private int maxnum;
    private int productId;
    private int selectedNum;
    private Subscription subscription;


    @OnClick(R.id.submitbtn)
    void addMyShoppingCard() {


        if (MainActivity.isLogin) {
            Log.d(TAG, MainActivity.userId + " " + productId + "  " + selectedNum);

            subscription = NetWork.getMyApi()
                    .postToShoppingCard(MainActivity.userId, new RequestBody(MainActivity.userId, productId, 1))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getSubscriber());
        } else {
            Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
            startActivity(intent);
            ProductDetailActivity.this.finish();
        }

    }

    private Subscriber<ShoppingResult> getSubscriber() {
        Subscriber<ShoppingResult> subscriber = new Subscriber<ShoppingResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
                Toast.makeText(ProductDetailActivity.this, "出了点问题,请重新加入购物车", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(ShoppingResult shoppingResult) {
                if (shoppingResult.getErr() != null)
                    Toast.makeText(ProductDetailActivity.this, shoppingResult.getErr(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProductDetailActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();

            }
        };
        return subscriber;
    }

    private static final String TAG = "ProductDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
        maxnum = Integer.parseInt(productremain.getText().toString());
    }

    private void init() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Log.d(TAG, bundle.getString("productName"));
        productname.setText(bundle.getString("productName"));
        productprice.setText(bundle.getString("productPrice"));
        productdescription.setText(bundle.getString("productDes"));
        productremain.setText(bundle.getInt("productRemain") + "");
        productId = Integer.parseInt(bundle.getString("productId"));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setBannerStyle(BannerConfig.CENTER);
        banner.setImages(bundle.getStringArrayList("productImgUrl"));


    }


}
