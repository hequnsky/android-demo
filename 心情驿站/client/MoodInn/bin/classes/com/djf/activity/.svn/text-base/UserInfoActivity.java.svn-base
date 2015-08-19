package com.djf.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.djf.adapter.UserDiaryAdapter;
import com.djf.bean.BaseBean;
import com.djf.bean.MoodInBean;
import com.djf.bean.MoodInfoBean;
import com.djf.bean.UserInfoBean;
import com.djf.callback.ResultCallback;
import com.djf.constants.HttpConstants;
import com.djf.listview.XListView;
import com.djf.listview.XListView.IXListViewListener;
import com.djf.loadimage.AsynImageLoader;
import com.djf.moodinn.R;
import com.djf.util.HttpUtil;
import com.djf.view.TitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class UserInfoActivity extends BaseActivity implements
		IXListViewListener {

	private String userName;
	private String userId;
	private TitleBar userInfoTitleBar;
	/*private FrameLayout frameLayout;*/
	private UserInfoBean userInfoBean;
	private ImageView userLogo;
	private TextView tvUserName;
	private TextView tvUserAge;
	private TextView tvUserCity;
	private XListView myListView;
	private int page = 1;
	private int INIT_LOADING = 0;// 初始化第一次加载
	private int MORE_LOADING = 1;// 加载更多
	UserDiaryAdapter adapter;

	AsynImageLoader imageLoad = new AsynImageLoader();
	List<MoodInfoBean> allData = null;
	private View ListViewHeadView;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.user_info);
		initData();
		initViews();
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		final String userId = getIntent().getStringExtra("userid");
		String userName = getIntent().getStringExtra("username");
		this.userName = userName;
		this.userId = userId;
		userInfoTitleBar = (TitleBar) findViewById(R.id.userInfoTitleBar);
		userInfoTitleBar.showLeftImageAndRightStr(userName, "写私信", getResources().getDrawable(R.drawable.app_back), new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}
		}, new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(UserInfoActivity.this, "写私信", Toast.LENGTH_LONG).show();
				Intent intent=new Intent();
				intent.setClass(UserInfoActivity.this, FriendsMessageActivity.class);
				intent.putExtra("userid", userId);
				startActivity(intent);
			}
		});
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		super.initViews();

		
		ListViewHeadView=LayoutInflater.from(UserInfoActivity.this).inflate(R.layout.listview_head, null);
		userLogo = (ImageView) findViewById(R.id.iv_leftlogo);
		tvUserName = (TextView) findViewById(R.id.tvName);
		tvUserAge = (TextView) findViewById(R.id.tvAge);
		tvUserCity = (TextView) findViewById(R.id.tvCity);
		myListView = (XListView) findViewById(R.id.allDiaryListView);
		myListView.setPullLoadEnable(true);
		myListView.setXListViewListener(this);

		myListView.addHeaderView(ListViewHeadView);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", userId));
		initProgressDialog();
		HttpUtil.doPost(HttpConstants.HTTP_GET_USERINFO, params,
				new ResultCallback() {

					@Override
					public void getReslt(String result) {
						// TODO Auto-generated method stub
						BaseBean b = JSON.parseObject(result, BaseBean.class);
						if (b.getRespcode().equals("0")) {
							final UserInfoBean userInfo = JSON.parseObject(
									b.getData(), UserInfoBean.class);
							if (userInfo != null) {
								/*userInfoBean = userInfo;
								imageLoad.showImageAsyn(
										userLogo,
										HttpConstants.HTTP_REQUEST
												+ userInfo.getImage(),
										R.drawable.ic_launcher);
								tvUserName.setText(userInfo.getName());
								tvUserAge.setText(userInfo.getAge());
								tvUserCity.setText(userInfo.getCity());*/

								getListViewData(INIT_LOADING);
								close();
							}
							close();
						} else {
							Toast.makeText(UserInfoActivity.this, "访问网络失败",
									Toast.LENGTH_LONG).show();
							close();
						}
					}
				});

	}

	/**
	 * 初始化listview 数据
	 */
	public void getListViewData(final int code) {

		List<NameValuePair> getMeDiarysP = new ArrayList<NameValuePair>();
		getMeDiarysP.add(new BasicNameValuePair("page", String.valueOf(page)));
		getMeDiarysP.add(new BasicNameValuePair("userid", userId));
		initProgressDialog();
		HttpUtil.doPost(HttpConstants.HTTP_ALL_DIARY_FORUSERID, getMeDiarysP,
				new ResultCallback() {

					@SuppressWarnings("null")
					@Override
					public void getReslt(String result) {
						// TODO Auto-generated method stub

						
						if(code==INIT_LOADING){
							BaseBean b = JSON.parseObject(result, BaseBean.class);
							if(b.getRespcode().equals("0")){
								
								Type type = new TypeToken<MoodInBean>() {
								}.getType();
								// Gson解析
								Gson gson = new Gson();

								MoodInBean json = gson.fromJson(result, type);
								allData = json.getData();
								if(allData.size()>0){
									adapter = new UserDiaryAdapter(
											UserInfoActivity.this, allData);
									myListView.setAdapter(adapter);
									page++;
								}else{
									Toast.makeText(UserInfoActivity.this, "暂无相关信息", Toast.LENGTH_LONG).show();
								}
								close();
							}else{
								close();
								Toast.makeText(UserInfoActivity.this, "服务器响应失败", Toast.LENGTH_LONG).show();
							}
							close();
						}else if(code==MORE_LOADING){
							BaseBean b = JSON.parseObject(result, BaseBean.class);
							if(b.getRespcode().equals("0")){
								
								Type type = new TypeToken<MoodInBean>() {
								}.getType();
								// Gson解析
								Gson gson = new Gson();

								MoodInBean json = gson.fromJson(result, type);
								List<MoodInfoBean> allDatas = json.getData();
								if(allDatas.size()>0){
									for(int i=0;i<allDatas.size();i++){
										MoodInfoBean mb=allDatas.get(i);
										allData.add(mb);
									}
									adapter.notifyDataSetChanged();
									page++;
									onLoad();
								}else{
									Toast.makeText(UserInfoActivity.this, "暂无相关信息", Toast.LENGTH_LONG).show();
									onLoad();
								}
								close();
							}else{
								close();
								Toast.makeText(UserInfoActivity.this, "服务器响应失败", Toast.LENGTH_LONG).show();
								onLoad();
							}
						}
						
					}
				});

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		myListView.stopRefresh(); 
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		getListViewData(MORE_LOADING);

	}

	/**
	 * 关闭进度
	 */
	private void onLoad() {
		 myListView.stopRefresh(); 
		myListView.stopLoadMore();
		myListView.setRefreshTime("刚刚");
	}

}
