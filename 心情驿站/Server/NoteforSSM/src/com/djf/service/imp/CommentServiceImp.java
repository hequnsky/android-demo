package com.djf.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djf.bean.Comment;
import com.djf.dao.CommentDAO;
import com.djf.service.CommentService;
/**
 * ÆÀÂÛservice
 * @author android_djf
 *
 */
@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentDAO commentDao;

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.addComment(comment);
	}

	@Override
	public List<Comment> queryCommentByDiaryId(String diaryId) {
		// TODO Auto-generated method stub
		List<Comment> comment=commentDao.queryCommentByDiaryId(diaryId);
		return comment;
	}
	
	
}
