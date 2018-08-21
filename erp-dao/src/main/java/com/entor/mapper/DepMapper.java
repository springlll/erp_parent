package com.entor.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.entor.entity.Dep;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface DepMapper extends Mapper<Dep>{
/*	@Select("select * from dep")
	public List<Dep> findAll() ;
	public List<Dep> getDeps();*/
	
}
