package com.oracle.gdms.dao;

import java.util.List;

import com.oracle.gdms.entity.AreaModel;

public interface AreaDao {
	
	List<AreaModel> findAllProv(AreaModel area);
	
	AreaModel findNameById(int areaid);
	
}
