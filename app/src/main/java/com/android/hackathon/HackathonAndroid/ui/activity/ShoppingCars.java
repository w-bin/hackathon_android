package com.android.hackathon.HackathonAndroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.adapter.OrderAdapter;
import com.android.hackathon.HackathonAndroid.adapter.ShoppingCarAdapter;
import com.android.hackathon.HackathonAndroid.modle.RequestBody;
import com.android.hackathon.HackathonAndroid.modle.ShoppingCard;
import com.android.hackathon.HackathonAndroid.modle.ShoppingResult;
import com.android.hackathon.HackathonAndroid.network.NetWork;

import java.util.ArrayList;
import java.util.List;

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

public class ShoppingCars extends AppCompatActivity {
    public static  boolean isChoosed = false;
    private Subscription subscription;
    private ShoppingCarAdapter adapter;
    private static String TAG = "ShoppingCars";
    private static final int GETSHOPPINGCAR = 0;
    private static final int POSTTOORDERS = 1;
    private static final int PUTNUMBER = 2;
    public static ArrayList<ShoppingCard> list;
    @Bind(R.id.mylist)
    RecyclerView recyclerView;
    @Bind(R.id.choose_all)
    CheckBox checkBox;
    @Bind(R.id.submitbtn)
    Button btn;


    @OnClick(R.id.choose_all)
    void choose_all() {
        isChoosed = !isChoosed;
        adapter.choose_all(isChoosed);
    }

    @OnClick(R.id.submitbtn)
    void click() {
        jumpToOrder();
    }


    public void jumpToOrder() {
        Intent intent = new Intent(ShoppingCars.this, OrderActivity.class);
        list = adapter.getChoosed();
        startActivity(intent);
        finish();
    }

    public ArrayList<ShoppingCard> getlist() {

        return adapter.getChoosed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcar);
        ButterKnife.bind(this);
        adapter = new ShoppingCarAdapter();
        adapter.setOnItemClickListener(new ShoppingCarAdapter.OnItemClickListener() {
            @Override
            public void onClickAdd() {

            }

            @Override
            public void onClickSub() {

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        getShoppingCars();
    }


    private Subscriber<ShoppingResult> getSubscriber() {
        Subscriber<ShoppingResult> subscriber = new Subscriber<ShoppingResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onNext(ShoppingResult shoppingResult) {
//                Log.d(TAG,shoppingCard.);
                if (shoppingResult.getErr() != null) {
                    Toast.makeText(ShoppingCars.this, shoppingResult.getErr(), Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter.addAll(shoppingResult.getShoppingCardsList());


            }
        };
        return subscriber;
    }

    private void getShoppingCars() {
        subscription = NetWork.getMyApi().getShoppingCard(MainActivity.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscriber());
    }

}
