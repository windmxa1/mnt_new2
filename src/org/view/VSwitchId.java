package org.view;

/**
 * VSwitchId entity. @author MyEclipse Persistence Tools
 */

public class VSwitchId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Long groupid;
	private Integer available;
	private String value;
	private Integer ack;
	private Integer clock;
	private String time;
	private String groupname;

	// Constructors

	/** default constructor */
	public VSwitchId() {
	}

	/** minimal constructor */
	public VSwitchId(Integer id, String name, Long groupid, Integer available,
			String value, Integer ack, Integer clock, String groupname) {
		this.id = id;
		this.name = name;
		this.groupid = groupid;
		this.available = available;
		this.value = value;
		this.ack = ack;
		this.clock = clock;
		this.groupname = groupname;
	}

	/** full constructor */
	public VSwitchId(Integer id, String name, Long groupid, Integer available,
			String value, Integer ack, Integer clock, String time,
			String groupname) {
		this.id = id;
		this.name = name;
		this.groupid = groupid;
		this.available = available;
		this.value = value;
		this.ack = ack;
		this.clock = clock;
		this.time = time;
		this.groupname = groupname;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getAck() {
		return this.ack;
	}

	public void setAck(Integer ack) {
		this.ack = ack;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VSwitchId))
			return false;
		VSwitchId castOther = (VSwitchId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getGroupid() == castOther.getGroupid()) || (this
						.getGroupid() != null && castOther.getGroupid() != null && this
						.getGroupid().equals(castOther.getGroupid())))
				&& ((this.getAvailable() == castOther.getAvailable()) || (this
						.getAvailable() != null
						&& castOther.getAvailable() != null && this
						.getAvailable().equals(castOther.getAvailable())))
				&& ((this.getValue() == castOther.getValue()) || (this
						.getValue() != null && castOther.getValue() != null && this
						.getValue().equals(castOther.getValue())))
				&& ((this.getAck() == castOther.getAck()) || (this.getAck() != null
						&& castOther.getAck() != null && this.getAck().equals(
						castOther.getAck())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getGroupname() == castOther.getGroupname()) || (this
						.getGroupname() != null
						&& castOther.getGroupname() != null && this
						.getGroupname().equals(castOther.getGroupname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getGroupid() == null ? 0 : this.getGroupid().hashCode());
		result = 37 * result
				+ (getAvailable() == null ? 0 : this.getAvailable().hashCode());
		result = 37 * result
				+ (getValue() == null ? 0 : this.getValue().hashCode());
		result = 37 * result
				+ (getAck() == null ? 0 : this.getAck().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getGroupname() == null ? 0 : this.getGroupname().hashCode());
		return result;
	}

}