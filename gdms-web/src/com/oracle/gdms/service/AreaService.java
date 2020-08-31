package com.oracle.gdms.service;

import java.util.List;

import com.oracle.gdms.entity.AreaModel;

public interface AreaService {
	List<AreaModel> findAllProv(int pid);
	AreaModel findNameById(int areaid);
}
