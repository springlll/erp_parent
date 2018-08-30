package com.entor.entity;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="v_storealert")
public class StoreAlert {
	private Long uuid;// 商品编号
	private String name;// 商品名称
	private Long storenum;// 库存数量
	private Long outnum;// 待出库数量
}
