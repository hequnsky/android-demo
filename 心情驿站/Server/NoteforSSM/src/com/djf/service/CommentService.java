package com.djf.service;

import java.util.List;

import com.djf.bean.Comment;

/**
 * ����service
 * @author android_djf
 *
 */
public interface CommentService {

	/*����һ������*/
	public void addComment(Comment comment);
	
	/*ͨ������id��ѯ����*/
	public List<Comment> queryCommentByDiaryId(String diaryId);
}
