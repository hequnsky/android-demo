package com.djf.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ant.liao.GifView;
import com.djf.bean.BaseBean;
import com.djf.bean.UserBean;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.moodinn.R;
import com.djf.util.AppUtils;
import com.djf.util.HttpUtil;
import com.djf.util.MoodApplication;
import com.djf.view.EditTextWithDel;
import com.djf.view.TitleBar;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout tvRegister;
	
	private Button btnLogin;
	
	private EditTextWithDel etPhone;
	
	private EditTextWithDel etPassWord;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		MoodApplication.getInstance().addActivity(this);
		
		setContentView(R.layout.userlogin);
		
		initViews();
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		TitleBar titleBar=(TitleBar)findViewById(R.id.TitleBar);
		 if(!"0".equals(String.valueOf(new SharedPreferenceDb(LoginActivity.this).getChangeTheme()))){
			 titleBar.setBackgroundColor(new SharedPreferenceDb(LoginActivity.this).getChangeTheme());
		 }else{
			 titleBar.setBackgroundColor(getResources().getColor(R.color.red));
		 }
		 titleBar.showCenterTitle("用户登录");
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		
		 tvRegister=(RelativeLayout)findViewById(R.id.newUserRegister);
		 tvRegister.setOnClickListener(this);
		 
		 etPhone=(EditTextWithDel)findViewById(R.id.accounts);
		 etPassWord=(EditTextWithDel)findViewById(R.id.password);
		 
		 
		 
		 btnLogin=(Button)findViewById(R.id.login);
		 btnLogin.setOnClickListener(this);
		 
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==tvRegister){
			SkipActivityforClass(LoginActivity.this, RegisterActivity.class);
			if(new SharedPreferenceDb(LoginActivity.this).getAnimation()==true){
				overridePendingTransition(R.anim.fade, R.anim.hold);
			}
			
		}else if(view==btnLogin){
			
			if(!etPhone.getText().toString().isEmpty()){
				
				if(!etPassWord.getText().toString().isEmpty()){
					
					
					if(AppUtils.checkNetwork(LoginActivity.this)==true){
						//SkipActivityforClass(LoginActivity.this, MainActivity.class);
						
						List<NameValuePair> allP=new ArrayList<NameValuePair>();
						allP.add(new BasicNameValuePair("phone", etPhone.getText().toString().trim()));
						allP.add(new BasicNameValuePair("password", etPassWord.getText().toString().trim()));
						
						Map<String,String> params=new HashMap<String, String>();
						params.put("phone", etPhone.getText().toString().trim());
						params.put("password", etPassWord.getText().toString().trim());
						
						initProgressDialog();
						
						HttpUtil.doPost(HttpConstants.HTTP_LOGIN, allP, new ResultCallback() {
							
							@Override
							public void getReslt(String result) {
								// TODO Auto-generated method stub
								if(!result.isEmpty() && !"1".equals(result)){
									BaseBean b=JSON.parseObject(result, BaseBean.class);
									
									if("0".equals(b.getRespcode())){
										
										UserBean u=JSON.parseObject(b.getData(), UserBean.class);
										new SharedPreferenceDb(LoginActivity.this).setUserId(u.getId());
										new SharedPreferenceDb(LoginActivity.this).setName(u.getName());
										Intent intent=new Intent();
										intent.putExtra("user", u);
										intent.setClass(LoginActivity.this, MainActivity.class);
										startActivity(intent);
										
										if(new SharedPreferenceDb(LoginActivity.this).getAnimation()==true){
											overridePendingTransition(R.anim.slide_left,
													R.anim.slide_right);
										}
										close();
									}else{
										showToast("用户名或密码错误");
										close();
									}
								}else{
									Toast.makeText(LoginActivity.this, "服务器响应失败", Toast.LENGTH_LONG).show();
									close();
								}
							}
						});
						
						
					}else{
						showToast("亲，您还没有联网了!");
					}
					
				}else{
					showToast("密码不能为空");
				}
			}else{
				showToast("用户名不能为空");
			}
			
			

			
			
			
			
		}
	}
	
	public void showToast(String str){
		Toast.makeText(LoginActivity.this, str, Toast.LENGTH_LONG).show();
	}
	
	
}
