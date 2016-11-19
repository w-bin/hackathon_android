package com.android.hackathon.HackathonAndroid.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.android.hackathon.HackathonAndroid.R;
import com.android.hackathon.HackathonAndroid.ui.fragment.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    public static boolean isLogin =  false;
    public static final  int ROOT = 1;
    public static final int guest = 0;
    public static  int right  =guest;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getFragmentManager().beginTransaction().replace(R.id.frame_layout,new MainFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.shoppingcard){
            //跳转到购物车
        }
        else if(id == R.id.myorders){
            //跳转到我的订单
        }

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

}
