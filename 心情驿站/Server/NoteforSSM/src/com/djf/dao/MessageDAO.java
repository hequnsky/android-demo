package com.djf.dao;

import java.util.List;

import com.djf.bean.SystemMessage;
/**
 * 私信dao层
 * @author android_djf
 *
 */
public interface MessageDAO {

	/*增加一条私信*/
	public void addSystemMessage(SystemMessage message);
	
	/*根据接收方id查询私信*/
	public List<SystemMessage> queryAll(String acceptid);
	
	/*拉黑*/
	public void updateMessage(SystemMessage message);
	
	public List<SystemMessage> queryMessageforDate(String currentDate);
}
