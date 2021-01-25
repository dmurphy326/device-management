package com.dmurphy.repo;


public interface DeviceRepo extends CrudRepository<Device, Long>{
	Device findDeviceByMachineCode(String machineCode);
	Device findDeviceBySerialNumber(String serialNumber);
}
