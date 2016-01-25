package com.rmc.gaiya.bean;

import java.util.List;

public class Bee {
	 String list_title;
	 String  list_imgs;
	 List<GV> gv;
	public String getList_title() {
		return list_title;
	}
	public void setList_title(String list_title) {
		this.list_title = list_title;
	}
	public String getList_imgs() {
		return list_imgs;
	}
	public void setList_imgs(String list_imgs) {
		this.list_imgs = list_imgs;
	}
	public List<GV> getGv() {
		return gv;
	}
	public void setGv(List<GV> gv) {
		this.gv = gv;
	}
	@Override
	public String toString() {
		return "Bee [list_title=" + list_title + ", list_imgs=" + list_imgs + ", gv=" + gv + "]";
	}
	
	

	
	

}
