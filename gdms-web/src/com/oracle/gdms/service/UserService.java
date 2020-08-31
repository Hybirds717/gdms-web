package com.oracle.gdms.service;

import com.oracle.gdms.entity.UserModel;

/**
 * �������û����ҵ���ܽӿ�
 * @author VX��23482378
 * 2020-7-6
 */
public interface UserService {
	
	/**
	 * ���û�ע��ҵ��
	 * @param user
	 * @return
	 */
	int add(UserModel user);

	UserModel login(UserModel user);

	/**
	 * ����ֻ������Ƿ�ռ��
	 * @param mobile
	 * @return
	 */
	boolean hasMobile(String mobile);
}
