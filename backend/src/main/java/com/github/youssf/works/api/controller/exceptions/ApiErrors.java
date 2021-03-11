package com.github.youssf.works.api.controller.exceptions;

public class ApiErrors {
	
	private String field;
	private String messageError;
	
	public ApiErrors(String field, String messageError) {		
		this.field = field;
		this.messageError = messageError;
	}	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
}
