package com.android.hackathon.HackathonAndroid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.hackathon.HackathonAndroid.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzbpc on 2016/11/20.
 */

public class OrderDetailActivity extends AppCompatActivity {

    @Bind(R.id.order_title)
    TextView textView;
    @Bind(R.id.detail)
    TextView content;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderdetail_item);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String detail = "";
        ArrayList<String> name = intent.getStringArrayListExtra("title");
        ArrayList<Integer> numbers = intent.getIntegerArrayListExtra("numbers");
        for(int i=0;i<name.size();i++){
            detail +=(i+1)+"."+name.get(i) +"  "+numbers.get(i)+"件 \n";

        }
        detail += "  合计 "+intent.getIntExtra("sum",0);
        content.setText(detail);

    }
}
