package com.djf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djf.bean.SystemMessage;
import com.djf.bean.SystemMessageInfo;
import com.djf.bean.User;
import com.djf.service.MessageService;
import com.djf.service.UserService;
import com.djf.util.ResultCode;
import com.djf.util.TimeUtil;

@Controller
@RequestMapping("/message")
public class MessageController {

	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="add")
	@ResponseBody
	public Map<String,Object> addMessage(HttpServletRequest request){
		
		Map<String,Object> result=new HashMap<String, Object>();
		SystemMessage systemMessage=new SystemMessage();
		
		String sendId=request.getParameter("sendid");
		String acceptId=request.getParameter("acceptid");
		String sysMessage=request.getParameter("message");
		
		try{
			systemMessage.setAccept_id(acceptId);
			systemMessage.setMessage_content(sysMessage);
			systemMessage.setSend_id(sendId);
			systemMessage.setMessage_date(TimeUtil.getCurrentTimeAndDate());
			systemMessage.setSend_date(TimeUtil.getCurrentDate());
			messageService.addSystemMessage(systemMessage);
			
			result.put("respcode", ResultCode.SUCCESS);
			result.put("data", ResultCode.SUCCESS);
			result.put("errorcode", "");
			result.put("message", "私信发送成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("respcode", ResultCode.FAIL);
			result.put("data", "");
			result.put("errorcode", ResultCode.FAIL);
			result.put("message", "私信发送失败");
		}
		return result;
		
	}
	
	/**
	 * 查询所有私信，不包括黑名单的
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryAllMessage")
	@ResponseBody
	public Map<String,Object> queryAllSystemMessage(HttpServletRequest request){
		
		/*第一层的结果集*/
		Map<String,Object> result=new HashMap<String, Object>();
		
		List<SystemMessageInfo> allMessageInfo=new ArrayList<SystemMessageInfo>();
		SystemMessageInfo info;
		
		String acceptid=request.getParameter("acceptid");
		try{
			List<SystemMessage> allMessage=messageService.queryAll(acceptid);
			if(allMessage.size()>0){
				for(int i=0;i<allMessage.size();i++){
					SystemMessage sm=allMessage.get(i);
					
					info=new SystemMessageInfo();
					info.setId(sm.getId());
					info.setAccept_id(sm.getAccept_id());
					info.setMessage_content(sm.getMessage_content());
					info.setMessage_date(sm.getMessage_date());
					info.setSend_date(sm.getSend_date());
					info.setSend_id(sm.getSend_id());
					info.setUser(userService.getUserInfoId(Integer.parseInt(sm.getSend_id())));
					info.setState(sm.getState());
					allMessageInfo.add(info);
				}
				
				
				
				
			}
			result.put("message", "查询成功");
			result.put("data", allMessageInfo);
			result.put("errorcode", "");
			result.put("respcode", ResultCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			result.put("message", "查询失败");
			result.put("data", "");
			result.put("errorcode", ResultCode.FAIL);
			result.put("respcode", ResultCode.FAIL);
		}
		return result;
		
	}
	
	/**
	 * 拉入黑名单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addBlackList")
	@ResponseBody
	public Map<String,Object> addBlackList(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		String sendUserId=request.getParameter("sendid");
		String state=request.getParameter("state");
		
		SystemMessage systemMessage=new SystemMessage();
		systemMessage.setSend_id(sendUserId);
		systemMessage.setState(Integer.parseInt(state));
		messageService.updateMessage(systemMessage);
		result.put("message", "拉黑成功");
		result.put("data", ResultCode.SUCCESS);
		result.put("errorcode", "");
		result.put("respcode", ResultCode.SUCCESS);
		return result;
	}
	
	@RequestMapping(value="queryfordate")
	@ResponseBody
	public Map<String,Object> queryMessageForDate(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		
		String date=request.getParameter("message_date");
		List<SystemMessage> allMessage=messageService.queryMessageforDate(date);
		result.put("message", "查询成功");
		result.put("errorcode", ResultCode.SUCCESS);
		result.put("data", allMessage);
		result.put("respcode", ResultCode.SUCCESS);
		return result;
		
	}
	
}
