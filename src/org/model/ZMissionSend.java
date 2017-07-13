package org.model;

/**
 * ZMissionSend entity. @author MyEclipse Persistence Tools
 */

public class ZMissionSend implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer senderId;
	private Integer receiverId;
	private Integer missionId;

	// Constructors

	/** default constructor */
	public ZMissionSend() {
	}

	/** full constructor */
	public ZMissionSend(Integer senderId, Integer receiverId, Integer missionId) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.missionId = missionId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getMissionId() {
		return this.missionId;
	}

	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
	}

}