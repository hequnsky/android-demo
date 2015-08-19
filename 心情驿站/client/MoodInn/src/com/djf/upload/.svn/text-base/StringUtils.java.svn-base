package com.djf.upload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.google.gson.Gson;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

/**
 * 字符串处理工具类
 * @author JiaYe 2014年7月18日
 *
 */
public class StringUtils {

	/**
	 * 从流中读取字符串
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static String readString(InputStream input) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len=(input.read(buffer))) != -1){  
			baos.write(buffer,0,len);    
		}
		String retStr = new String(baos.toByteArray());
		input.close();
		return retStr;
	}

	/**
	 * 向右补全空格
	 * @param s
	 * @param size
	 * @return
	 */
	public static String blankSizeString(String s, int size) {
		StringBuilder sb = new StringBuilder(s);
		if(size > s.length()){
			for(int i=0; i<size-s.length(); i++){
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
	public static String blankSizeStringHtml(String s, int size) {
		StringBuilder sb = new StringBuilder(s);
		if(size > s.length()){
			for(int i=0; i<size-s.length(); i++){
				sb.append("&nbsp;");
			}
		}
		return sb.toString();
	}

	public static boolean validatePhone(String phone) {
		if (phone==null || phone.length()==0)
			return false;
		String pattern = "^1[3,4,5,6,8]+\\d{9}$";
		return phone.matches(pattern);
	}

	/**
	 * 转换成map
	 * @return
	 */
	public static HashMap<String, String> toMap(Object obj){
		Gson g = new Gson();
		return g.fromJson(g.toJson(obj), HashMap.class);
	}
	
	/**
	 * 给字符串设置颜色
	 * 
	 * @param str
	 * @param color
	 * @return
	 */
	public static SpannableString colorSpan(String str, int color) {
		SpannableString spanString = new SpannableString(str);
		ForegroundColorSpan span = new ForegroundColorSpan(color);
		spanString.setSpan(span, 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spanString;
	}

	/**
	 * 给字符串设置颜色，阴影
	 * 
	 * @param str
	 * @param color
	 * @param shadowCOlor
	 * @return
	 */
	public static SpannableString shadowSpan(String str, int color, int shadowCOlor) {
		SpannableString spanString = new SpannableString(str);
		ForegroundColorSpan span = new ForegroundColorSpan(color);
		spanString.setSpan(span, 0, spanString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ShadowSpan sd = new ShadowSpan(16, 2, 2, shadowCOlor);
		spanString.setSpan(sd, 0, spanString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		// spanString.setSpan(what, start, end, flags);
		return spanString;
	}
	
	/**
	 * 阴影着色span
	 * 
	 * @author JiaYe
	 * 
	 */
	static class ShadowSpan extends CharacterStyle {
		public float Dx;
		public float Dy;
		public float Radius;
		public int Color;

		public ShadowSpan(float radius, float dx, float dy, int color) {
			Radius = radius;
			Dx = dx;
			Dy = dy;
			Color = color;
		}

		@Override
		public void updateDrawState(TextPaint tp) {
			tp.setShadowLayer(Radius, Dx, Dy, Color);
			/*
			 * tp.setStyle(Style.FILL_AND_STROKE); Paint paint = new Paint();
			 * paint.setStyle(Style.FILL_AND_STROKE);
			 * paint.setColor(android.graphics.Color.WHITE); tp.set(paint);
			 */
		}
	}
}
