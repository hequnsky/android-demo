package com.djf.service;

import java.util.List;

import com.djf.bean.Diary;
import com.djf.bean.Page;
import com.djf.bean.PageForId;

/**
 * �ռǲ���service
 * @author android_djf
 *
 */
public interface DiaryService {

	 void addDiary(Diary diary);//����һƪ�ռ�
	 
	 void addDiaryNoImage(Diary diary);//дһƪ���飬û��ͼ��

	void deleteDiary(int id) throws Exception;

	int queryTotalDiarys(String date)throws Exception;

	//��ҳ��ѯ�����û�������
	List<Diary> queryDiarys(Page page)throws Exception;
	
	/*�����û�id��ѯ��������*/
	int queryTotalDiarysForId(String userid) throws Exception;
	
	/*�����û�id��ҳ��ѯ��������*/
	List<Diary> queryDiarysforId(PageForId page);

	void updateDiary(Diary diary)throws Exception;
	
	
}
