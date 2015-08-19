package com.djf.bean;

import java.io.Serializable;

/**
 * 版本控制
 * @author android_djf
 *
 */
public class AppVersion implements Serializable {

	//ID
	private int id;
	
	//版本名
	private String vname;
	
	//版本号
	private int vcode;
	
	//apk 路径
	private String apkpath;
	
	//消息
	private String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public int getVcode() {
		return vcode;
	}

	public void setVcode(int vcode) {
		this.vcode = vcode;
	}

	public String getApkpath() {
		return apkpath;
	}

	public void setApkpath(String apkpath) {
		this.apkpath = apkpath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
