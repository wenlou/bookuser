package com.example.bookeruser.fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.bookeruser.MainActivity;
import com.example.bookeruser.R;

/**
 * Created by sxj52 on 2016/4/11.
 */
public class FragmentMian extends FragmentActivity {
    protected static final String TAG = "MainActivity";
    private Context mContext;
    private ImageButton mNews,mConstact,mDeynaimic;
    private View mPopView;
    private View currentButton;


    private int mLevel=1;
    private PopupWindow mPopupWindow;
    private LinearLayout buttomBarGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        mContext=this;

        findView();
        init();
    }

    private void findView(){
        buttomBarGroup=(LinearLayout) findViewById(R.id.buttom_bar_group);
        mNews=(ImageButton) findViewById(R.id.buttom_news);
        mConstact=(ImageButton) findViewById(R.id.buttom_constact);
        mDeynaimic=(ImageButton) findViewById(R.id.buttom_deynaimic);

    }

    private void init() {
        mNews.setOnClickListener(newsOnClickListener);
        mConstact.setOnClickListener(constactOnClickListener);
        mDeynaimic.setOnClickListener(deynaimicOnClickListener);


        mNews.performClick();

        mPopupWindow = new PopupWindow(mPopView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);

    }



    private View.OnClickListener newsOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            NewsFatherFragment newsFatherFragment=new NewsFatherFragment();
            ft.replace(R.id.fl_content, newsFatherFragment, FragmentMian.TAG);
            ft.commit();
            setButton(v);

        }
    };

    private View.OnClickListener constactOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ConstactFatherFragment constactFatherFragment=new ConstactFatherFragment();
            ft.replace(R.id.fl_content, constactFatherFragment,FragmentMian.TAG);
            ft.commit();
            setButton(v);

        }
    };

    private View.OnClickListener deynaimicOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            DynamicFragment dynamicFragment=new DynamicFragment();
            ft.replace(R.id.fl_content, dynamicFragment,FragmentMian.TAG);
            ft.commit();
            setButton(v);

        }
    };



    private void setButton(View v){
        if(currentButton!=null&&currentButton.getId()!=v.getId()){
            currentButton.setEnabled(true);
        }
        v.setEnabled(false);
        currentButton=v;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_MENU){
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b0000000")));
            mPopupWindow.showAtLocation(buttomBarGroup, Gravity.BOTTOM, 0, 0);
            mPopupWindow.setAnimationStyle(R.style.app_pop);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);
            mPopupWindow.update();
        }
        return super.onKeyDown(keyCode, event);

    }

}
