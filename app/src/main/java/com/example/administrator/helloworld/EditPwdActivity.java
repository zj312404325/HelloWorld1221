package com.example.administrator.helloworld;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.helloworld.common.TopActivity;
import com.example.administrator.helloworld.util.CommonUtil;
import com.example.administrator.helloworld.util.XUtilsHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.activity_edit_pwd)
public class EditPwdActivity extends TopActivity {

	@ViewInject(R.id.submitbtn)
	private Button submitbtn;
	
	@ViewInject(R.id.et_newpass)
	private EditText et_newpass;
	@ViewInject(R.id.et_repass)
	private EditText et_repass;
	@ViewInject(R.id.et_oldpass)
	private EditText et_oldpass;

	/**
	 * EditText有内容的个数
	 */
	private int mEditTextHaveInputCount = 0;
	/**
	 * EditText总个数
	 */
	private final int EDITTEXT_AMOUNT = 3;
	/**
	 * 编辑框监听器
	 */
	private TextWatcher mTextWatcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		super.title.setText("修改密码");
		init();
		progressDialog.hide();
	}

	@Event(R.id.submitbtn)
	private void submitclick(View v){
		if(et_oldpass.getText().toString().length() <1){
    		CommonUtil.alter("老密码不能为空！");return;
    	}
		if(et_newpass.getText().toString().length() <6){
    		CommonUtil.alter("新密码必须大于6位！");return;
    	}
    	if(!et_newpass.getText().toString().equals(et_repass.getText().toString())){
    		CommonUtil.alter("确认密码不相同");return;
    	}
    	Map<String, String> maps= new HashMap<String, String>();
		maps.put("serverKey", super.serverKey);
		maps.put("newpass", et_newpass.getText().toString());
		maps.put("repass", et_repass.getText().toString());
		maps.put("oldpass", et_oldpass.getText().toString());
		XUtilsHelper.getInstance().post("app/updatePass.htm", maps,new XUtilsHelper.XCallBack(){

			@SuppressLint("NewApi")
			@Override
			public void onResponse(String result)  {
				progressDialog.hide();
				JSONObject res;
				try {
					res = new JSONObject(result);
					setServerKey(res.get("serverKey").toString());
					if(res.get("d").equals("n")){
						CommonUtil.alter(res.get("msg").toString());
					}
					else{
						startActivity(new Intent(getApplicationContext(),EditPwdResActivity.class));
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

	private void init() {
		mTextWatcher = new TextWatcher() {
			/** 改变前*/
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

				/** EditText最初内容为空 改变EditText内容时 个数加一*/
				if (TextUtils.isEmpty(charSequence)) {

					mEditTextHaveInputCount++;
					/** 判断个数是否到达要求*/
					if (mEditTextHaveInputCount == EDITTEXT_AMOUNT) {
						submitbtn.setBackgroundColor(submitbtn.getResources().getColor(R.color.login_back_blue));
						submitbtn.setTextColor(submitbtn.getResources().getColor(R.color.login_text_white));
						submitbtn.setEnabled(true);
					}
				}
			}

			/** 内容改变*/
			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

				/** EditText内容改变之后 内容为空时 个数减一 按钮改为不可以的背景*/
				if (TextUtils.isEmpty(charSequence)) {
					mEditTextHaveInputCount--;
					submitbtn.setBackgroundColor(submitbtn.getResources().getColor(R.color.login_back_gray));
					submitbtn.setTextColor(submitbtn.getResources().getColor(R.color.login_text_gray));
					submitbtn.setEnabled(false);
				}

			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		};

		/** 需要监听的EditText add*/
		et_newpass.addTextChangedListener(mTextWatcher);
		et_repass.addTextChangedListener(mTextWatcher);
		et_oldpass.addTextChangedListener(mTextWatcher);

	}

}
