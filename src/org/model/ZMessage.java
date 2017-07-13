package org.model;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class ZMessage implements java.io.Serializable {

	// Fields

	private Integer id;
	private String message;
	private Long time;
	private Boolean sendState;
	private Boolean receiveState;
	private Integer senderId;
	private Integer receiverId;

	// Constructors

	/** default constructor */
	public ZMessage() {
	}

	/** minimal constructor */
	public ZMessage(String message, Long time, Integer senderId,
			Integer receiverId) {
		this.message = message;
		this.time = time;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	/** full constructor */
	public ZMessage(String message, Long time, Boolean sendState,
			Boolean receiveState, Integer senderId, Integer receiverId) {
		this.message = message;
		this.time = time;
		this.sendState = sendState;
		this.receiveState = receiveState;
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Boolean getSendState() {
		return this.sendState;
	}

	public void setSendState(Boolean sendState) {
		this.sendState = sendState;
	}

	public Boolean getReceiveState() {
		return this.receiveState;
	}

	public void setReceiveState(Boolean receiveState) {
		this.receiveState = receiveState;
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