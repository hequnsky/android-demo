package com.djf.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.djf.adapter.FriendMessageAdapter;
import com.djf.bean.BaseBean;
import com.djf.bean.FriendMessageBean;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.db.SharedPreferenceDb;
import com.djf.moodinn.R;
import com.djf.util.HttpUtil;
import com.djf.util.TimeUtil;
import com.djf.view.TitleBar;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class FriendsMessageActivity extends BaseActivity {

	private TitleBar titleBar;
	private String userId;
	private Button btnSend;
	private EditText etContent;
	private ListView messageListView;
	private List<FriendMessageBean> allFriendBean;
	private FriendMessageAdapter adapter=null;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.system_msg);
		initData();
		initViews();
	}
	
	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();
		titleBar=(TitleBar)findViewById(R.id.friendsMessageTitleBar);
		titleBar.showLeft("悄悄话", getResources().getDrawable(R.drawable.app_back), new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		btnSend=(Button)findViewById(R.id.btnSend);
		etContent=(EditText)findViewById(R.id.etMessageContent);
		
		
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(!etContent.getText().toString().isEmpty()){
					
					if(allFriendBean.size()>=0&&allFriendBean.size()<=1){
						loadHttpGetData();
					}else{
						Toast.makeText(FriendsMessageActivity.this, "亲，频繁的发送悄悄话,很有多可能会被对方屏蔽掉,视为骚扰哦！", Toast.LENGTH_LONG).show();
					}
					
				}else{
					Toast.makeText(FriendsMessageActivity.this, "亲，你还没有填写内容了，不能发送哦", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		
	}
	
	public void loadHttpGetData(){
		
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sendid", new SharedPreferenceDb(FriendsMessageActivity.this).getUserId()));
		params.add(new BasicNameValuePair("message", etContent.getText().toString()));
		params.add(new BasicNameValuePair("acceptid", userId));
		initProgressDialog();
		HttpUtil.doPost(HttpConstants.HTTP_SEND_MESSAGE, params, new ResultCallback() {
			
			@Override
			public void getReslt(String result) {
				// TODO Auto-generated method stub
				BaseBean b=JSON.parseObject(result, BaseBean.class);
				FriendMessageBean  friendBean=new FriendMessageBean();
				friendBean.setAccept_name("小林");
				friendBean.setMessage_content(etContent.getText().toString().trim());
				friendBean.setSend_time(TimeUtil.getCurrentTime());
				if(b.getRespcode().equals("0")){
					friendBean.setSend_code("成功");
				}else{
					friendBean.setSend_code("失败");
				}
				allFriendBean.add(friendBean);
				adapter.notifyDataSetChanged();
				close();
			}
		});
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		String userId=getIntent().getStringExtra("userid");
		this.userId=userId;
		
		if(allFriendBean!=null){
			allFriendBean.clear();
		}
		messageListView=(ListView)findViewById(R.id.messageListView);
		allFriendBean=new ArrayList<FriendMessageBean>();
		adapter=new FriendMessageAdapter(FriendsMessageActivity.this, allFriendBean);
		messageListView.setAdapter(adapter);
		
	}
	
}
