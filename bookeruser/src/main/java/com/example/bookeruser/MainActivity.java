package com.example.bookeruser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookeruser.activity.HaveActivity;
import com.example.bookeruser.activity.LodingActivity;
import com.example.bookeruser.fragment.FragmentMian;


public class MainActivity extends Activity {

    private Button bt_have;
    private Button bt_loding;
    private TextView tv_start;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initView();
    }

    private void initView() {
        bt_have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(MainActivity.this,HaveActivity.class);
                startActivity(intent);
            }
        });
        bt_loding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(MainActivity.this,LodingActivity.class);
                startActivity(intent);
            }
        });
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(MainActivity.this,FragmentMian.class);
                startActivity(intent);
            }
        });
    }

    private void findView(){
        bt_have=(Button) findViewById(R.id.bt_have);
        bt_loding=(Button) findViewById(R.id.bt_loding);
        tv_start=(TextView) findViewById(R.id.tv_start);
    }



}
