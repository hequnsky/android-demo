package com.djf.bean;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private Map<String,String> result = new HashMap<String,String>();
	private Map<String,String> msg = new HashMap<String,String>();
	
	public void setResult(String code){
		result.put("result", code);
	}
	
	public void setMsg(String message){
		msg.put("msg", message);
	}
	
	public Map getResult(){
		return result;
	}
	public Map getMsg(){
		return msg;
	}
}
