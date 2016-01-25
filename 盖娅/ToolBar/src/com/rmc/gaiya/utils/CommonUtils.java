package com.rmc.gaiya.utils;

/**
 * 
 * @author hq
 * 防止用户重复点击
 *
 */
public class CommonUtils {
	private static long lastClickTime;
	private static long lasttouchTime;
	public static boolean isFastDoubleClick(){
		long time=System.currentTimeMillis();
		long timeD=time-lastClickTime;
		if (0<timeD&&timeD<1200) {
			return true;
			
		}
		lastClickTime=time;
		return false;
		
	}
	public static boolean isFastDoubleTouch(){
		long Touchtime=System.currentTimeMillis();
		long TouchtimeD=Touchtime-lasttouchTime;
		if (0<Touchtime&&TouchtimeD<1200) {
			return true;
			
		}
		lasttouchTime=Touchtime;
		return false;
		
	}

}
