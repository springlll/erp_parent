package com.entor.business;

import java.util.List;

import com.entor.entity.Supplier;

public interface ISupplierService extends IBaseService<Supplier>{
	public List<Supplier> findByCond (Supplier supplier);
}
