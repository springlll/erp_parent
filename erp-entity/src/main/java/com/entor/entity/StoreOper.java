package com.entor.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="storeoper")
public class StoreOper {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="storeoper_seq")
	@SequenceGenerator(name="storeoper_seq", sequenceName="storeoper_seq", allocationSize=1)
	private Long uuid;
	private Long empuuid;
	private Date opertime;
	private Long storeuuid;
	private Long goodsuuid;
	private Integer num;
	private String type;
}

