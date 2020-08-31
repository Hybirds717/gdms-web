package com.oracle.gdms.service.impl;

import com.oracle.gdms.dao.UserDao;
import com.oracle.gdms.entity.UserModel;
import com.oracle.gdms.service.UserService;
import com.oracle.gdms.util.GDMSUtil;

public class UserServiceImpl extends BaseService  implements UserService {
	private UserDao userdao;
	
	@Override
	public int add(UserModel user) {
		try {
			// TODO 增加一个新用户的业务
			session = GDMSUtil.getSession();
			userdao = session.getMapper(UserDao.class);
			int c = userdao.add(user);
			session.commit();
			return c;
		} catch(Exception ex){
			ex.printStackTrace();
			session.rollback();// 回滚事务
		} finally {
			free();
		}
		return 0;
	}

	@Override
	public UserModel login(UserModel user) {
		try {
			// TODO 增加一个新用户的业务
			session = GDMSUtil.getSession();
			userdao = session.getMapper(UserDao.class);
			return userdao.login(user);
		} catch(Exception ex){
			ex.printStackTrace();
			session.rollback();// 回滚事务
		} finally {
			free();
		}
		return null;
	}

	@Override
	public boolean hasMobile(String mobile) {
		try {
			// TODO 增加一个新用户的业务
			session = GDMSUtil.getSession();
			userdao = session.getMapper(UserDao.class);
			int c = userdao.hasMobile(mobile);
			return c > 0;
		} catch(Exception ex){
			ex.printStackTrace();
			session.rollback();// 回滚事务
		} finally {
			free();
		}
		return false;
	}
	
}
