package com.djf.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djf.bean.User;
import com.djf.dao.UserDAO;
import com.djf.service.UserService;
/**
 * 用户service 实现类
 * @author android_djf
 *
 */
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDAO userDao;


	@Override
	public void userRegist(User user) throws Exception {
		userDao.regist(user);
		
	}

	@Override
	public User userLogin(User user) throws Exception {
		// TODO Auto-generated method stub
		User userInfo =userDao.login(user);
		return userInfo;
	}

	@Override
	public void updateUserPwd(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.updateUserPwd(user);
	}

	@Override
	public User getUserInfoId(int id) throws Exception {
		// TODO Auto-generated method stub
		User user=userDao.getUserInfoId(id);
		return user;
	}

	@Override
	public User queryUserforName(String name) throws Exception {
		// TODO Auto-generated method stub
		User user=userDao.queryUserForName(name);
		return user;
	}

	@Override
	public void updateUserInfo(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.updateUserInfo(user);
	}

	@Override
	public void updateUserIcon(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.updateUserIcon(user);
	}

	@Override
	public void updateUserInfoImage(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.updateUserInfoImage(user);
	}



}
