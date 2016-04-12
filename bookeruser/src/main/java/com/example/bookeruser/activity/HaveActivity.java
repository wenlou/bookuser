package com.example.bookeruser.activity;

import android.os.Bundle;
import android.view.View;

import com.example.bookeruser.R;

/**
 * Created by sxj52 on 2016/4/11.
 */
public class HaveActivity extends  BaseActivty{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRightButon(View.GONE);
        setTitle("");
    }

    @Override
    public View setContentView() {
        View view=View.inflate(this, R.layout.activity_login,null);
        return view;
    }

    @Override
    public void rightButtonClick() {

    }

    @Override
    public void leftButtonClick() {
        finish();
    }
}
