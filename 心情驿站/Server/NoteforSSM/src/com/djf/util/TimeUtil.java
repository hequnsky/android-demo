package com.djf.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author android_djf
 *
 */
public class TimeUtil {

	public static String getCurrentDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		return date;
	}
	
	
	public static String getCurrentTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		String currentTime=sdf.format(new Date());
		return currentTime;
	}
	
	public static  String getCurrentTimeAndDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime=sdf.format(new Date());
		return currentTime;
	}
}
