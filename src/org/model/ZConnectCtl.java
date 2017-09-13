package org.model;

/**
 * ZConnectCtl entity. @author MyEclipse Persistence Tools
 */

public class ZConnectCtl implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer userid;
	private String threadId;
	private Integer type;

	// Constructors

	/** default constructor */
	public ZConnectCtl() {
	}

	/** full constructor */
	public ZConnectCtl(Integer userid, String threadId, Integer type) {
		this.userid = userid;
		this.threadId = threadId;
		this.type = type;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getThreadId() {
		return this.threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}