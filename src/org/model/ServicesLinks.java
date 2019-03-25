package org.model;

import java.io.Serializable;

public class ServicesLinks implements Serializable{
	private Long linkid;
	private Long serviceupid;
	private Long servicedownid;
	
	public ServicesLinks(){
		
	}
	public ServicesLinks(Long serviceupid, Long servicedownid) {
		super();
		this.serviceupid = serviceupid;
		this.servicedownid = servicedownid;
	}
	public Long getLinkid() {
		return linkid;
	}
	public void setLinkid(Long linkid) {
		this.linkid = linkid;
	}
	public Long getServiceupid() {
		return serviceupid;
	}
	public void setServiceupid(Long serviceupid) {
		this.serviceupid = serviceupid;
	}
	public Long getServicedownid() {
		return servicedownid;
	}
	public void setServicedownid(Long servicedownid) {
		this.servicedownid = servicedownid;
	}
	
}
