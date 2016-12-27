package com.efly.map.keydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.efly.map.keydemo.util.ToastUtil;

/**
 * Created by danfeng.wang on 2016/12/26.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void popkeyboard(View view){
        Intent intent=new Intent(MainActivity.this,KeyDemo.class);
        startActivityForResult(intent,0x11);
        overridePendingTransition(R.anim.showanim,R.anim.dismissanim);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            String text=data.getExtras().getString("data");
            ToastUtil toastUtil=new ToastUtil(getApplicationContext());
            toastUtil.showToastCenter(text);

        }
    }
}
