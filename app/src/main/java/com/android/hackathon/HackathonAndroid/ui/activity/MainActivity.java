package com.android.hackathon.HackathonAndroid.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

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
    public  static int userId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",-1);

        isLogin  = intent.getBooleanExtra("isLogin",false);

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
            Intent intent;
            if(MainActivity.isLogin) {
                intent = new Intent(MainActivity.this, ShoppingCars.class);
            }else{
                intent = new Intent(MainActivity.this,LoginActivity.class);
            }
            startActivity(intent);

        }
        else if(id == R.id.myorders){
            //跳转到我的订单
        }

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

}
