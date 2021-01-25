package com.dmurphy.response;

public enum ResponseEnum {
	CREATE("device.created", "NONE", "The device was successfully created"),
	UPDATE("device.updated", "NONE", "The device was successfully updated"),
	INVALID_MACHINE("machine.code.invalid", "ER001", "The machine code is incorrect. Check the Machine code you provided and try again."),
	MACHINE_NOT_FOUND("machine.code.not.found", "ER002", "The machine code does not match our records."),
	INVALID_SERIAL("serial.number.invalid", "ER003", "The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your entry."),
	SERIAL_NOT_FOUND( "serial.number.not.found", "ER004", "The serial number does not match our records.");
	
	ResponseEnum(String resourceKey, String errorCode, String message) {
		this.resourceKey = resourceKey;
		this.errorCode = errorCode;
		this.message = message;
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
