package com.entor.entity;

import javax.persistence.Id;

import lombok.Data;

@Data
public class RoleMenu {
	@Id
	private Long roleuuid;
	@Id
	private Long menuuuid;
	
}
