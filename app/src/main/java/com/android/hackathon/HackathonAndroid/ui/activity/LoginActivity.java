package com.android.hackathon.HackathonAndroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.modle.RequestBody;
import com.android.hackathon.HackathonAndroid.modle.Result;
import com.android.hackathon.HackathonAndroid.network.NetWork;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zzbpc on 2016/11/19.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.username)
    TextView usernametv;
    @Bind(R.id.password)
    TextView passwordtv;
    @Bind(R.id.login_btn)
    Button loginbtn;

    private Subscription subscription;
    private RequestBody requestBody;
    private String username;
    private String password;
    private Subscriber<Result> subscriber;
    private static final String TAG ="LoginActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ButterKnife.bind(this);
        loginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        username = usernametv.getText().toString();
        password = passwordtv.getText().toString();
        Log.d(TAG,"click" + username+"  "+password);
        requestBody = new RequestBody(username,password);
        Toast.makeText(LoginActivity.this,username+ "  "+password,Toast.LENGTH_SHORT);
        subscription = NetWork.getMyApi()
                              .login(requestBody)
                              .subscribeOn(Schedulers.io())
                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribe(getSubscriber());
    }

    public Subscriber<Result> getSubscriber(){
        subscriber = new Subscriber<Result>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(LoginActivity.this,"登录失败,请检查网络和账号密码",Toast.LENGTH_SHORT);
                Log.d(TAG,e.toString());

            }

            @Override
            public void onNext(Result result) {
                Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT);
                Log.d(TAG,result.isResult()+"  "+result.getErr()+"  "+result.getUserId());
                Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        };
        return subscriber;
    }
}
