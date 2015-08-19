package com.djf.service;

import java.util.List;

import com.djf.bean.Comment;

/**
 * 评论service
 * @author android_djf
 *
 */
public interface CommentService {

	/*增加一条评论*/
	public void addComment(Comment comment);
	
	/*通过心情id查询评论*/
	public List<Comment> queryCommentByDiaryId(String diaryId);
}
