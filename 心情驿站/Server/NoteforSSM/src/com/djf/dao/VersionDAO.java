package com.djf.dao;

import com.djf.bean.AppVersion;

/**
 * �汾����
 * @author android_djf
 *
 */
public interface VersionDAO {

	/*����id ��ѯ�ö���*/
	public AppVersion queryVersionById(int id);
}
