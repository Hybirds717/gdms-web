package com.oracle.gdms.service.impl;

import org.apache.ibatis.session.SqlSession;

public abstract class BaseService {

	protected SqlSession session;
	
	protected void free() {
		if ( session != null ) {
			session.close();
		}
	}
}
