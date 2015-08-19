package com.djf.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djf.bean.Diary;
import com.djf.bean.Page;
import com.djf.bean.PageForId;
import com.djf.dao.DiaryDAO;
import com.djf.service.DiaryService;
/**
 * 日记的操作
 * @author android_djf
 *
 */

@Service
public class DiaryServiceImp implements DiaryService {

	@Autowired
	private DiaryDAO diaryDAO;
	
	/**
	 * 增加一篇日记
	 */
	@Override
	public void addDiary(Diary diary) {
		// TODO Auto-generated method stub
		diaryDAO.addDiary(diary);
	}


	@Override
	public void deleteDiary(int id) throws Exception {
		// TODO Auto-generated method stub
		diaryDAO.deleteDiary(id);
	}


	@Override
	public int queryTotalDiarys(String date) throws Exception {
		// TODO Auto-generated method stub
		return diaryDAO.queryTotalDiarys(date);
	}


	@Override
	public List<Diary> queryDiarys(Page page) throws Exception {
		// TODO Auto-generated method stub
		return diaryDAO.queryDiarys(page);
	}


	@Override
	public void updateDiary(Diary diary) throws Exception {
		// TODO Auto-generated method stub
		diaryDAO.updateDiary(diary);
	}


	@Override
	public void addDiaryNoImage(Diary diary) {
		// TODO Auto-generated method stub
		diaryDAO.addDiaryNoImage(diary);
	}


	@Override
	public int queryTotalDiarysForId(String id) throws Exception {
		// TODO Auto-generated method stub
		int nums=diaryDAO.queryTotalDiarysForId(id);
		return nums;
	}


	@Override
	public List<Diary> queryDiarysforId(PageForId page) {
		// TODO Auto-generated method stub
		List<Diary> allDiary=diaryDAO.queryDiarysforId(page);
		return allDiary;
	}


	

}
