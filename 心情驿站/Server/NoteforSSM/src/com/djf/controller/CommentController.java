package com.djf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djf.bean.Comment;
import com.djf.service.CommentService;
import com.djf.service.imp.CommentServiceImp;
import com.djf.util.ResultCode;
import com.djf.util.TimeUtil;
/**
 * 心情评论
 * @author android_djf
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	/**
	 * 增加一条评论
	 * @param request
	 * @return
	 */
	@RequestMapping(value="add")
	@ResponseBody
	public Map<String,Object> addComment(HttpServletRequest request){
		
		Map<String,Object> result=new HashMap<String, Object>();
		Comment comment=new Comment();
		
		String diaryId=request.getParameter("diaryId");
		String 	comment_user_id=request.getParameter("cUserId");
		String comment_detail=request.getParameter("content");
		
		try{
			comment.setComment_detail(comment_detail);
			comment.setComment_user_id(comment_user_id);
			comment.setDiaryid(diaryId);
			comment.setComment_time(TimeUtil.getCurrentTimeAndDate());
			
			result.put("data", "0");
			result.put("errorcode", "");
			result.put("message", "评论成功");
			result.put("respcode", ResultCode.SUCCESS);
			commentService.addComment(comment);
		}catch(Exception e){
			e.printStackTrace();
			result.put("data", "");
			result.put("errorcode", ResultCode.FAIL);
			result.put("message", "评论失败");
			result.put("respcode", ResultCode.FAIL);
		}
		return result;
		
	}
	
	/**
	 * 通过心情id查询对应的评论
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryCommentByDiaryId")
	@ResponseBody
	public Map<String,Object> queryCommentByDiaryId(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		String diaryId=request.getParameter("diaryId");
		
		try{
			List<Comment> allComment=commentService.queryCommentByDiaryId(diaryId);
			
			result.put("data", allComment);
			result.put("errorcode", ResultCode.SUCCESS);
			result.put("message", "查询评论成功");
			result.put("respcode", ResultCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			result.put("data", "");
			result.put("errorcode", ResultCode.FAIL);
			result.put("message", "查询评论失败");
			result.put("respcode", ResultCode.FAIL);
		}
		return result;
	}
}
