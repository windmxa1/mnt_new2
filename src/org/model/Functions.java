package org.model;

import java.io.Serializable;

public class Functions implements Serializable{
	private Long triggerid;
	private Long itemid;
	private Long functionid;
	
	public Functions(){
		
	}
	
	public Functions(Long triggerid, Long itemid, Long functionid) {
		super();
		this.triggerid = triggerid;
		this.itemid = itemid;
		this.functionid = functionid;
	}
	public Long getTriggerid() {
		return triggerid;
	}
	public void setTriggerid(Long triggerid) {
		this.triggerid = triggerid;
	}
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public Long getFunctionid() {
		return functionid;
	}
	public void setFunctionid(Long functionid) {
		this.functionid = functionid;
	}
}
