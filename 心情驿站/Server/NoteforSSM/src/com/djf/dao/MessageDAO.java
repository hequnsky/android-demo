package com.djf.dao;

import java.util.List;

import com.djf.bean.SystemMessage;
/**
 * ˽��dao��
 * @author android_djf
 *
 */
public interface MessageDAO {

	/*����һ��˽��*/
	public void addSystemMessage(SystemMessage message);
	
	/*���ݽ��շ�id��ѯ˽��*/
	public List<SystemMessage> queryAll(String acceptid);
	
	/*����*/
	public void updateMessage(SystemMessage message);
	
	public List<SystemMessage> queryMessageforDate(String currentDate);
}
