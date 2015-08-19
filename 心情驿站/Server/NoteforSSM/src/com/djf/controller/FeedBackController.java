package com.djf.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djf.bean.FeedBack;
import com.djf.service.FeedBackService;
import com.djf.util.ResultCode;
import com.djf.util.TimeUtil;
/**
 * 意见反馈控制器
 * @author android_djf
 *
 */
@Controller
@RequestMapping("/feedback")
public class FeedBackController {

	
	@Autowired
	FeedBackService feedBackService;
	
	@RequestMapping(value="add")
	@ResponseBody
	public Map<String,Object>  addFeedBack(HttpServletRequest request){
		
		Map<String,Object> result=new HashMap<String, Object>();
		FeedBack feedBack=new FeedBack();
		
		String name=request.getParameter("name");
		String contact=request.getParameter("contact");
		String content=request.getParameter("content");
		
		try{
			feedBack.setContact(contact);
			feedBack.setContent(content);
			feedBack.setName(name);
			feedBack.setTime(TimeUtil.getCurrentTimeAndDate());
			
			feedBackService.add(feedBack);
			
			result.put("data", "0");
			result.put("respcode", ResultCode.SUCCESS);
			result.put("errorcode", "");
			result.put("message", "用户反馈成功");
		}catch(Exception e){
			e.printStackTrace();
			result.put("data", "");
			result.put("respcode", ResultCode.FAIL);
			result.put("errorcode", ResultCode.FAIL);
			result.put("message", "用户反馈失败");
		}
		return result;
		
	}
}
