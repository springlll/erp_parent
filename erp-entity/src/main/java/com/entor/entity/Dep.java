package com.entor.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
public class Dep {
	@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dep_seq")
@SequenceGenerator(name="dep_seq", sequenceName="dep_seq", allocationSize=1)	
	private Integer uuid;
	private String name;
	private String tele;
	
}
