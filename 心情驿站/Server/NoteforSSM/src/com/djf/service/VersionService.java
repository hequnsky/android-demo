package com.djf.service;

import com.djf.bean.AppVersion;

/**
 * 版本控制
 * @author android_djf
 *
 */
public interface VersionService {

	/*根据id查询该对象*/
	public AppVersion queryVersionById(int id);
}
