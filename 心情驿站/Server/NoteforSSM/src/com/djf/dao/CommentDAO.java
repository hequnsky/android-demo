package com.djf.dao;

import java.util.List;

import com.djf.bean.Comment;

/**
 * ����dao
 * @author android_djf
 *
 */
public interface CommentDAO {

	public void addComment(Comment comment);//����һ������
	
	public List<Comment> queryCommentByDiaryId(String diaryId);//ͨ������id��ѯ��������
	
}
