package com.example.bookeruser.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.bookeruser.R;

public abstract class BaseActivty extends Activity {
	private Button bt_left;
	private TextView tv_title;
	private Button bt_right;
	private LinearLayout ll_child_content;
	private FrameLayout fl_titlebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initView();
		setOnclickListenr();
	}

	private void setOnclickListenr() {
		bt_left.setOnClickListener(mOnclicklistner);
		bt_right.setOnClickListener(mOnclicklistner);

	}

	OnClickListener mOnclicklistner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.bt_left:
					leftButtonClick();
					break;
				case R.id.bt_right:
					rightButtonClick();
					break;
				default:
					break;
			}

		}
	};

	/**
	 * 初始化view
	 */
	private void initView() {
		bt_left = (Button) findViewById(R.id.bt_left);
		tv_title = (TextView) findViewById(R.id.tv_title);
		bt_right = (Button) findViewById(R.id.bt_right);
		ll_child_content = (LinearLayout) findViewById(R.id.ll_child_content);
		fl_titlebar=(FrameLayout) findViewById(R.id.fl_titlebar);
		View child = setContentView();
		if (child != null) {

			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			// 添加布局文件
			ll_child_content.addView(child, params);
		}

	}

	/**
	 * 加载布局文件
	 *
	 * @return
	 */
	public abstract View setContentView();

	/**
	 * 设置标题
	 *
	 * @param tiltle要设置的标题内容
	 */
	public void setTitle(String tiltle) {
		tv_title.setText(tiltle);
	}

	/**
	 * 设置左边按钮可见状态
	 *
	 * @param visibility
	 */
	public void setLeftButon(int visibility) {
		bt_left.setVisibility(visibility);
	}
	/**
	 * 设置标题栏是否隐藏
	 * @param visibility
	 */
	public void setTileBar(int visibility){
		fl_titlebar.setVisibility(visibility);
	}
	/**
	 * 设置右边按钮可见状态
	 *
	 * @param visibility
	 */
	public void setRightButon(int visibility) {
		bt_right.setVisibility(visibility);
	}

	/**
	 * 点击右边按钮，由子类具体实现
	 */
	public abstract void rightButtonClick();

	/**
	 * 点击左边按钮，由子类具体实现
	 */
	public abstract void leftButtonClick();

}
