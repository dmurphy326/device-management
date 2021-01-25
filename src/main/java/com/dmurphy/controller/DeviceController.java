package com.dmurphy.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dmurphy.entities.Device;
import com.dmurphy.repo.DeviceRepo;
import com.dmurphy.response.Response;
import com.dmurphy.response.ResponseEnum;


@RestController
public class DeviceController {
	
	@Autowired DeviceRepo repo;
	
	// POST create
	@PostMapping(path = "/createDevice", consumes = "application/json", produces = "application/json")
	public ArrayList<Object> createDevice(@RequestBody Device device) {
		ArrayList<Object> list = new ArrayList<Object>();
		
		validate(device, list);
		
		// If no error was added, created message successful
		if(list.isEmpty()) {
			list.add(new Response(ResponseEnum.CREATE));
			repo.save(device);
		}
		
		return list;
	}
	
	// PUT update
	@PutMapping("updateDevice/serialNumber/{serialNumber}/machineCode/{machineCode}")
	public ArrayList<Object> updateDevice(@PathVariable("serialNumber") String serialNumber, 
			@PathVariable("machineCode") String machineCode, @RequestBody Device updatedDevice) {
		ArrayList<Object> list = new ArrayList<Object>();
		
		validate(updatedDevice, list);
		
		Device device = findBySerialNumberAndMachineCode(serialNumber, machineCode, list);
		
		// If no error was added, created message successful
		if(list.isEmpty()) {
			device.setDeviceName(updatedDevice.getDeviceName());
			device.setMachineCode(updatedDevice.getMachineCode());
			device.setSerialNumber(updatedDevice.getSerialNumber());
			repo.save(device);
			
			list.add(new Response(ResponseEnum.UPDATE));
		}
		
		return list;
	}
	
	
	// GET find
	@GetMapping("findDevice/serialNumber/{serialNumber}/machineCode/{machineCode}")
	public ArrayList<Object> findDevice(@PathVariable("serialNumber") String serialNumber, @PathVariable("machineCode") String machineCode) {
		ArrayList<Object> list = new ArrayList<Object>();
		
		Device device = findBySerialNumberAndMachineCode(serialNumber, machineCode, list);
		
		if(list.isEmpty()) {
			list.add(device);
		}
		
		return list;
	}
	
	// Helper methods
	private boolean validateSerialNumber(String serialNumber) {
		Pattern pattern = Pattern.compile("[A-Z0-9]+-[A-Z0-9]+", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(serialNumber);
		
		return matcher.find();
	}
	
	private boolean validateMachineCode(String machineCode) {
		boolean valid = false;
		if(machineCode != null && !machineCode.trim().isEmpty()) {
			valid = true;
		}
		
		return valid;
	}
	
	private void validate(Device device, ArrayList<Object> list) {
		if(!validateMachineCode(device.getMachineCode())) {
			list.add(new Response(ResponseEnum.INVALID_MACHINE));
		}
		if(!validateSerialNumber(device.getSerialNumber())) {
			list.add(new Response(ResponseEnum.INVALID_SERIAL));
		}
	}
	
	private Device findBySerialNumberAndMachineCode (String serialNumber, String machineCode, ArrayList<Object> list) {
		
		Device device = repo.findDeviceByMachineCode(machineCode);
		
		// Checks if device is found by machine code in repository
		if(device == null) {
			list.add(new Response(ResponseEnum.MACHINE_NOT_FOUND));
			
			device = repo.findDeviceBySerialNumber(serialNumber);
			
			// Checks if serial number is found, so both errors can be displayed
			if(device == null) {
				list.add(new Response(ResponseEnum.SERIAL_NOT_FOUND));
			}
		} else if(!device.getSerialNumber().equals(serialNumber)) {
			list.add(new Response(ResponseEnum.SERIAL_NOT_FOUND));
		}
		
		return device;
	}
}
