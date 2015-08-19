package com.djf.dao;

import java.util.List;

import com.djf.bean.Comment;

/**
 * 评论dao
 * @author android_djf
 *
 */
public interface CommentDAO {

	public void addComment(Comment comment);//增加一条评论
	
	public List<Comment> queryCommentByDiaryId(String diaryId);//通过心情id查询心情评论
	
}
