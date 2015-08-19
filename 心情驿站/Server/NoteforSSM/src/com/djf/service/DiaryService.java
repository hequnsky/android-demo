package com.djf.service;

import java.util.List;

import com.djf.bean.Diary;
import com.djf.bean.Page;
import com.djf.bean.PageForId;

/**
 * 日记操作service
 * @author android_djf
 *
 */
public interface DiaryService {

	 void addDiary(Diary diary);//增加一篇日记
	 
	 void addDiaryNoImage(Diary diary);//写一篇心情，没有图像

	void deleteDiary(int id) throws Exception;

	int queryTotalDiarys(String date)throws Exception;

	//分页查询单个用户的心情
	List<Diary> queryDiarys(Page page)throws Exception;
	
	/*根据用户id查询所有条数*/
	int queryTotalDiarysForId(String userid) throws Exception;
	
	/*根据用户id分页查询所有心情*/
	List<Diary> queryDiarysforId(PageForId page);

	void updateDiary(Diary diary)throws Exception;
	
	
}
