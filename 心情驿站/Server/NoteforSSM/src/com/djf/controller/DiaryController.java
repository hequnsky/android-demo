package com.djf.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.djf.bean.Comment;
import com.djf.bean.Diary;
import com.djf.bean.DiaryforUserIcon;
import com.djf.bean.Page;
import com.djf.bean.PageForId;
import com.djf.bean.User;
import com.djf.service.CommentService;
import com.djf.service.DiaryService;
import com.djf.service.UserService;
import com.djf.util.InfoMation;
import com.djf.util.ResultCode;
import com.djf.util.TimeUtil;

@Controller
@RequestMapping("/diary")
public class DiaryController {

	@Autowired
	private DiaryService diaryService;

	@Autowired
	private CommentService commentService;

	// UserService
	@Autowired
	private UserService userService;

	@RequestMapping("testDiary")
	public String toUploadPage() {
		return "diary";
	}

	/**
	 * 写心情,针对有图像的处理
	 * 
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="addDiary")
	@ResponseBody
	public Map<String, Object> addDiary(HttpServletRequest request,
			@RequestParam("file") MultipartFile file[]) throws Exception {
		Diary diary = new Diary();
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> imagePath = new ArrayList<String>();
		String realPath = request.getSession().getServletContext()
				.getRealPath("userDiary/");

		String content = request.getParameter("diarycontent");
		int userid = Integer.parseInt(request.getParameter("userid"));

		System.out.println("服务器收到写心情传递过来的参数用户id:" + userid + "内容:" + content);
		System.out.println("上传图片的根目录为:" + realPath);

		for (int i = 0; i < file.length; i++) {

			MultipartFile multFile = file[i];
			if (multFile.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				System.out.println("文件的长度为:" + multFile.getSize());
				System.out.println("文件的类型:" + multFile.getContentType());
				System.out.println("文件的名称:" + multFile.getName());
				System.out.println("文件的原名:" + multFile.getOriginalFilename());
				FileUtils.copyInputStreamToFile(multFile.getInputStream(),
						new File(realPath, multFile.getOriginalFilename()));
				String fileName = "/userDiary/"
						+ multFile.getOriginalFilename();
				imagePath.add(fileName);
			}
		}

		if (imagePath.size() > 0) {
			// 有图像
			if (imagePath.size() == 1) {
				diary.setImgone(imagePath.get(0));
			} else if (imagePath.size() == 2) {
				diary.setImgone(imagePath.get(0));
				diary.setImgtwo(imagePath.get(1));
			} else if (imagePath.size() == 3) {
				diary.setImgone(imagePath.get(0));
				diary.setImgtwo(imagePath.get(1));
				diary.setImgthree(imagePath.get(2));
			} else if (imagePath.size() == 4) {
				diary.setImgone(imagePath.get(0));
				diary.setImgtwo(imagePath.get(1));
				diary.setImgthree(imagePath.get(2));
				diary.setImgfour(imagePath.get(3));
			} else if (imagePath.size() == 5) {
				diary.setImgone(imagePath.get(0));
				diary.setImgtwo(imagePath.get(1));
				diary.setImgthree(imagePath.get(2));
				diary.setImgfour(imagePath.get(3));
				diary.setImgfive(imagePath.get(4));
			} else if (imagePath.size() == 6) {
				diary.setImgone(imagePath.get(0));
				diary.setImgtwo(imagePath.get(1));
				diary.setImgthree(imagePath.get(2));
				diary.setImgfour(imagePath.get(3));
				diary.setImgfive(imagePath.get(4));
				diary.setImgsix(imagePath.get(5));
			}
			diary.setUserid(userid);
			diary.setContent(content);
			diary.setTime(TimeUtil.getCurrentTime());
			diary.setDate(TimeUtil.getCurrentDate());
			diaryService.addDiary(diary);

		} else {
			// 无图像
			diary.setUserid(userid);
			diary.setContent(content);
			diary.setTime(TimeUtil.getCurrentTime());
			diary.setDate(TimeUtil.getCurrentDate());
			diaryService.addDiary(diary);
		}

		result.put("data", "0");
		result.put("respcode", ResultCode.SUCCESS);
		result.put("errorcode", "");
		result.put("message", "上传文件成功");

		return result;
	}
	
	/**
	 * 写心情，不支持图片上传
	 * @param request
	 * @return
	 */
	@RequestMapping(value="addDiaryNoImage")
	@ResponseBody
	public Map<String,Object> addDiaryNoImage(HttpServletRequest request){
		Map<String,Object> result=new HashMap<String, Object>();
		Diary diary=new Diary();
		String content=request.getParameter("diarycontent");
		String userid=request.getParameter("userid");
		
		try{
			diary.setContent(content);
			diary.setUserid(Integer.parseInt(userid));
			diary.setDate(TimeUtil.getCurrentDate());
			diary.setTime(TimeUtil.getCurrentTime());
			diaryService.addDiaryNoImage(diary);
			result.put("data", "0");
			result.put("respcode", ResultCode.SUCCESS);
			result.put("errorcode", "");
			result.put("message", "写心情成功");
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("data", "0");
			result.put("respcode", ResultCode.FAIL);
			result.put("errorcode", ResultCode.FAIL);
			result.put("message", "写心情失败");
		}
		return result;
		
	}
	

