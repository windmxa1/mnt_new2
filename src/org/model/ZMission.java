package org.model;

/**
 * ZMission entity. @author MyEclipse Persistence Tools
 */

public class ZMission implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private Integer senderId;
	private Short isRead;
	private Short isComplete;
	private Short isConfirm;
	private Short isCancel;
	private String cancelReason; 
	private Integer receiverId;
	private Long time;

	// Constructors

	/** default constructor */
	public ZMission() {
	}

	public ZMission(String content, Integer senderId, Integer receiverId,
			Long time) {
		super();
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.time = time;
	}

	/** full constructor */
	public ZMission(String content, Integer senderId, Short isRead,
			Short isComplete, Short isConfirm, Short isCancel,
			String cancelReason, Integer receiverId, Long time) {
		super();
		this.content = content;
		this.senderId = senderId;
		this.isRead = isRead;
		this.isComplete = isComplete;
		this.isConfirm = isConfirm;
		this.isCancel = isCancel;
		this.cancelReason = cancelReason;
		this.receiverId = receiverId;
		this.time = time;
	}
	// Property accessors

	

	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Short getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Short isRead) {
		this.isRead = isRead;
	}

	public Short getIsComplete() {
		return this.isComplete;
	}

	public void setIsComplete(Short isComplete) {
		this.isComplete = isComplete;
	}

	public Short getIsConfirm() {
		return this.isConfirm;
	}

	public void setIsConfirm(Short isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Short getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Short isCancel) {
		this.isCancel = isCancel;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Integer getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

}