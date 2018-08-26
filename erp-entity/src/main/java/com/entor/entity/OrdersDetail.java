package com.entor.entity;



import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="orderdetail")
public class OrdersDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orders_seq")
	@SequenceGenerator(name="orders_seq",sequenceName="orders_seq", allocationSize=1)

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
