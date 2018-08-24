package com.entor.entity;



import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="orderdetail")
public class OrdersDetail {
	@Id
	private Long uuid;
	private Long goodsuuid;
	private String goodsname;
	private Double price;
	private Integer num;
	private Double money;
	private Date endtime;
	private Integer ender;
	private Integer storeuuid;
	private String state;
	private Long ordersuuid;
}
