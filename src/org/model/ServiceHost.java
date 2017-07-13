package org.model;

/**
 * ServiceHost entity. @author MyEclipse Persistence Tools
 */

public class ServiceHost implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long serviceid;
	private Long hostid;

	// Constructors

	/** default constructor */
	public ServiceHost() {
	}

	/** full constructor */
	public ServiceHost(Long serviceid, Long hostid) {
		this.serviceid = serviceid;
		this.hostid = hostid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(Long serviceid) {
		this.serviceid = serviceid;
	}

	public Long getHostid() {
		return this.hostid;
	}

	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}

}