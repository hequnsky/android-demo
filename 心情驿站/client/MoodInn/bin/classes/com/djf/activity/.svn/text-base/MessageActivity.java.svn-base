package com.djf.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.djf.adapter.FragmentAdapter;
import com.djf.db.SharedPreferenceDb;
import com.djf.fragment.PrivateFragment;
import com.djf.fragment.SystemFragment;
import com.djf.moodinn.R;
import com.djf.view.TitleBar;

@SuppressLint("ResourceAsColor")
public class MessageActivity extends BaseActivity implements OnClickListener,OnPageChangeListener{

	private TitleBar messageTitleBar;
	private RelativeLayout privateMessage;
	private RelativeLayout systemMessage;
	private TextView tvPrivate;
	private TextView tvSystem;
	private List<Fragment> allFragment=new ArrayList<Fragment>();
	private SystemFragment systemFragment=new SystemFragment();
	private PrivateFragment privateFragment=new PrivateFragment();
	
	
	private ViewPager messageViewPager;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.message);
		
		privateMessage=(RelativeLayout)findViewById(R.id.privateMessage);
		systemMessage=(RelativeLayout)findViewById(R.id.systemMessage);
		tvPrivate=(TextView)findViewById(R.id.tvPrivate);
		tvSystem=(TextView)findViewById(R.id.tvSystem);
		messageViewPager=(ViewPager)findViewById(R.id.messageViewPager);
		
		privateMessage.setOnClickListener(this);
		systemMessage.setOnClickListener(this);
		
		
		allFragment.add(systemFragment);
		allFragment.add(privateFragment);
		
		FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager(), allFragment);
		messageViewPager.setAdapter(adapter);
		tvSystem.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		tvPrivate.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		messageViewPager.setCurrentItem(0);
		messageViewPager.setOnPageChangeListener(this);
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		messageTitleBar=(TitleBar)findViewById(R.id.MessageTitleBar);
		messageTitleBar.showLeft("消/息", getResources().getDrawable(R.drawable.app_back), new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		if(!"0".equals(String.valueOf(new SharedPreferenceDb(MessageActivity.this).getChangeTheme()))){
			messageTitleBar.setBackgroundColor(new SharedPreferenceDb(MessageActivity.this).getChangeTheme());
		 }else{
			 messageTitleBar.setBackgroundColor(getResources().getColor(R.color.red));
		 }
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view==systemMessage){
			tvSystem.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			tvPrivate.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
			messageViewPager.setCurrentItem(0);
		}else if(view==privateMessage){
			tvPrivate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			tvSystem.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
			messageViewPager.setCurrentItem(1);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		if(arg0==0){
			tvSystem.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			tvPrivate.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		}else if(arg0==1){
			tvPrivate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			tvSystem.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		}
	}
	
}
