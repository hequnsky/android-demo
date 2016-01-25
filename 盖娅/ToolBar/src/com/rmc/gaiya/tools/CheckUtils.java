package com.rmc.gaiya.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class CheckUtils {
	private static final String HMAC_SHA1 = "HmacSHA1";
	private static String time;
	private static StringBuilder str3;
	
 public static String getURL(String publicKey,String privateKey){
	 
	 SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date t = new Date();
		df1.setTimeZone(TimeZone.getTimeZone("UTC"));
		time = df1.format(t);
		String a="&";
		String b="=";
		String c="GET";
		String d="?";
		String s="/";
		 Map<String, String> map = new HashMap<String, String>();
		map.put("PublicKey", publicKey);
		map.put("SignatureNonce", "5643nhhhhy");
		map.put("SignatureMethod", "HMAC-SHA1");
		map.put("Timestamp", time);
		map.put("ParentId", "1");
		SortedMap<String,String> sortedMapByKey = new TreeMap<String,String>();
		sortedMapByKey.putAll(map);
		StringBuilder str=new StringBuilder();
		try {
			str.append(c).append(a).append(URLEncoder.encode("/", "utf-8")).append(a);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder str2=new StringBuilder();
		for (Map.Entry<String, String> entry : sortedMapByKey.entrySet()) {
			str2.append(entry.getKey());
			str2.append(b);
			try {
				str2.append(URLEncoder.encode(entry.getValue(),"utf-8"));
				str2.append(a);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try {
			str2.delete(str2.length()-1, str2.length());
			str.append(URLEncoder.encode(str2+"","UTF-8"));
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		try {
			String signature = getSignature(str+"",privateKey+"&");
			System.err.println("signature=="+signature);
			System.err.println("signature=="+signature);
			String Str6="http://192.168.1.226:89/api/AppHome?";
			str3 = new StringBuilder();
			str3.append(Str6).append(str2).append("&Signature=").append(signature);
			System.err.println("str3=="+str3);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	 String url=str3.toString();
	return url; 
 }
//º”√‹
 public static String getSignature(String data,String key) throws Exception{  
	     byte[] keyBytes=key.getBytes();  
	        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);     
	        Mac mac = Mac.getInstance(HMAC_SHA1);     
	        mac.init(signingKey);     
	        byte[] rawHmac = mac.doFinal(data.getBytes());  

	        return Base64.encodeToString(rawHmac, Base64.DEFAULT);  
	    }  

}
