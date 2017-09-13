package org.view;

/**
 * VLogId entity. @author MyEclipse Persistence Tools
 */

public class VLogId implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String operation;
	private Long time;
	private String msg;
	private String vtime;
	private String name;

	// Constructors

	/** default constructor */
	public VLogId() {
	}

	/** minimal constructor */
	public VLogId(Long id, String username, String operation, Long time) {
		this.id = id;
		this.username = username;
		this.operation = operation;
		this.time = time;
	}

	/** full constructor */
	public VLogId(Long id, String username, String operation, Long time,
			String msg, String vtime, String name) {
		this.id = id;
		this.username = username;
		this.operation = operation;
		this.time = time;
		this.msg = msg;
		this.vtime = vtime;
		this.name = name;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Long getTime() {
		return this.time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getVtime() {
		return this.vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
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
		if (!(other instanceof VLogId))
			return false;
		VLogId castOther = (VLogId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& ((this.getOperation() == castOther.getOperation()) || (this
						.getOperation() != null
						&& castOther.getOperation() != null && this
						.getOperation().equals(castOther.getOperation())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getMsg() == castOther.getMsg()) || (this.getMsg() != null
						&& castOther.getMsg() != null && this.getMsg().equals(
						castOther.getMsg())))
				&& ((this.getVtime() == castOther.getVtime()) || (this
						.getVtime() != null && castOther.getVtime() != null && this
						.getVtime().equals(castOther.getVtime())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getOperation() == null ? 0 : this.getOperation().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getMsg() == null ? 0 : this.getMsg().hashCode());
		result = 37 * result
				+ (getVtime() == null ? 0 : this.getVtime().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		return result;
	}

}