package org.model;

/**
 * ZUserRole entity. @author MyEclipse Persistence Tools
 */

public class ZUserRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer roleid;

	// Constructors

	/** default constructor */
	public ZUserRole() {
	}

	/** full constructor */
	public ZUserRole(Integer userid, Integer roleid) {
		this.userid = userid;
		this.roleid = roleid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}