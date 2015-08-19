package com.djf.bean;

import java.util.List;

public class MoodInBean {

	private String message;
	
	private String respcode;
	
	private List<MoodInfoBean> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRespcode() {
		return respcode;
	}

	public void setRespcode(String respcode) {
		this.respcode = respcode;
	}

	public List<MoodInfoBean> getData() {
		return data;
	}

	public void setData(List<MoodInfoBean> data) {
		this.data = data;
	}
	
	
}
