package com.entor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.entor.entity.Menu;

import tk.mybatis.mapper.common.Mapper;
@Repository
public interface MenuMapper extends Mapper<Menu>{
@Select ("slect m.*from role_menu r , menu m where r.menuuuid and"
		+ "r.roleuuid = #{roleId}")
	List<Menu> getRolesMenu(@Param("roleId") long roleId);
}
