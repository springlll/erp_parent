package com.entor.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Tree {
	
	private String id;
	private String text;
	private boolean checked;
	private List<Tree> children = new ArrayList<Tree>();
	
	
}
