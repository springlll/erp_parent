package com.entor.business;

import java.util.List;

import com.entor.entity.Dep;

public interface IDepBus {

	List<Dep> findDeps(Dep dep);

	List<Dep> findAllDeps(Dep dep);
	int getTotal(Dep dep);
	
	void addDep(Dep dep);

}