	/**
	 * 删除指定id日志
	 * */
	@RequestMapping("deleteDiary")
	@ResponseBody
	public Map<String, String> deleteDiary(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		int id = Integer.parseInt(request.getParameter("diaryID"));
		try {
			diaryService.deleteDiary(id);
			result.put("code", "0");
			result.put("data", "0");
			result.put("msg", "删除心情成功！");
		} catch (Exception e) {
			result.put("result", "-1");
			result.put("data", "");
			result.put("msg", "删除失败！");
		}
		return result;
	}

	/**
	 * 查询所有diary 
	 * 根据天当天日期进行
	 * 分页查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("allDiarys")
	@ResponseBody
	public Map<String, Object> viewMyDiarys(HttpServletRequest request) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
//		String d = request.getParameter("date");

		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Object> diarys = new ArrayList<Object>();
		Map<String, String> result = new HashMap<String, String>();

		Page page = new Page();
		page.setPageNo(pageNo);
//		page.setDate(d);
	/*	int totalPage;
		try {
			totalPage = diaryService.queryTotalDiarys(d);
		} catch (Exception e) {
			returnData.put("code", "-1");
			returnData.put("msg", "查询心情失败！");
			returnData.put("data", "");
			return returnData;

		}*/
//		page.setTotalPage(totalPage);

		try {
			List<DiaryforUserIcon> allData = new ArrayList<DiaryforUserIcon>();
			DiaryforUserIcon di;
			List<Diary> tempDirays = diaryService.queryDiarys(page);
			
			List<Comment> allComment = null;
			Comment comment = null;
			for (int i = 0; i < tempDirays.size(); i++) {
				di = new DiaryforUserIcon();
				User u = userService.getUserInfoId(tempDirays.get(i)
						.getUserid());
				di.setContent(tempDirays.get(i).getContent());
				di.setDate(tempDirays.get(i).getDate());
				di.setTime(tempDirays.get(i).getTime());
				di.setImgone(tempDirays.get(i).getImgone());
				di.setImgtwo(tempDirays.get(i).getImgtwo());
				di.setImgthree(tempDirays.get(i).getImgthree());
				di.setImgfour(tempDirays.get(i).getImgfour());
				di.setImgfive(tempDirays.get(i).getImgfive());
				di.setImgsix(tempDirays.get(i).getImgsix());
				di.setUserName(u.getName());
				di.setAge(String.valueOf(u.getAge()));
				
				
				//说明有评论
				if(commentService.queryCommentByDiaryId(String
						.valueOf(tempDirays.get(i).getId())).size()>0){
					
					allComment=new ArrayList<Comment>();
					
					//遍历评论列表
					for(int k=0;k<commentService.queryCommentByDiaryId(String
						.valueOf(tempDirays.get(i).getId())).size();k++){
						comment=new Comment();
						comment.setComment_detail(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_detail());
						
						comment.setComment_name(userService.getUserInfoId(Integer.parseInt(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_user_id())).getName());
						
						comment.setComment_time(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_time());
						
						comment.setComment_user_id(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_user_id());
						
						comment.setDiaryid(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getDiaryid());
						
						comment.setId(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getId());
						allComment.add(comment);
					}
					
					di.setAllComment(allComment);
				}//没有评论
				else {
					di.setAllComment(null);
				}
				
				

				di.setId(tempDirays.get(i).getId());
				di.setUserid(tempDirays.get(i).getUserid());
				di.setUsreIcon(u.getImage());
				allData.add(di);
			}
			diarys.addAll(allData);
			returnData.put("respcode", ResultCode.SUCCESS);
			returnData.put("message", "查询所有发表心情成功！");
			returnData.put("data", diarys);
			result.put("errorcode", ResultCode.SUCCESS);

		} catch (Exception e) {
			returnData.put("respcode", ResultCode.FAIL);
			returnData.put("msg", "查询所有心情失败！");
			result.put("errorcode", ResultCode.FAIL);
			returnData.put("data", "");
		}

