package org.view;

/**
 * VHostGroupId entity. @author MyEclipse Persistence Tools
 */

public class VHostGroupId implements java.io.Serializable {

	// Fields

	private Long groupid;
	private Long hostid;
	private String host;
	private String name;

	// Constructors

	/** default constructor */
	public VHostGroupId() {
	}

	/** full constructor */
	public VHostGroupId(Long groupid, Long hostid, String host, String name) {
		this.groupid = groupid;
		this.hostid = hostid;
		this.host = host;
		this.name = name;
	}

	// Property accessors

	public Long getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public Long getHostid() {
		return this.hostid;
	}

	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VHostGroupId))
			return false;
		VHostGroupId castOther = (VHostGroupId) other;

		return ((this.getGroupid() == castOther.getGroupid()) || (this
				.getGroupid() != null && castOther.getGroupid() != null && this
				.getGroupid().equals(castOther.getGroupid())))
				&& ((this.getHostid() == castOther.getHostid()) || (this
						.getHostid() != null && castOther.getHostid() != null && this
						.getHostid().equals(castOther.getHostid())))
				&& ((this.getHost() == castOther.getHost()) || (this.getHost() != null
						&& castOther.getHost() != null && this.getHost()
						.equals(castOther.getHost())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupid() == null ? 0 : this.getGroupid().hashCode());
		result = 37 * result
				+ (getHostid() == null ? 0 : this.getHostid().hashCode());
		result = 37 * result
				+ (getHost() == null ? 0 : this.getHost().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		return result;
	}

}