package com.hcl.exception;

public class ContentNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ContentNotFoundException(String message){
		super(message);
	}
}
