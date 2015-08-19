package com.djf.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djf.bean.AppVersion;
import com.djf.dao.VersionDAO;
import com.djf.service.VersionService;

/**
 * °æ±¾¿ØÖÆ
 * @author android_djf
 *
 */
@Service
public class VersionServiceImp implements VersionService {

	@Autowired
	private VersionDAO versionDao;

	@Override
	public AppVersion queryVersionById(int id) {
		// TODO Auto-generated method stub
		AppVersion v=versionDao.queryVersionById(id);
		return v;
	}
	
}
