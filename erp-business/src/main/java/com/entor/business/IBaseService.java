package com.entor.business;

import java.util.List;

public interface IBaseService<T> {
	
	List<T> findAll();
	List<T> find(T entity);
	
	T get(T entity);
	T findById(int uuid);
	void add(T entity);
	void update(T entity);
	void delete(T entity);
	int count(T entity);
	
}
