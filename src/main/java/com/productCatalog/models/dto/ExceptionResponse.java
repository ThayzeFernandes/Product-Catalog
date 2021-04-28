package com.productCatalog.models.dto;

public class ExceptionResponse extends Exception {
	
    private Integer status_code;
    
    private StringBuilder message;
    
    public ExceptionResponse() {
    	super();
    }
    
    public ExceptionResponse(int status_code, StringBuilder message) {
    	super(message.toString());
    	this.status_code = status_code;
    }
}
