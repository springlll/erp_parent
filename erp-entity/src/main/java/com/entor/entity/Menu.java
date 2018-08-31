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
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true ;
			
		}
		if(obj instanceof Menu) {
			Menu menu = (Menu) obj;
			return this.menuid.equals(menu.getMenuid());
		}
		return false ;
	}
}
