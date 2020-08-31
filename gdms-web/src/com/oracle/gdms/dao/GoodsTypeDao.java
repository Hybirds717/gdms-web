package com.oracle.gdms.dao;

import com.oracle.gdms.entity.GoodsType;

public interface GoodsTypeDao {
	
	int add(GoodsType obj);
	
	GoodsType findById(int gtid);
	
}
