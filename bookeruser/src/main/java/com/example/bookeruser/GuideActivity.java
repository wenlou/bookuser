package com.example.bookeruser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.bookeruser.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
    private ViewPager vpGuide;
    private List<ImageView> imageViewList;//引导页的ImageView集合
    private LinearLayout llPointGroup;//点的集合
    private View viewRedPoint;

    private int pointSpace;//两点之间的间距
    private Button btnStatr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initViews();

    }

    /**
     * ViewPager的适配器
     */
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }

    /**
     * 初始化界面
     */
    private void initViews() {
        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        viewRedPoint = findViewById(R.id.view_red_point);
        btnStatr = (Button) findViewById(R.id.btn_start);

        //设置"开始体验"按钮的监听事件
        btnStatr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录已经展示过新手引导页面
                SharePreferenceUtils.putBoolean(GuideActivity.this, "IS_USER_GUIDE_SHOWED", true);
                //跳转到主页面
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

        initDatas();

        //给ViewPager设置适配器
        vpGuide.setAdapter(new GuideAdapter());

        //measure -> layout -> draw
        //获得视图树观察者，观察当整个布局的layout的事件
        viewRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //此方法只需要执行一次就可以，把当前的监听事件从视图树中移除掉，以后就不会在回调此事件了
                viewRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //点的距离 = 第1个点的左边 - 第0个点的左边
                pointSpace = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
                System.out.println("两点之间的距离:" + pointSpace);

//                System.out.println("onGlobalLayout");

            }
        });

        //设置ViewPager页面切换变化的监听事件
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * 页面滑动监听
             * @param position 当前选中的位置
             * @param positionOffset 偏移百分比
             * @param positionOffsetPixels 页面偏移长度
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                System.out.println("位置:" + position + ",位置移动的百分比:" + positionOffset + ",位置移动的像素:" + positionOffsetPixels);
                int leftMargin = (int) (pointSpace * positionOffset + pointSpace * position);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
                lp.leftMargin = leftMargin;
                viewRedPoint.setLayoutParams(lp);
            }

            //page页面被选中
            @Override
            public void onPageSelected(int position) {
                if (position == imageViewList.size() - 1) {//选中最后一个页面
                    btnStatr.setVisibility(View.VISIBLE);//显示"开始体验"按钮
                } else {
                    btnStatr.setVisibility(View.INVISIBLE);
                }

            }

            //page页面滚动状态的变化
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initDatas() {
        imageViewList = new ArrayList<>();
        //初始化引导页的3个页面
        int[] idsImageView = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
        for (int i = 0; i < idsImageView.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(idsImageView[i]);//设置引导页背景
            imageViewList.add(imageView);

            //初始化引导页的小圆点
            View point = new View(this);
            point.setBackgroundResource(R.drawable.guide_point_shape);//设置引导页默认圆点
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);//设置圆点的大小
            if (i > 0) {
                params.leftMargin = 10;
            }
            llPointGroup.addView(point, params);//将圆点添加到线性布局中

        }
    }

}
