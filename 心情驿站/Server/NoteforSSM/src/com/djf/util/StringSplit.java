package com.djf.util;

public class StringSplit {

	/**
	 * ���ڴ���ͼ��
	 * @param imageUrl
	 * @return
	 */
	public static String userImage(String imageUrl){
		String[] str=imageUrl.split("/");
		return str[str.length-1];
		
	} 
	
	
}
