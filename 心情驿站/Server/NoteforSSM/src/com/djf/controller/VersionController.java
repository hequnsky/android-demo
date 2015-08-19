package com.djf.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djf.bean.AppVersion;
import com.djf.service.VersionService;
import com.djf.service.imp.VersionServiceImp;
import com.djf.util.ResultCode;

/**
 * 版本控制器
 * @author android_djf
 *
 */

@Controller
@RequestMapping("/version")
public class VersionController {

	@Autowired
	VersionService versionService;
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> updateVersion(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		
		int id=Integer.parseInt(request.getParameter("id"));
		int vCode=Integer.parseInt(request.getParameter("vcode"));
		
		AppVersion appBean=versionService.queryVersionById(id);
		if(appBean!=null){
			
			if(vCode>appBean.getVcode()){
				result.put("respcode", "0");
				result.put("message", "有新版本");
				result.put("data", appBean);
				result.put("errorcode", "");
			}else{
				result.put("code", "0");
				result.put("msg", "已经是最新版本");
				result.put("data", "");
			}
			
		}else{
			result.put("code", "1");
			result.put("msg", "版本信息查询失败");
			result.put("data", "");
		}
		return result;
		
	}
}
