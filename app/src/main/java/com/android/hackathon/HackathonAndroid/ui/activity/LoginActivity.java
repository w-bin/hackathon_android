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
import com.android.hackathon.HackathonAndroid.modle.UserResult;
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
    private Subscriber<UserResult> subscriber;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        loginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        username = usernametv.getText().toString();
        password = passwordtv.getText().toString();
        Log.d(TAG, "click" + username + "  " + password);
        requestBody = new RequestBody(username, password);
        subscription = NetWork.getMyApi()
                .login(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSubscriber());
    }

    public Subscriber<UserResult> getSubscriber() {
        subscriber = new Subscriber<UserResult>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "completed");
            }

            @Override
            public void onError(Throwable e) {
                if (e.toString().contains("net"))
                    Toast.makeText(LoginActivity.this, "登录失败,请检查网络是否连接", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, "账号或者密码错误,请重新输入", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
            }

            @Override
            public void onNext(UserResult userResult) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.d(TAG, userResult.isResult() + "  " + userResult.getErr() + "  " + userResult.getUserId());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        };
        return subscriber;
    }
}
