package com.example.administrator.helloworld.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.helloworld.CartActivity;
import com.example.administrator.helloworld.CategoryActivity;
import com.example.administrator.helloworld.DiscoveryActivity;
import com.example.administrator.helloworld.IndexActivity;
import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.UserInfoActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

public class ButtomTapActivity extends UserActivity {

	@ViewInject(R.id.buttom_tap_1)
	public LinearLayout linearLayout1 = null;
	
	@ViewInject(R.id.buttom_tap_2)
	public LinearLayout linearLayout2 = null;
	
	@ViewInject(R.id.buttom_tap_3)
	public LinearLayout linearLayout3 = null;
	
	@ViewInject(R.id.buttom_tap_4)
	public LinearLayout linearLayout4 = null;
	
	@ViewInject(R.id.buttom_tap_5)
	public LinearLayout linearLayout5 = null;
	
	@ViewInject(R.id.buttom_tap_img_1)
	public ImageView imageView1 = null;
	
	@ViewInject(R.id.buttom_tap_img_2)
	public ImageView imageView2 = null;
	
	@ViewInject(R.id.buttom_tap_img_3)
	public ImageView imageView3 = null;
	
	@ViewInject(R.id.buttom_tap_img_4)
	public ImageView imageView4 = null;
	
	@ViewInject(R.id.buttom_tap_img_5)
	public ImageView imageView5 = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Event(value=R.id.buttom_tap_1,type=OnTouchListener.class)
	private boolean btn1(View v, MotionEvent event){
		if (event.getAction() == event.ACTION_UP) {
			startActivity(new Intent(getApplicationContext(),IndexActivity.class));
			return false;
		}
		return true;
	}
	
	@Event(value=R.id.buttom_tap_2,type=OnTouchListener.class)
	private boolean btn2(View v, MotionEvent event){
		if (event.getAction() == event.ACTION_UP) {
			startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
			return false;
		}
		return true;
	}
	
	@Event(value=R.id.buttom_tap_3,type=OnTouchListener.class)
	private boolean btn3(View v, MotionEvent event){
		if (event.getAction() == event.ACTION_UP) {
			startActivity(new Intent(getApplicationContext(),DiscoveryActivity.class));
			return false;
		}
		return true;
	}
	
	@Event(value=R.id.buttom_tap_4,type=OnTouchListener.class)
	private boolean btn4(View v, MotionEvent event){
		if (event.getAction() == event.ACTION_UP) {
			startActivity(new Intent(getApplicationContext(),CartActivity.class));
			return false;
		}
		return true;
	}
	
	@Event(value=R.id.buttom_tap_5,type=OnTouchListener.class)
	private boolean btn5(View v, MotionEvent event){
		if (event.getAction() == event.ACTION_UP) {
			startActivity(new Intent(getApplicationContext(),UserInfoActivity.class));
			return false;
		}
		return true;
	}
	

	
}
