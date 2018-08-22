package com.entor.business;

import java.util.List;

import com.entor.entity.Dep;
import com.entor.entity.Menu;

public interface IDepBus extends IBaseService<Dep>{

public interface IMenuService extends IBaseService<Menu>{
	
}

int getTotal(Dep dep);

List<Dep> findAllDeps(Dep dep);

Dep findById(int uuid);
}
