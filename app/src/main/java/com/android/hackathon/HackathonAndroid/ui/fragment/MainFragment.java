package com.android.hackathon.HackathonAndroid.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.adapter.LeftViewAdapter;
import com.android.hackathon.HackathonAndroid.adapter.RightViewAdapter;
import com.android.hackathon.HackathonAndroid.modle.Product;
import com.android.hackathon.HackathonAndroid.modle.ProductKind;
import com.android.hackathon.HackathonAndroid.modle.ProductKindResult;
import com.android.hackathon.HackathonAndroid.modle.ProductResult;
import com.android.hackathon.HackathonAndroid.network.NetWork;
import com.android.hackathon.HackathonAndroid.ui.activity.ProductDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class MainFragment extends Fragment {

    @Bind(R.id.left_list)
    RecyclerView leftview;
    @Bind(R.id.right_list)
    RecyclerView rightview;
    private static final String TAG = "MainFragment";
    private LeftViewAdapter leftViewAdapter;
    private RightViewAdapter rightViewAdapter;

    private Subscription leftsubscription;
    private Subscription rightsubscription;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        leftViewAdapter = new LeftViewAdapter();
        rightViewAdapter = new RightViewAdapter();

        leftview.setAdapter(leftViewAdapter);
        rightview.setAdapter(rightViewAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        leftview.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        rightview.setLayoutManager(layoutManager1);

        leftViewAdapter.setOnItemClickListener(new LeftViewAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(ProductKind kind) {
                Log.d(TAG,kind.getKindId()+"  "+kind.getKindName()+"  ");
                if(kind.getKindName().equals("全部"))
                    getAllProducts();
                else
                getOneKindProducts(kind.getKindId());
            }
        });
        rightViewAdapter.setOnItemClickListener(new RightViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Log.d(TAG,product.getKindId()+"  "+product.getName()+"  "+product.getPrice());
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("productName",product.getName());
                bundle.putString("productPrice",product.getPrice());
                bundle.putInt("productRemain",product.getNumber());
                bundle.putString("productImage",product.getImgurl());
                bundle.putString("productDes",product.getDescribe());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getProductKind();
        getAllProducts();

        return view;
    }

    public void getProductKind() {
        Toast.makeText(getActivity(), "getkind", Toast.LENGTH_SHORT);
        Log.d(TAG, "get kind");
        leftsubscription = NetWork.getMyApi()
                .getKinds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getLeftSubscriber());
    }

    public void getAllProducts() {
        Log.d(TAG, "get all products");
        rightViewAdapter.clearList();
        rightsubscription = NetWork.getMyApi()
                .getAllProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getRightSubScriber());
    }

    public void getOneKindProducts(int kindId) {
        rightViewAdapter.clearList();
        rightsubscription = NetWork.getMyApi()
                .getOneKindProducts(kindId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getRightSubScriber());
    }


    private Subscriber<ProductKindResult> getLeftSubscriber() {
        Subscriber<ProductKindResult> subscriber = new Subscriber<ProductKindResult>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
                Toast.makeText(getActivity(), "网络错误,请检查网络", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(ProductKindResult productKindResult) {
                leftViewAdapter.addItem(new ProductKind(0,"全部"));
                leftViewAdapter.addAll(productKindResult.getKindList());

            }
        };
        return subscriber;
    }

    private Subscriber <ProductResult> getRightSubScriber(){
        Subscriber<ProductResult> subscriber = new Subscriber<ProductResult>() {
            @Override
            public void onCompleted() {
                Log.d(TAG,"complete");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,e.toString());

            }

            @Override
            public void onNext(ProductResult productResult) {
                Log.d(TAG,productResult.getErr()+"  "+productResult.isResult()+"  "+productResult.getProductsList().size());
                rightViewAdapter.addAll(productResult.getProductsList());
            }
        };
        return subscriber;
    }

}
