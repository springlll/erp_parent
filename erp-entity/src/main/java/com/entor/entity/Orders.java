package com.entor.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="orders_seq")
	@SequenceGenerator(name="orders_seq",sequenceName="orders_seq", allocationSize=1)

	
	private Long uuid;
	private Date createtime;
	private Date checktime;
	private Date starttime;
	private Date endtime;
	private String type;
	private Long creater;
	private Long checker;
	private Long starter;
	private Long ender;
	private Long supplieruuid;
	private Double totalmoney;
	private String state;
	@Transient
	private List<OrdersDetail> ordersDetails;
}
