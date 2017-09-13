package org.model;

/**
 * ZOperationTime entity. @author MyEclipse Persistence Tools
 */

public class ZOperationTime implements java.io.Serializable {

	// Fields

	private Integer userid;
	private Long lastOperationTime;

	// Constructors

	/** default constructor */
	public ZOperationTime() {
	}

	/** full constructor */
	public ZOperationTime(Integer userid, Long lastOperationTime) {
		this.userid = userid;
		this.lastOperationTime = lastOperationTime;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Long getLastOperationTime() {
		return this.lastOperationTime;
	}

	public void setLastOperationTime(Long lastOperationTime) {
		this.lastOperationTime = lastOperationTime;
	}

}