package org.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Hosts entity. @author MyEclipse Persistence Tools
 */

public class Hosts implements java.io.Serializable {

	// Fields

	private Long hostid;
	private String host;
	private Integer status;
	private String name;
	private Integer flags;
	private Integer available;
	
	// Constructors

	/** default constructor */
	public Hosts() {
	}
	
	public Hosts(Long hostid, String host, Integer status, String name,
			Integer flags, Integer available) {
		super();
		this.hostid = hostid;
		this.host = host;
		this.status = status;
		this.name = name;
		this.flags = flags;
		this.available = available;
	}

//	public Hosts(Long hostid, String host, Integer status, String name,Integer flags) {
//		super();
//		this.hostid = hostid;
//		this.host = host;
//		this.status = status;
//		this.name = name;
//		this.flags = flags;
//	}
	
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integer getFlags() {
		return flags;
	}
	public void setFlags(Integer flags) {
		this.flags = flags;
	}
	public Long getHostid() {
		return hostid;
	}
	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}