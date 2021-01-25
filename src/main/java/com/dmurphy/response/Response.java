package com.dmurphy.response;

public class Response {
	
	public Response(ResponseEnum responseEnum) {
		this.resourceKey = responseEnum.getResourceKey();
		this.errorCode = responseEnum.getErrorCode();
		this.message = responseEnum.getMessage();
	}
	
	private String resourceKey;
	private String errorCode;
	private String message;
	
	public String getResourceKey() {
		return resourceKey;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getMessage() {
		return message;
	}
	
	

}
