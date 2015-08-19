package com.djf.service;
import com.djf.bean.User;
/**
 * baseUserService
 * @author android_djf
 *
 */
public interface UserService {
	void userRegist(User user) throws Exception;//
	
	User userLogin(User user) throws Exception;
	
	void updateUserPwd(User user) throws Exception;//修改用户密码
	
	void updateUserInfo(User user) throws Exception;//修改用户资料
	
	void updateUserIcon(User user) throws Exception;//修改用户图像
	
	void updateUserInfoImage(User user) throws Exception;//修改用户资料带图像
	
	User getUserInfoId(int id) throws Exception;//根据用户id查询用户信息
	
	User queryUserforName(String name) throws Exception;//根据用户名查询该用户是否存在

}
