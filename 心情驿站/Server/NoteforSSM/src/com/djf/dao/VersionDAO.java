package com.djf.dao;

import com.djf.bean.AppVersion;

/**
 * 版本控制
 * @author android_djf
 *
 */
public interface VersionDAO {

	/*根据id 查询该对象*/
	public AppVersion queryVersionById(int id);
}
