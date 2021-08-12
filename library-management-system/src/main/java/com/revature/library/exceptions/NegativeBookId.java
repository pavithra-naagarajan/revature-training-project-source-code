package com.revature.library.exceptions;

public class NegativeBookId extends Exception {
public NegativeBookId() {
		
	}
	public NegativeBookId(String msg) {
		super(msg);
	}
}
