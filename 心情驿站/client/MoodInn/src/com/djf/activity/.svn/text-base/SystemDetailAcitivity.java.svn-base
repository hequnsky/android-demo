package com.djf.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.djf.bean.BasePrivateLetter;
import com.djf.bean.PrivateLetter;
import com.djf.bean.PullTheBlackBean;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.loadimage.AsynImageLoader;
import com.djf.moodinn.R;
import com.djf.util.HttpUtil;
import com.djf.view.CircleImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SystemDetailAcitivity extends BaseActivity implements
		OnClickListener {

	private CircleImageView system_detail_back;
	private TextView text_system_name_title;
	private TextView text_system_blacklist;
	private CircleImageView system_detail_user;
	private TextView text_system_name;
	private TextView text_system_user_sex;
	private TextView text_system_user_city;
	private TextView text_system_detail_content;
	private PrivateLetter privateLetter;
	private AsynImageLoader asynImageLoader;
	private Context context;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_system_detail);
		context = this;
		initData();
		initView();
		setViewData();
	}

	private void initView() {
		// TODO Auto-generated method stub
		asynImageLoader=new AsynImageLoader();
		system_detail_back = (CircleImageView) findViewById(R.id.system_detail_back);
		text_system_name_title = (TextView) findViewById(R.id.text_system_name_title);
		text_system_blacklist = (TextView) findViewById(R.id.text_system_blacklist);
		system_detail_user = (CircleImageView) findViewById(R.id.system_detail_user);
		text_system_name = (TextView) findViewById(R.id.text_system_name);
		text_system_user_sex = (TextView) findViewById(R.id.text_system_user_sex);
		text_system_user_city = (TextView) findViewById(R.id.text_system_user_city);
		text_system_detail_content = (TextView) findViewById(R.id.text_system_detail_content);

		system_detail_back.setOnClickListener(this);
		text_system_blacklist.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		privateLetter = (PrivateLetter) getIntent().getExtras()
				.getSerializable("privateLetter");
	}

	private void setViewData() {
		// TODO Auto-generated method stub
		if (privateLetter != null) {
			text_system_name_title.setText(privateLetter.getUser().getName());
			if (!TextUtils.isEmpty(privateLetter.getUser().getImage())) {
				asynImageLoader.showImageAsyn(system_detail_user,
						HttpConstants.HTTP_REQUEST
								+ privateLetter.getUser().getImage(),
						R.drawable.ic_launcher);
			}
			text_system_name.setText(privateLetter.getUser().getName());
			text_system_user_sex.setText(privateLetter.getUser().getSex()
					.equals("0") ? "男" : "女");
			text_system_user_city.setText(privateLetter.getUser().getCity());
			text_system_detail_content.setText(privateLetter
					.getMessage_content());
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.system_detail_back:
			finish();
			break;

		case R.id.text_system_blacklist:
			loadData();
			break;
		default:
			break;
		}
	}
	
	
	private void loadData() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (privateLetter != null)
			params.add(new BasicNameValuePair("sendid", privateLetter.getSend_id()));
		params.add(new BasicNameValuePair("state", "1"));
		HttpUtil.doPost(HttpConstants.HTTP_PULL_THE_BLACK, params,
				new ResultCallback() {

					@Override
					public void getReslt(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Type type = new TypeToken<PullTheBlackBean>() {
						}.getType();
						PullTheBlackBean pullTheBlackBean = gson.fromJson(result, type);
						if (pullTheBlackBean != null) {
							Toast.makeText(context, pullTheBlackBean.getMessage(), Toast.LENGTH_LONG).show();
						}
					}
				});
	}

}
