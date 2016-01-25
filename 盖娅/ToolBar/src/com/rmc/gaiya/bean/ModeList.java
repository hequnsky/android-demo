package com.rmc.gaiya.bean;

public class ModeList {
	public String id;
	public String Name;
	public String ImgUrl;
	public String SortId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getImgUrl() {
		return ImgUrl;
	}
	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}
	public String getSortId() {
		return SortId;
	}
	public void setSortId(String sortId) {
		SortId = sortId;
	}
	@Override
	public String toString() {
		return "ModeList [id=" + id + ", Name=" + Name + ", ImgUrl=" + ImgUrl + ", SortId=" + SortId + "]";
	}

}
