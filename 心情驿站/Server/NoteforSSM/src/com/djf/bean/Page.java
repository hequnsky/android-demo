package com.djf.bean;

/**
 * 根据当天日期进行分页查询
 * @author android_djf
 *
 */
public class Page {
	private int pageNo=1;
	private int pageSize = 5;
	private int totalPage;
	private int start ;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStart() {
		return (pageNo-1)*pageSize;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
