package com.entor.entity;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Supplier {

	@Id
	private Integer uuid;
	private String name;
	private String address;
	private String contact;
	private String tele ;
	private String email;
	private String type;
	
	
}
