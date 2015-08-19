package com.djf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 心情
 * 
 * @author android_djf
 *
 */
@SuppressWarnings("serial")
public class DiaryforUserIcon implements Serializable {

	// ID
	private int id;

	// 用户id
	private int userid;

	//用户昵称
	private String userName;
	
	//用户昵称
	private String age;

	// 日期
	private String date;
	
	//时间
	private String time;
	
	private List<Comment> allComment;

	// 内容
	private String content;

	// 心情图像1
	private String imgone;

	// 心情图像2
	private String imgtwo;

	// 心情图像3
	private String imgthree;

	// 心情图像4
	private String imgfour;

	// 心情图像5
	private String imgfive;

	// 心情图像6
	private String imgsix;


	// 用户图像
	private String usreIcon;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImgone() {
		return imgone;
	}


	public void setImgone(String imgone) {
		this.imgone = imgone;
	}


	public String getImgtwo() {
		return imgtwo;
	}


	public void setImgtwo(String imgtwo) {
		this.imgtwo = imgtwo;
	}


	public String getImgthree() {
		return imgthree;
	}


	public void setImgthree(String imgthree) {
		this.imgthree = imgthree;
	}


	public String getImgfour() {
		return imgfour;
	}


	public void setImgfour(String imgfour) {
		this.imgfour = imgfour;
	}


	public String getImgfive() {
		return imgfive;
	}


	public void setImgfive(String imgfive) {
		this.imgfive = imgfive;
	}


	public String getImgsix() {
		return imgsix;
	}


	public void setImgsix(String imgsix) {
		this.imgsix = imgsix;
	}


	public String getUsreIcon() {
		return usreIcon;
	}


	public void setUsreIcon(String usreIcon) {
		this.usreIcon = usreIcon;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public List<Comment> getAllComment() {
		return allComment;
	}


	public void setAllComment(List<Comment> allComment) {
		this.allComment = allComment;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}
	
	

}
