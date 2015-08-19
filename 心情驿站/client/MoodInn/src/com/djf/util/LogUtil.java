package com.djf.util;

import com.djf.constants.SystemConstants;

import android.util.Log;

public class LogUtil {

	public static boolean Debug=true;
	
	public static void i(String str){
		if(Debug){
		Log.i(SystemConstants.LOG_NAME, str);
		}
	}
	
	
}
