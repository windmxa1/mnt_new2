package org.model;

/**
 * ZRoleAuthority entity. @author MyEclipse Persistence Tools
 */

public class ZRoleAuthority implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleid;
	private Integer authorityid;

	// Constructors

	/** default constructor */
	public ZRoleAuthority() {
	}

	/** full constructor */
	public ZRoleAuthority(Integer roleid, Integer authorityid) {
		this.roleid = roleid;
		this.authorityid = authorityid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getAuthorityid() {
		return this.authorityid;
	}

	public void setAuthorityid(Integer authorityid) {
		this.authorityid = authorityid;
	}

}