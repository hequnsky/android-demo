package com.djf.dao;

import java.util.List;

import com.djf.bean.Diary;
import com.djf.bean.Page;
import com.djf.bean.PageForId;

public interface DiaryDAO {

	void addDiary(Diary diary);//写一篇心情有图像
	
	void addDiaryNoImage(Diary diary);//写一篇心情，没有图像

	
	void deleteDiary(int id) throws Exception;

	/*根据当天查询查询条数*/
	int queryTotalDiarys(String date)throws Exception;

	//分页查询所有用户的心情
	List<Diary> queryDiarys(Page page);
	
	/*根据用户id查询所有条数*/
	int queryTotalDiarysForId(String id) throws Exception;
	
	/*根据用户id分页查询所有心情*/
	List<Diary> queryDiarysforId(PageForId page);

	void updateDiary(Diary diary);
}
