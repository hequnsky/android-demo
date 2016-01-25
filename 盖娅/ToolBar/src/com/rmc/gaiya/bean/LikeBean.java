package com.rmc.gaiya.bean;

import java.io.Serializable;

public class LikeBean implements Serializable{
 public String MediaID;
 public String MediaName;
 public String MedaiSize;
 public String Duration;
 public String ImgUrl;
 public String MediaType;
 public String IsLike;
 public LikeBean() {

 }
 
public LikeBean(String mediaID, String mediaName, String medaiSize, String duration, String imgUrl, String mediaType,
		String isLike) {
	super();
	MediaID = mediaID;
	MediaName = mediaName;
	MedaiSize = medaiSize;
	Duration = duration;
	ImgUrl = imgUrl;
	MediaType = mediaType;
	IsLike = isLike;
}
public String getMediaID() {
	return MediaID;
}
public void setMediaID(String mediaID) {
	MediaID = mediaID;
}
public String getMediaName() {
	return MediaName;
}
public void setMediaName(String mediaName) {
	MediaName = mediaName;
}
public String getMedaiSize() {
	return MedaiSize;
}
public void setMedaiSize(String medaiSize) {
	MedaiSize = medaiSize;
}
public String getDuration() {
	return Duration;
}
public void setDuration(String duration) {
	Duration = duration;
}
public String getImgUrl() {
	return ImgUrl;
}
public void setImgUrl(String imgUrl) {
	ImgUrl = imgUrl;
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
@Override
public String toString() {
	return "LikeBean [MediaID=" + MediaID + ", MediaName=" + MediaName + ", MedaiSize=" + MedaiSize + ", Duration="
			+ Duration + ", ImgUrl=" + ImgUrl + ", MediaType=" + MediaType + ", IsLike=" + IsLike + "]";
}
	

}
