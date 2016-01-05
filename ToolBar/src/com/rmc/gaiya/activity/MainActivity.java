package com.rmc.gaiya.activity;


import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import com.rmc.gaiya.widget.CustomGridView;
import com.rmc.gaiya.widget.CustomerViewPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity{
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	ImageView mian_play;
    boolean ispause=true;
	private float offset;
	private boolean flipped;
	private CustomerViewPage viewPage;
	private List<View> views;
	private LinearLayout drawr_cancel;
	private LinearLayout drawr_about;
	private LinearLayout lookip;
	private LinearLayout iplist;
	private LinearLayout collect;
	private Member member;
     LinearLayout li;
     DrawerLayout drawer ;
	private CustomGridView gridView,gridView2,gridView3,gridView4,gridView5,gridView6,gridView7;
	private int[] imageId = new int[] { R.drawable.cai1, R.drawable.cai1, R.drawable.cai1, R.drawable.cai1,
			R.drawable.cai1 }; // 定义并初始化保存图片id的数组

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Toolbar boolBar = (Toolbar)findViewById(R.id.toolbar);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
		boolBar.setTitle("盖娅光音");
		boolBar.setTitleTextColor(getResources().getColor(R.color.title_color));
		setSupportActionBar(boolBar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, boolBar,R.string.drawer_open, R.string.drawer_close);
		mDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		initID();
		initViewpager();
		initGridView();
		initOnClickListener();
		
	}


	private void initOnClickListener() {
	/**
	 * 进入音乐播放器事件 
	 * 
	 * */	
     li.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),GaiyaMusic.class);
				startActivity(intent);
				  MainActivity.this.overridePendingTransition(R.anim.activity_open,0);  
			}
		});
     /**
      * 底部播放按钮的点击事件
      * */
     mian_play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ispause) {
					mian_play.setImageResource(R.drawable.main_pause);
					ispause=false;
					
				}else{
					mian_play.setImageResource(R.drawable.pause2);
					ispause=true;
				}
			}
		});
     iplist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "跳转到主机列表", 1000).show();
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		collect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent collect=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(collect);
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		drawr_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);// 返回登入界面
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		drawr_about.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), GaiYaAbout.class);
				startActivity(intent);
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
		lookip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(MainActivity.this,LookFor_IPActivity.class);
				startActivity(i);
				mDrawerLayout.closeDrawer(Gravity.LEFT);
				
			}
		});
     
     
     
	}


	private void initID() {
		li=(LinearLayout) findViewById(R.id.buttom);
		mian_play=(ImageView) findViewById(R.id.main_paly);
		iplist=(LinearLayout) findViewById(R.id.ip_list);
		drawr_cancel = (LinearLayout) findViewById(R.id.drawr_cancel);
		drawr_about = (LinearLayout) findViewById(R.id.drawr_about);
		lookip = (LinearLayout) findViewById(R.id.lookip);
		collect=(LinearLayout) findViewById(R.id.collect);
	}


	/**
	 * 
	 * 初始化广告轮播条
	 **/
	private void initViewpager() {
		viewPage = (CustomerViewPage) findViewById(R.id.vp);
		views = new ArrayList<View>();
		ImageView imageView1 = new ImageView(this);
		ImageView imageView2 = new ImageView(this);
		ImageView imageView3 = new ImageView(this);
		ImageView imageView4 = new ImageView(this);
		ImageView imageView5 = new ImageView(this);
		ImageView imageView6 = new ImageView(this);
		ImageView imageView7 = new ImageView(this);
		imageView1.setBackgroundResource(R.drawable.vp1);
		views.add(imageView1);
		imageView2.setBackgroundResource(R.drawable.vp2);
		views.add(imageView2);
		imageView3.setBackgroundResource(R.drawable.vp3);
		views.add(imageView3);
		imageView4.setBackgroundResource(R.drawable.vp4);
		views.add(imageView4);
		imageView5.setBackgroundResource(R.drawable.vp5);
		views.add(imageView5);
		imageView6.setBackgroundResource(R.drawable.vp6);
		views.add(imageView6);
		imageView7.setBackgroundResource(R.drawable.vp7);
		views.add(imageView7);
		viewPage.setViewPageViews(views);
	}
	/**
	 * 初始化gridView
	 */
	private void initGridView() {
		gridView = (CustomGridView) findViewById(R.id.gridView);
		GridViewAdapter gridViewAdapter = new GridViewAdapter();
		gridView.setAdapter(gridViewAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent enter=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(enter);
				
			}
		});
		
		gridView2 = (CustomGridView) findViewById(R.id.gridView2);
		GridViewAdapter gridViewAdapter2 = new GridViewAdapter();
		gridView2.setAdapter(gridViewAdapter2);
		gridView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent enter=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(enter);
			}
		});
		
		gridView3= (CustomGridView) findViewById(R.id.gridView3);
		GridViewAdapter gridViewAdapter3 = new GridViewAdapter();
		gridView3.setAdapter(gridViewAdapter3);
		gridView3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent enter=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(enter);
			}
		});
		
		gridView4 = (CustomGridView) findViewById(R.id.gridView4);
		GridViewAdapter gridViewAdapter4 = new GridViewAdapter();
		gridView4.setAdapter(gridViewAdapter4);
		gridView4.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent enter=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(enter);
			}
		});
		
		gridView5= (CustomGridView) findViewById(R.id.gridView5);
		GridViewAdapter gridViewAdapter5 = new GridViewAdapter();
		gridView5.setAdapter(gridViewAdapter5);
		gridView5.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent enter=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(enter);
			}
		});
		
		gridView6 = (CustomGridView) findViewById(R.id.gridView6);
		GridViewAdapter gridViewAdapter6 = new GridViewAdapter();
		gridView6.setAdapter(gridViewAdapter6);
		gridView6.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent enter=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(enter);
			}
		});
		
		gridView7= (CustomGridView) findViewById(R.id.gridView7);
		GridViewAdapter gridViewAdapter7 = new GridViewAdapter();
		gridView7.setAdapter(gridViewAdapter7);
		gridView7.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent enter=new Intent(MainActivity.this,GaiyaListInfo.class);
				startActivity(enter);
			}
		});
		
		
	}
	private class GridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageId.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageview;
			if (convertView == null) {
				imageview = new ImageView(MainActivity.this); // 实例化ImageView的对象
				imageview.setScaleType(ImageView.ScaleType. FIT_XY); // 设置缩放方式
				imageview.setPadding(5, 0, 5, 0); // 设置ImageView的内边距
			} else {
				imageview = (ImageView) convertView;
			}
			imageview.setImageResource(imageId[position]); // 为ImageView设置要显示的图片
			return imageview; // 返回ImageView
		}
	}
	
}
