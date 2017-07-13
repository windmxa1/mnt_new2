package org.view;

/**
 * VeMessageId entity. @author MyEclipse Persistence Tools
 */

public class VMessageId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String message;
	private Boolean sendState;
	private Boolean receiveState;
	private String time;
	private Integer senderId;
	private String senderName;
	private String receiverName;
	private Integer receiverId;

	// Constructors

	/** default constructor */
	public VMessageId() {
	}

	/** minimal constructor */
	public VMessageId(Integer id, String message, Integer senderId,
			Integer receiverId) {
		this.id = id;
		this.message = message;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

	/** full constructor */
	public VMessageId(Integer id, String message, Boolean sendState,
			Boolean receiveState, String time, Integer senderId,
			String senderName, String receiverName, Integer receiverId) {
		this.id = id;
		this.message = message;
		this.sendState = sendState;
		this.receiveState = receiveState;
		this.time = time;
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverName = receiverName;
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

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getSenderId() {
		return this.senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return this.senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VMessageId))
			return false;
		VMessageId castOther = (VMessageId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getMessage() == castOther.getMessage()) || (this
						.getMessage() != null && castOther.getMessage() != null && this
						.getMessage().equals(castOther.getMessage())))
				&& ((this.getSendState() == castOther.getSendState()) || (this
						.getSendState() != null
						&& castOther.getSendState() != null && this
						.getSendState().equals(castOther.getSendState())))
				&& ((this.getReceiveState() == castOther.getReceiveState()) || (this
						.getReceiveState() != null
						&& castOther.getReceiveState() != null && this
						.getReceiveState().equals(castOther.getReceiveState())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getSenderId() == castOther.getSenderId()) || (this
						.getSenderId() != null
						&& castOther.getSenderId() != null && this
						.getSenderId().equals(castOther.getSenderId())))
				&& ((this.getSenderName() == castOther.getSenderName()) || (this
						.getSenderName() != null
						&& castOther.getSenderName() != null && this
						.getSenderName().equals(castOther.getSenderName())))
				&& ((this.getReceiverName() == castOther.getReceiverName()) || (this
						.getReceiverName() != null
						&& castOther.getReceiverName() != null && this
						.getReceiverName().equals(castOther.getReceiverName())))
				&& ((this.getReceiverId() == castOther.getReceiverId()) || (this
						.getReceiverId() != null
						&& castOther.getReceiverId() != null && this
						.getReceiverId().equals(castOther.getReceiverId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getMessage() == null ? 0 : this.getMessage().hashCode());
		result = 37 * result
				+ (getSendState() == null ? 0 : this.getSendState().hashCode());
		result = 37
				* result
				+ (getReceiveState() == null ? 0 : this.getReceiveState()
						.hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getSenderId() == null ? 0 : this.getSenderId().hashCode());
		result = 37
				* result
				+ (getSenderName() == null ? 0 : this.getSenderName()
						.hashCode());
		result = 37
				* result
				+ (getReceiverName() == null ? 0 : this.getReceiverName()
						.hashCode());
		result = 37
				* result
				+ (getReceiverId() == null ? 0 : this.getReceiverId()
						.hashCode());
		return result;
	}

}