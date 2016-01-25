package com.rmc.gaiya.bean;

public class MediaList {
	public String Id;
	public String Name;
	public String ImgUrl;
	public String MediaSize;
	public String Duration;
	public String MediaType;
	public String IsLike;
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
	public String getMediaSize() {
		return MediaSize;
	}
	public void setMediaSize(String mediaSize) {
		MediaSize = mediaSize;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public String getMediaType() {
		return MediaType;
	}
	public void setMediaType(String mediaType) {
		MediaType = mediaType;
	}
	public String getIsLike() {
		return IsLike;
	}
	public void setIsLike(String isLike) {
		IsLike = isLike;
	}
	public String getMarkUp() {
		return MarkUp;
	}
	public void setMarkUp(String markUp) {
		MarkUp = markUp;
	}
	public String MarkUp;
	@Override
	public String toString() {
		return "MediaList [Id=" + Id + ", Name=" + Name + ", ImgUrl=" + ImgUrl + ", MediaSize=" + MediaSize
				+ ", Duration=" + Duration + ", MediaType=" + MediaType + ", IsLike=" + IsLike + ", MarkUp=" + MarkUp
				+ "]";
	}
	
}
