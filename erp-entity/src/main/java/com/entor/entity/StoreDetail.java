package com.entor.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="storedetail")
public class StoreDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="storedetail_seq")
	@SequenceGenerator(name="storedetail_seq", sequenceName="storedetail_seq", allocationSize=1)
	private Long uuid;
	private Long storeuuid;
	private Long goodsuuid;
	private Integer num;
}

