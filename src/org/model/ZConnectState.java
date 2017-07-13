package org.model;

/**
 * ConnectState entity. @author MyEclipse Persistence Tools
 */

public class ZConnectState implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer count;
	private Integer senderId;
	private Integer receiverId;

	// Constructors

	/** default constructor */
	public ZConnectState() {
	}

	/** minimal constructor */
	public ZConnectState(Integer senderId, Integer receiverId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	/** full constructor */
	public ZConnectState(Integer count, Integer senderId, Integer receiverId) {
		this.count = count;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

}