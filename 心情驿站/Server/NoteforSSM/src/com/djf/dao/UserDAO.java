package com.djf.dao;

import com.djf.bean.User;
/**
 * 用户DAO层
 * @author android_djf
 *
 */
public interface UserDAO {

	void regist(User user) throws Exception;//注册
	
	User login(User user) throws Exception;//用户登录
	
	void updateUserPwd(User user) throws Exception;//修改用户密码
	
	void updateUserInfo(User user) throws Exception;//修改用户资料不带图像
	
	void updateUserInfoImage(User user) throws Exception;//修改用户资料带图像
	
	void updateUserIcon(User user) throws Exception;//修噶用户图像
	
	User getUserInfoId(int id) throws Exception;//根据用户id查询信息
	
	User queryUserForName(String name) throws Exception;//根据用户名查询是否存在该用户
}
