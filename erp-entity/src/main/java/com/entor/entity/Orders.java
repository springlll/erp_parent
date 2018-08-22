package com.entor.entity;

import java.util.Date;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Orders {
	@Id
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
}
