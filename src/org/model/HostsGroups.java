package org.model;

/**
 * HostsGroups entity. @author MyEclipse Persistence Tools
 */

public class HostsGroups implements java.io.Serializable {

	// Fields

	private Long hostgroupid;
	private Long hostid;
	private Long groupid;

	// Constructors

	/** default constructor */
	public HostsGroups() {
	}

	// Property accessors
	public HostsGroups(Long hostgroupid, Long hostid, Long groupid) {
		super();
		this.hostgroupid = hostgroupid;
		this.hostid = hostid;
		this.groupid = groupid;
	}

	public Long getHostgroupid() {
		return hostgroupid;
	}

	public void setHostgroupid(Long hostgroupid) {
		this.hostgroupid = hostgroupid;
	}

	public Long getHostid() {
		return hostid;
	}

	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

}