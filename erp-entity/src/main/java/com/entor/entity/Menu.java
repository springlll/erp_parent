package com.entor.entity;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Transient;


import lombok.Data;
@Data
public class Menu {
	@Id
	private String menuid;
	private String menuname;
	private String icon;
	private String url;
	private String pid;
	@Transient
	private List<Menu> menus;
	
}
