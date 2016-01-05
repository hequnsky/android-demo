package com.rmc.gaiya.activity;

import com.rmc.gaiya.utils.Check;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	private Button  loginbutton;// ��¼��ť
	private EditText username;// �û��������
	private EditText password;// ���������
	private String userNameStr;
	private String passwordStr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initID();
	}
	private void initID() {
		loginbutton = (Button) findViewById(R.id.loginbutton);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		loginbutton.setOnClickListener(this);
		
	}
	public void startLogin(){
		//�����½��ť
		userNameStr = username.getText().toString().trim();
		passwordStr = password.getText().toString().trim();
		//����
		if(Check.isEmpty(userNameStr)||Check.isEmpty(passwordStr)){
			Toast.makeText(this, "�˺Ż����벻��Ϊ��", Toast.LENGTH_SHORT).show();
		} else if(userNameStr.length()<5|| passwordStr.length() < 6){
			Toast.makeText(this, "�û�������λ������,����������...", Toast.LENGTH_SHORT).show();
			
		}else{
			Intent i=new Intent(LoginActivity.this,MainActivity.class);
			startActivity(i);
			finish();
		}	
		
		}
	@Override
	public void onClick(View v) {		
		startLogin();
	}
	
}

