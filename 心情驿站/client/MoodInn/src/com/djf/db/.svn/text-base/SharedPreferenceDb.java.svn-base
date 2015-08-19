package com.djf.db;

import com.djf.constants.DbConstants;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceDb {

	private Context context;
	
	public SharedPreferenceDb(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	
	public void setIsFirstInstall(){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_IS_INSTALL, Context.MODE_PRIVATE);
		sp.edit().putBoolean(DbConstants.KEY_IS_INSTALL, true).commit();
	}
	
	public boolean getIsFirstInstall(){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_IS_INSTALL, Context.MODE_PRIVATE);
		return sp.getBoolean(DbConstants.KEY_IS_INSTALL, false);
	}
	
	public void setChangeTheme(int bgColor){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_CHANGE_THEME, Context.MODE_PRIVATE);
		sp.edit().putInt("bgColor", bgColor).commit();
	}
	
	public int getChangeTheme(){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_CHANGE_THEME, Context.MODE_PRIVATE);
		int bgColor=sp.getInt("bgColor", 0);
		return bgColor;
	}
	
	public void setOpenAnimation(){//开
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_ANIMATION, Context.MODE_PRIVATE);
		sp.edit().putBoolean(DbConstants.KEY_ANIMATION, true).commit();
	}
	
	public void setCloseAnimation(){//关
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_ANIMATION, Context.MODE_PRIVATE);
		sp.edit().putBoolean(DbConstants.KEY_ANIMATION, false).commit();
	}
	
	public boolean getAnimation(){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_ANIMATION, Context.MODE_PRIVATE);
		return sp.getBoolean(DbConstants.KEY_ANIMATION, false);
	}
	
	
	public void setUserId(String userId){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_USER_ID, Context.MODE_PRIVATE);
		sp.edit().putString(DbConstants.KEY_USER_ID, userId).commit();
	}
	
	public String getUserId(){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_USER_ID, Context.MODE_PRIVATE);
		return sp.getString(DbConstants.KEY_USER_ID, "");
	}
	
	public String getName(){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_USER_NAME, Context.MODE_PRIVATE);
		return sp.getString(DbConstants.KEY_USER_NAME, "");
	}
	public void setName(String name){
		SharedPreferences sp=context.getSharedPreferences(DbConstants.DB_USER_NAME, Context.MODE_PRIVATE);
		sp.edit().putString(DbConstants.KEY_USER_NAME, name).commit();
	}
	
	
}
