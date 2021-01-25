package com.dmurphy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String serialNumber;
	private String machineCode;
	private String deviceName;
	
	public Long getId() {
		return id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	
}
