package com.djf.util;
import java.util.LinkedList;
import java.util.List;

import com.baidu.frontia.FrontiaApplication;
import com.baidu.mapapi.SDKInitializer;
import android.app.Activity;

public class MoodApplication extends FrontiaApplication {

	private List<Activity> allActivity=null;
	
	private static MoodApplication moodApplication=null;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MoodApplication() {
		// TODO Auto-generated constructor stub
		allActivity=new LinkedList();
	}
	
	public static MoodApplication getInstance(){
		if(moodApplication==null){
			moodApplication=new MoodApplication();
		}
		return moodApplication;
	}
	
	public void addActivity(Activity act){
		if(allActivity!=null && allActivity.size()>0){
			if(!allActivity.contains(act)){
				allActivity.add(act);
			}
		}else{
			allActivity.add(act);
		}
	}
	
	public void exit(){
		if(allActivity!=null && allActivity.size()>0){
			for(Activity act:allActivity){
				act.finish();
			}
			LogUtil.i("activity數量："+allActivity.size());
		}
		System.exit(0);
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		LogUtil.i("application create(");
		SDKInitializer.initialize(this);
		
		
		new App().init();
	}
	
	
}
