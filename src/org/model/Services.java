package org.model;

import java.io.Serializable;

public class Services implements Serializable {
	private Long serviceid;
	private String name;
	private Integer status;
	private Long triggerid;
	public Services(){
		
	}
	public Services(Long serviceid, String name, Integer status, Long triggerid) {
		super();
		this.serviceid = serviceid;
		this.name = name;
		this.status = status;
		this.triggerid = triggerid;
	}
	public Long getServiceid() {
		return serviceid;
	}
	public void setServiceid(Long serviceid) {
		this.serviceid = serviceid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getTriggerid() {
		return triggerid;
	}
	public void setTriggerid(Long triggerid) {
		this.triggerid = triggerid;
	}
	
}