		return returnData;
	}
	
	/**
	 * 查询所有diary
	 * 
	 *  根据userid分页查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("allMeDiarys")
	@ResponseBody
	public Map<String, Object> allMeDiarys(HttpServletRequest request) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		String userId = request.getParameter("userid");

		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Object> diarys = new ArrayList<Object>();
		Map<String, String> result = new HashMap<String, String>();

		PageForId page = new PageForId();
		page.setPageNo(pageNo);
		page.setUserId(userId);
		int totalPage = 0;
		
			try {
				totalPage = diaryService.queryTotalDiarysForId(userId);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		page.setTotalPage(totalPage);

		try {
			List<DiaryforUserIcon> allData = new ArrayList<DiaryforUserIcon>();
			DiaryforUserIcon di;
			List<Diary> tempDirays = diaryService.queryDiarysforId(page);
			
			List<Comment> allComment = null;
			Comment comment = null;
			for (int i = 0; i < tempDirays.size(); i++) {
				di = new DiaryforUserIcon();
				User u = userService.getUserInfoId(tempDirays.get(i)
						.getUserid());
				di.setContent(tempDirays.get(i).getContent());
				di.setDate(tempDirays.get(i).getDate());
				di.setTime(tempDirays.get(i).getTime());
				di.setImgone(tempDirays.get(i).getImgone());
				di.setImgtwo(tempDirays.get(i).getImgtwo());
				di.setImgthree(tempDirays.get(i).getImgthree());
				di.setImgfour(tempDirays.get(i).getImgfour());
				di.setImgfive(tempDirays.get(i).getImgfive());
				di.setImgsix(tempDirays.get(i).getImgsix());
				di.setUserName(u.getName());
				di.setAge(String.valueOf(u.getAge()));
				
				
				//说明有评论
				if(commentService.queryCommentByDiaryId(String
						.valueOf(tempDirays.get(i).getId())).size()>0){
					
					allComment=new ArrayList<Comment>();
					
					//遍历评论列表
					for(int k=0;k<commentService.queryCommentByDiaryId(String
						.valueOf(tempDirays.get(i).getId())).size();k++){
						comment=new Comment();
						comment.setComment_detail(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_detail());
						
						comment.setComment_name(userService.getUserInfoId(Integer.parseInt(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_user_id())).getName());
						
						comment.setComment_time(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_time());
						
						comment.setComment_user_id(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getComment_user_id());
						
						comment.setDiaryid(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getDiaryid());
						
						comment.setId(commentService.queryCommentByDiaryId(String
								.valueOf(tempDirays.get(i).getId())).get(k).getId());
						allComment.add(comment);
					}
					
					di.setAllComment(allComment);
				}//没有评论
				else {
					di.setAllComment(null);
				}
				
				

				di.setId(tempDirays.get(i).getId());
				di.setUserid(tempDirays.get(i).getUserid());
				di.setUsreIcon(u.getImage());
				allData.add(di);
			}
			diarys.addAll(allData);
			returnData.put("respcode", ResultCode.SUCCESS);
			returnData.put("message", "查询所有发表心情成功！");
			returnData.put("data", diarys);
			result.put("errorcode", ResultCode.SUCCESS);

		} catch (Exception e) {
			returnData.put("respcode", ResultCode.FAIL);
			returnData.put("msg", "查询所有心情失败！");
			result.put("errorcode", ResultCode.FAIL);
			returnData.put("data", "");
		}

		return returnData;
	}


	/*	*//**
	 * 修改日志
	 */
	/*
	 * @SuppressWarnings("finally")
	 * 
	 * @RequestMapping(value="updateDiary",method=RequestMethod.POST)
	 * 
	 * @ResponseBody// public Map<String,Object> updateDiary(HttpServletRequest
	 * request){
	 * 
	 * Diary diary=new Diary();
	 * diary.setContent(request.getParameter("diarycontent")); diary.setDate(new
	 * Date(System.currentTimeMillis()).toString());
	 * diary.setTitle(request.getParameter("diarytitle"));
	 * diary.setId(Integer.parseInt(request.getParameter("id")));
	 * 
	 * Map<String,Object> result=new HashMap<String, Object>();
	 * 
	 * try{ diaryService.updateDiary(diary); result.put("code", "0");
	 * result.put("data", "0"); result.put("msg", "更新成功！"); }catch(Exception e){
	 * e.printStackTrace(); result.put("result", "1"); result.put("data", "");
	 * result.put("msg", "更新失败！"); } return result; }
	 */
}
