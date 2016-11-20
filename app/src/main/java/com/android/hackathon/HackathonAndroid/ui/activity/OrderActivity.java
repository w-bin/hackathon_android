package com.android.hackathon.HackathonAndroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.adapter.OrderAdapter;
import com.android.hackathon.HackathonAndroid.modle.OrderListResult;
import com.android.hackathon.HackathonAndroid.modle.RequestBody;
import com.android.hackathon.HackathonAndroid.modle.ShoppingCard;
import com.android.hackathon.HackathonAndroid.modle.ShoppingResult;
import com.android.hackathon.HackathonAndroid.network.NetWork;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class OrderActivity extends AppCompatActivity {
    @Bind(R.id.order_list_view)
    RecyclerView recyclerView;
    @Bind(R.id.submitbtn)
    Button button;
    @Bind(R.id.sumtv)
    TextView sumtv;
    @OnClick(R.id.submitbtn)
    void order(){
        setOrder();
    }

    private OrderAdapter orderAdapter;
    private int sum = 0;
    private Subscription subscription;
    private static String TAG ="OrderActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        orderAdapter = new OrderAdapter();
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onClickAdd(int price) {
                sum+=price;
                sumtv.setText(""+sum);
            }

            @Override
            public void onClickSub(int price) {
                sum-=price;
                sumtv.setText(""+sum);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(orderAdapter);
        orderAdapter.addAll(ShoppingCars.list);
        sumtv.setText(""+orderAdapter.getMoney());
        sum=orderAdapter.getMoney();
    }

    private Subscriber<OrderListResult> getSubscriber() {
        Subscriber<OrderListResult> subscriber = new Subscriber<OrderListResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onNext(OrderListResult orderListResult) {
//                Log.d(TAG,shoppingCard.);
                if (orderListResult.getErr() != null) {
                    Toast.makeText(OrderActivity.this, orderListResult.getErr(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(OrderActivity.this,"下单成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OrderActivity.this,OrderDetailActivity.class);
                ArrayList<String > productnames = new ArrayList<String>();
                ArrayList<Integer> productNumbers = new ArrayList<Integer>();
                ArrayList<ShoppingCard> list = orderAdapter.getList();
                for (ShoppingCard s :list){
                    productnames.add(s.getProductName());
                    productNumbers.add(s.getBuyNumber());
                }
                intent.putStringArrayListExtra("title",productnames);
                intent.putIntegerArrayListExtra("numbers",productNumbers);

                intent.putExtra("sum",sum);
                startActivity(intent);
                finish();
            }
        };
        return subscriber;
    }

    private void setOrder() {
        subscription = NetWork.getMyApi().postToOrder(MainActivity.userId,new RequestBody(MainActivity.userId,orderAdapter.getList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscriber());
    }
}
