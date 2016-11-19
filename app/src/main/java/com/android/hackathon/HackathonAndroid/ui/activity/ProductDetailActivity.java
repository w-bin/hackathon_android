package com.android.hackathon.HackathonAndroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.hackathon.HackathonAndroid.R;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @OnClick(R.id.submitbtn)
    void addMyShoppingCard(){

    }


    private  static  final  String TAG = "ProductDetailActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        Intent intent  = getIntent();
        Bundle bundle = intent.getExtras();
        Log.d(TAG,bundle.getString("productName"));
        ArrayList<String >list = new ArrayList<String>();
        productname.setText(bundle.getString("productName"));
        productprice.setText(bundle.getString("productPrice"));
        productdescription.setText(bundle.getString("productDes"));
        productremain.setText("库存为: "+bundle.getInt("productRemain"));
        list.add(bundle.getString("productImage"));
        banner.setImages(list);
    }



}
