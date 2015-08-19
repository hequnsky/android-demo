package com.djf.util;

public class StringSplit {

	/**
	 * 用于处理图像
	 * @param imageUrl
	 * @return
	 */
	public static String userImage(String imageUrl){
		String[] str=imageUrl.split("/");
		return str[str.length-1];
		
	} 
	
	
}
