package com.djf.dao;

import java.util.List;

import com.djf.bean.Diary;
import com.djf.bean.Page;
import com.djf.bean.PageForId;

public interface DiaryDAO {

	void addDiary(Diary diary);//дһƪ������ͼ��
	
	void addDiaryNoImage(Diary diary);//дһƪ���飬û��ͼ��

	
	void deleteDiary(int id) throws Exception;

	/*���ݵ����ѯ��ѯ����*/
	int queryTotalDiarys(String date)throws Exception;

	//��ҳ��ѯ�����û�������
	List<Diary> queryDiarys(Page page);
	
	/*�����û�id��ѯ��������*/
	int queryTotalDiarysForId(String id) throws Exception;
	
	/*�����û�id��ҳ��ѯ��������*/
	List<Diary> queryDiarysforId(PageForId page);

	void updateDiary(Diary diary);
}
