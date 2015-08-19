package com.djf.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djf.bean.SystemMessage;
import com.djf.dao.MessageDAO;
import com.djf.service.MessageService;
/**
 * 私信实现类
 * @author android_djf
 *
 */
@Service
public class SystemMessageImp implements MessageService {

	@Autowired
	private MessageDAO messageDao;
	
	
	@Override
	public void addSystemMessage(SystemMessage message) {
		// TODO Auto-generated method stub
		messageDao.addSystemMessage(message);
	}


	@Override
	public List<SystemMessage> queryAll(String acceptid) {
		// TODO Auto-generated method stub
		List<SystemMessage> allMessage=messageDao.queryAll(acceptid);
		return allMessage;
	}


	@Override
	public void updateMessage(SystemMessage message) {
		// TODO Auto-generated method stub
		messageDao.updateMessage(message);
	}


	@Override
	public List<SystemMessage> queryMessageforDate(String currentDate) {
		// TODO Auto-generated method stub
		List<SystemMessage> allSystemMessage=messageDao.queryMessageforDate(currentDate);
		return allSystemMessage;
	}


	

}
