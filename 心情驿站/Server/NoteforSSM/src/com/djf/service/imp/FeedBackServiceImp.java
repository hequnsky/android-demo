package com.djf.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djf.bean.FeedBack;
import com.djf.dao.FeedBackDAO;
import com.djf.service.FeedBackService;
/**
 * 意见反馈实现
 * @author android_djf
 *
 */
@Service
public class FeedBackServiceImp implements FeedBackService {

	@Autowired
	private FeedBackDAO feedDao;

	@Override
	public void add(FeedBack back) {
		// TODO Auto-generated method stub
		feedDao.addFeedBack(back);
	}
	
}
