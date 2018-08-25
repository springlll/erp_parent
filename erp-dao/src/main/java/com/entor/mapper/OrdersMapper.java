package com.entor.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.entor.entity.Orders;

import tk.mybatis.mapper.common.Mapper;
@Repository
public interface OrdersMapper extends Mapper<Orders>{
	@Insert("insert into orders(uuid ,createtime,type,supplieruuid,"
			+ "totalmoney, state)"
			+"values(#{uuid},#{createtime},#{create},#{type},#{supplieruuid}"
			+ ",#{totalmoney},#{state})")
	@SelectKey(statement="select orders_seq.nextval as uuid from dual",keyProperty="uuid",before = true,resultType=Long.class)
					void add(Orders orders);
	

}
