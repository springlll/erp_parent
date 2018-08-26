package com.entor.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="store_seq")
	@SequenceGenerator(name="store_seq", sequenceName="store_seq", allocationSize=1)
	private Long uuid;
	private String name;
	private Long empuuid;
}
