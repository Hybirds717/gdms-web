package com.oracle.gdms.service.impl;

import java.util.List;

import com.oracle.gdms.dao.AreaDao;
import com.oracle.gdms.entity.AreaModel;
import com.oracle.gdms.service.AreaService;
import com.oracle.gdms.util.GDMSUtil;

public class AreaServiceImpl extends BaseService implements AreaService {
	
	@Override
	public List<AreaModel> findAllProv(int pid) {
		try {
			// TODO ����һ�����û���ҵ��
			session = GDMSUtil.getSession();
			AreaDao dao = session.getMapper(AreaDao.class);
			AreaModel area = new AreaModel();
			area.setParentid(pid);
			return dao.findAllProv(area);
		} catch(Exception ex){
			ex.printStackTrace();
			session.rollback();// �ع�����
		} finally {
			free();
		}
		return null;
	}

	@Override
	public AreaModel findNameById(int areaid) {
		try {
			// TODO ����һ�����û���ҵ��
			session = GDMSUtil.getSession();
			AreaDao dao = session.getMapper(AreaDao.class);
			return dao.findNameById(areaid);
		} catch(Exception ex){
			ex.printStackTrace();
			session.rollback();// �ع�����
		} finally {
			free();
		}
		return null;
	}

}
