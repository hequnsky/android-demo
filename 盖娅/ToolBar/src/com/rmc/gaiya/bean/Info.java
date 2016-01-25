package com.rmc.gaiya.bean;

import java.util.List;

public class Info {

	public String Id;
	public String Name;
	public String ImgUrl;
	public  String sortId;
    List<MediaList> media;
    List<ModeList> mode;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
		return sortId;
	}
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
	public List<MediaList> getMedia() {
		return media;
	}
	public void setMedia(List<MediaList> media) {
		this.media = media;
	}
	public List<ModeList> getMode() {
		return mode;
	}
	public void setMode(List<ModeList> mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "Info [Id=" + Id + ", Name=" + Name + ", ImgUrl=" + ImgUrl + ", sortId=" + sortId + ", media=" + media
				+ ", mode=" + mode + "]";
	}
    
    

}
