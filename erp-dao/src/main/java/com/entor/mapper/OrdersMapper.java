package com.entor.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import com.entor.entity.OrderReport;
import com.entor.entity.Orders;

import tk.mybatis.mapper.common.Mapper;
@Repository
public interface OrdersMapper extends Mapper<Orders>{
/*	@Insert("insert into orders(uuid ,createtime,create,type,supplieruuid,"
			+ "totalmoney, state)"
			+"values(#{uuid},#{createtime},#{create},#{type},#{supplieruuid}"
			+ ",#{totalmoney},#{state})")
	@SelectKey(statement="select orders_seq.nextval as uuid from dual",keyProperty="uuid",before = true,resultType=Long.class)
					void add(Orders orders);*/

	@Insert("insert into orders(uuid, createtime, creater, type, supplieruuid, totalmoney, state)"
			+ " values(#{uuid}, #{createtime}, #{creater}, #{type}, #{supplieruuid}, #{totalmoney}, #{state})")
	@SelectKey(statement="SELECT orders_seq.nextval as uuid from dual"
		, keyProperty="uuid", before=true, resultType=Long.class)
	void insertPrimaryKey(Orders orders);
	
	@Select("<script>"
			+ "select t.name, sum(d.money) as money from goodstype t, goods g, orders o, orderdetail d "
			+ "where g.goodstypeuuid = t.uuid and d.ordersuuid = o.uuid and d.goodsuuid = g.uuid "
			+ "and o.type = '2' "
			+ "<if test=\"startDate != null\">and o.createtime &gt;= #{startDate}</if> "
			+ "<if test=\"endDate != null\">and o.createtime &lt;= #{endDate}</if> "
			+ "group by t.name"
			+ "</script>")


	List<OrderReport> selectOrderReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
