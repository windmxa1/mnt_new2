package org.model;

import java.io.Serializable;

//1.设备类型			
//2.设备详细类型（子类型）	
//3.设备名称
//4.设备状态
//5.设备报警状态
//6.设备地址
//7.设备经度
//8.设备纬度
//9.设备负责人
public class HostInventory implements Serializable {
	private Long hostid;// 主机id
	private String name;// 主机名称
	private String deployment_status;
	private String contact;// 联系人
	private String location;// 主机地址
	private String locationLat;// 经度
	private String locationLon;// 纬度

	public HostInventory() {

	}

	public HostInventory(Long hostid, String name, String deployment_status,
			String contact, String location, String locationLat,
			String locationLon) {
		super();
		this.hostid = hostid;
		this.name = name;
		this.deployment_status = deployment_status;
		this.contact = contact;
		this.location = location;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}

	public String getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(String locationLat) {
		this.locationLat = locationLat;
	}

	public String getLocationLon() {
		return locationLon;
	}

	public void setLocationLon(String locationLon) {
		this.locationLon = locationLon;
	}

	public Long getHostid() {
		return hostid;
	}

	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeployment_status() {
		return deployment_status;
	}

	public void setDeployment_status(String deployment_status) {
		this.deployment_status = deployment_status;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
