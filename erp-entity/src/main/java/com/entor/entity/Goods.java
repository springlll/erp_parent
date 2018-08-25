package com.entor.entity;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Goods {
	@Id
	private Long uuid;
	private String name;
	private String origin;
	private String producer;
	private String unit;
	private Double inprice;
	private Double outprice;
	private Long goodstypeuuid;
}
