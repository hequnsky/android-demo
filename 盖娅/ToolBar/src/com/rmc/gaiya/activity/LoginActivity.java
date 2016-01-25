package com.rmc.gaiya.activity;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.rmc.gaiya.bean.LoginBean;
import com.rmc.gaiya.common.Config;
import com.rmc.gaiya.common.MyApplication;
import com.rmc.gaiya.utils.Check;
import com.rmc.gaiya.utils.SharedPreferenceUtil;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	String url=Config.LoginURL;
	String result;
	OkHttpClient client = new OkHttpClient();
	Response response = null;
	private Button  loginbutton;// 登录按钮
	private EditText username;// 用户名输入框
	private EditText password;// 密码输入框
	private String userNameStr;
	private String passwordStr;
	private ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initID();
		
		
	}
	/**
	 * @param null
	 * 实现自动登陆
	 * 
	 */
	private void initCache() {
		SharedPreferenceUtil.set(LoginActivity.this, "Login", "isFist", "flase");
		String name=SharedPreferenceUtil.get(getApplicationContext(), "Login", "name");
		String pw=SharedPreferenceUtil.get(getApplicationContext(), "Login", "pw");
		Log.i("63", pw);
		Log.i("63", name);
		if(Check.isEmpty(name)||Check.isEmpty(pw)){
			Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
		}else{
			username.setText(name);
			password.setText(pw);
			startLogin();
		}

	}
	private void initID() {
		loginbutton = (Button) findViewById(R.id.loginbutton);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		loginbutton.setOnClickListener(this);
		String isFist=SharedPreferenceUtil.get(getApplicationContext(), "Login", "isFist");
		Log.i("INFO", isFist);
		if (isFist=="true") {	
			SharedPreferenceUtil.set(LoginActivity.this, "Login", "isFist", "false");
		}else{
		    initCache();
		}
	

	   
		
		
		
		
	}
	public void startLogin(){
		//点击登陆按钮
		userNameStr = username.getText().toString().trim();
		passwordStr = password.getText().toString().trim();
		//正则
		if(Check.isEmpty(userNameStr)||Check.isEmpty(passwordStr)){
			Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
		} else if(userNameStr.length()<5|| passwordStr.length() <5){
			Toast.makeText(this, "用户名密码位数不够,请重新输入...", Toast.LENGTH_SHORT).show();
			
		}else {	
			new LoginAsync().execute(getUrl());
	
		}	
		
		}
	@Override
	public void onClick(View v) {		
		startLogin();
	}
 class LoginAsync extends AsyncTask<String, Void, String>{

	 @Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog =new ProgressDialog(LoginActivity.this);
		dialog.setMessage("正在登陆");
		dialog.show();
		
	}

	@SuppressLint("ShowToast")
	@Override
	protected String doInBackground(String... params) {
		Request request = new Request.Builder().url(getUrl()).build();
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response.isSuccessful()) {
			try {
				result = response.body().string();
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			throw new IOException("Unexpected code " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return result;
	}
	@SuppressLint("ShowToast")
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (result!=null) {
			try {
				
				JSONObject objc=new JSONObject(result);
				String Message=objc.getString("Message");
				Log.i("118",Message);
				JSONObject obj=new JSONObject(Message);
				int State=obj.getInt("State");
				Log.i("121",State+"");
				JSONObject Param=obj.getJSONObject("Param");
				String Name=Param.getString("Name");
				Log.i("124",Name);
				if (State==200&&Name.equals("Successful")) {
					String Content=	Param.getString("Content");
					Log.i("127",Content);
					JSONArray arr=new JSONArray(Content);
					for (int i = 0; i < arr.length(); i++) {
						JSONObject object=arr.getJSONObject(i);							
						Gson gson=new Gson();
						LoginBean login=gson.fromJson(object.toString(), LoginBean.class);
						MyApplication app=(MyApplication) getApplication();
						app.setLoginBean(login);
					}
					SharedPreferenceUtil.set(LoginActivity.this, "Login", "name", userNameStr);
					SharedPreferenceUtil.set(LoginActivity.this, "Login", "pw", passwordStr);
					Intent i=new Intent(LoginActivity.this,DeviceActivity.class);
					startActivity(i);
					finish();	
					
				}
				
			} catch (JSONException e) {
				Toast.makeText(LoginActivity.this, "解析错误...",
						Toast.LENGTH_SHORT).show();
				dialog.dismiss();
				e.printStackTrace();
			}
		}
		else {
			Toast.makeText(LoginActivity.this,
					"登入失败,请查看网络和用户名密码,然后请重新登入...", Toast.LENGTH_SHORT)
					.show();
			dialog.dismiss();
		}
		
	}
	
	 
 }
 public String  getUrl(){
	 StringBuilder builder=new StringBuilder();
	 builder.append(url).append("?").append("MLoginName").append("=").append(userNameStr).append("&").append("MLoginPwd").append("=").append(passwordStr);
	 return builder.toString();
	 
//		String url = "http://192.168.1.226:89/api/MemberLogin?MLoginName=Admin&MLoginPwd=admin";
 }
	
}

