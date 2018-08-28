package com.entor.exception;

public class OutOfStockException extends RuntimeException {
	public OutOfStockException() {}
	
	public OutOfStockException(String msg) {
		super(msg);
	}
	
}
