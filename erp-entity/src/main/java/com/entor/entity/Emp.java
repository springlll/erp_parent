package com.entor.entity;

import java.sql.Date;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Emp {
	@Id
	private Long uuid;
    private String username;
    private String pwd;
    private String name;
    private Integer gender;
    private String email;
    private String tele;
    private String address;
    private Date birthday;
    private Integer depuuid;
}
