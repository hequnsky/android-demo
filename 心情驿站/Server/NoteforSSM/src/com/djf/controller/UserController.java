package com.djf.controller;

import java.io.File;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.djf.bean.LoginResult;
import com.djf.bean.User;
import com.djf.service.UserService;
import com.djf.util.Constants;
import com.djf.util.InfoMation;
import com.djf.util.ResultCode;
import com.djf.util.StringSplit;

/**
 * 用户操作控制器
 * 
 * @author android_djf
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("update")
	public String test() {
		return "file";
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userRegist")
	@ResponseBody
	// 返回json
	public Map<String, Object> userRegist(HttpServletRequest request)
			throws Exception {
		User user = new User();
		Map<String, Object> result = new HashMap<String, Object>();

		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		System.out.println("注册接口/接收到用户传递参数" + phone + "密码" + password + "昵称:"
				+ name);
		User u = userService.queryUserforName(phone);
		if (u != null) {
			result.put("data", ResultCode.SUCCESS);
			result.put("respcode", ResultCode.SUCCESS);
			result.put("errorcode", ResultCode.USEREXIST);
			result.put("message", "注册失败,用户已经存在!");
		} else {
			user.setPhone(phone);
			user.setPassword(password);
			user.setName(name);
			// 数据库操作
			try {
				userService.userRegist(user);
				result.put("data", ResultCode.SUCCESS);
				result.put("respcode", ResultCode.SUCCESS);
				result.put("errorcode", "");
				result.put("message", "注册成功!");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("data", "");
				result.put("respcode", ResultCode.FAIL);
				result.put("errorcode", ResultCode.FAIL);
				result.put("message", "注册失败!");
			}
		}
		return result;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userLogin")
	@ResponseBody
	public Map<String, Object> userLogin(HttpServletRequest request)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		User user = new User();

		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		user.setPhone(phone);
		user.setPassword(password);

		User u = userService.queryUserforName(phone);
		if (u != null) {

			User users = userService.userLogin(user);
			if (users != null) {
				LoginResult loginResult = new LoginResult();
				loginResult.setGold(users.getGold());
				loginResult.setId(users.getId());
				loginResult.setUserIcon(users.getImage());
				loginResult.setName(users.getName());
				loginResult.setVip(users.getVip());
				result.put("data", loginResult);
				result.put("respcode", ResultCode.SUCCESS);
				result.put("errorcode", "");
				result.put("message", "登陆成功!");
			} else {
				result.put("data", "");
				result.put("respcode", ResultCode.FAIL);
				result.put("errorcode", ResultCode.FAIL);
				result.put("message", "登陆失败!");
			}

		} else {
			result.put("data", "");
			result.put("respcode", ResultCode.USEREXIST);
			result.put("errorcode", "");
			result.put("message", "登陆失败!");
		}

		return result;

	}

	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateUserPwd")
	@ResponseBody
	public Map<String, Object> updateUserPwd(HttpServletRequest request)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		InfoMation infoMaction = new InfoMation();
		int id = Integer.valueOf(request.getParameter("id"));
		String userOldPwd = request.getParameter("oldpassword");
		String userNewPwd = request.getParameter("newpassword");
		
		
			User user = userService.getUserInfoId(id);
			if (user != null) {
				if (userOldPwd.equals(user.getPassword())) {
					user.setPassword(userNewPwd);
					userService.updateUserPwd(user);
					result.put("message", "修改密码成功!");
					result.put("respcode", ResultCode.SUCCESS);
					result.put("data", ResultCode.SUCCESS);
					result.put("errorcode", "");
				} else {
					result.put("message", "旧密码错误");
					result.put("respcode", ResultCode.FAIL);
					result.put("data", "");
					result.put("errorcode", ResultCode.OLDPWDFAIL);
				}
			} else {
				result.put("message", "用户不存在");
				result.put("respcode", ResultCode.FAIL);
				result.put("data", "");
				result.put("errorcode", ResultCode.UNUSEREXIST);
			}
		
		return result;

	}

	/**
	 * 修改用户图像
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="updateUserIcon")
	@ResponseBody
	public Map<String, Object> uploadUserIcon(HttpServletRequest request,
			@RequestParam("file") MultipartFile file) throws Exception {
		Map<String,Object> result=new HashMap<String, Object>();
		User user=new User();
		int id = Integer.valueOf(request.getParameter("id"));
		System.out.println("修改图像传递的id:"+id);
		String realPath = request.getSession().getServletContext()
				.getRealPath("userIcon/");
		
		String fileName = file.getOriginalFilename();
		System.out.println("上传的文件名");
		File path=new File(realPath);
		if(!path.exists()){
			path.mkdirs();
		}
		
		if(!fileName.contains(".jpg") || !fileName.contains(".png")){
			
			
			User u=userService.getUserInfoId(id);
			if(u.getImage()!=null){
				File userIcon=new File(realPath+"/"+StringSplit.userImage(u.getImage()));
				if(userIcon.exists()){
					userIcon.delete();
				}
			}
			
			File destFile = new File(realPath, fileName);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			file.transferTo(destFile);
			String userIcon = "/userIcon/" + fileName;
			System.out.println("用户图像所在地址:" + realPath+"/"+fileName);
			user.setImage(userIcon);
			user.setId(id);
			userService.updateUserIcon(user);
			
			result.put("data", "0");
			result.put("errorcode", "");
			result.put("message", "上传用户图像成功");
			result.put("respcode", ResultCode.SUCCESS);
		}else{
			result.put("data", "");
			result.put("errorcode", ResultCode.VINT);
			result.put("message", "文件不合法");
			result.put("respcode", ResultCode.FAIL);
		}
		
		return result;

	}

	/**
	 * 修改用户资料
	 * 
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadUserInfo")
	@ResponseBody
	public Map<String, Object> uploadUserInfo(HttpServletRequest request,
			@RequestParam("file") MultipartFile file) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String realPath = request.getSession().getServletContext()
				.getRealPath("userIcon/");
		User user = new User();

		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		int ages = 0;
		int sexs = 0;
		if (age != null || sex != null) {
			ages = Integer.valueOf(age);
			sexs = Integer.valueOf(sex);
		}

		String address = request.getParameter("address");
		String fileName = file.getOriginalFilename();

		System.out.println("更新用户资料传递过来的参数：" + "昵称:" + name + "城市" + city
				+ "年龄:" + age + "性别" + sex + "地址:" + address + "\n"
				+ "用户图像存放的地址:" + realPath);

		File path = new File(realPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		System.out.println("图像文件名:" + fileName);
		if (!fileName.isEmpty()) {
			// 有图像的修改
			if (fileName.contains(".jpg") || fileName.contains(".png")) {

				File destFile = new File(realPath, fileName);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
				file.transferTo(destFile);
				String userIcon = "/userIcon/" + fileName;
				System.out.println("用户图像所在地址:" + userIcon);

				user.setId(id);
				user.setName(name);
				user.setImage(userIcon);
				user.setCity(city);
				user.setSex(sexs);
				user.setAge(ages);
				user.setAddress(address);
				userService.updateUserInfoImage(user);

				result.put("message", "修改用户资料成功");
				result.put("respcode", ResultCode.SUCCESS);
				result.put("data", "0");
				result.put("errorcode", ResultCode.SUCCESS);

			} else {
				// 非法文件
				result.put("message", "非法文件");
				result.put("respcode", ResultCode.FAIL);
				result.put("data", "");
				result.put("errorcode", ResultCode.FILENOLEGAL);
			}
		} else {
			// 没有图像的修改
			user.setId(id);
			user.setName(name);
			user.setCity(city);
			user.setSex(sexs);
			user.setAge(ages);
			user.setAddress(address);
			userService.updateUserInfo(user);

			result.put("message", "修改用户资料成功");
			result.put("respcode", ResultCode.SUCCESS);
			result.put("data", "0");
			result.put("errorcode", ResultCode.SUCCESS);
		}

		return result;

	}

	/**
	 * 根据用户id获取用户详细信息 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUserInfoId")
	@ResponseBody
	public Map<String, Object> getUserInfo(HttpServletRequest request)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		int id = Integer.parseInt(request.getParameter("id"));

		System.out.println("用户id:" + id);
			User info =null;
			
				User user = userService.getUserInfoId(id);
				if (user != null) {
					info= new User();
					info.setName(user.getName());
					info.setImage(user.getImage());
					info.setPhone(user.getPhone());
					info.setCity(user.getCity());
					info.setAge(user.getAge());
					info.setSex(user.getSex());
					info.setAddress(user.getAddress());
					info.setGold(user.getGold());
					info.setVip(user.getVip());

					result.put("message", "查询成功");
					result.put("respcode", ResultCode.SUCCESS);
					result.put("data", info);
					result.put("errorcode", "");
				} else {
					// 没有查询到该用户
					result.put("message", "用户不存在");
					result.put("respcode", ResultCode.FAIL);
					result.put("data", "");
					result.put("errorcode", ResultCode.UNUSEREXIST);
				}


		return result;

	}

}
