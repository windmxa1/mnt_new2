package org.view;

/**
 * VSwitchAlarmId entity. @author MyEclipse Persistence Tools
 */

public class VSwitchAlarmId implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer switchId;
	private String value;
	private Integer clock;
	private Integer ack;
	private String time;
	private String name;

	// Constructors

	/** default constructor */
	public VSwitchAlarmId() {
	}

	/** minimal constructor */
	public VSwitchAlarmId(Long id, Integer switchId, String value,
			Integer clock, Integer ack, String name) {
		this.id = id;
		this.switchId = switchId;
		this.value = value;
		this.clock = clock;
		this.ack = ack;
		this.name = name;
	}

	/** full constructor */
	public VSwitchAlarmId(Long id, Integer switchId, String value,
			Integer clock, Integer ack, String time, String name) {
		this.id = id;
		this.switchId = switchId;
		this.value = value;
		this.clock = clock;
		this.ack = ack;
		this.time = time;
		this.name = name;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSwitchId() {
		return this.switchId;
	}

	public void setSwitchId(Integer switchId) {
		this.switchId = switchId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public Integer getAck() {
		return this.ack;
	}

	public void setAck(Integer ack) {
		this.ack = ack;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
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
		if (!(other instanceof VSwitchAlarmId))
			return false;
		VSwitchAlarmId castOther = (VSwitchAlarmId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getSwitchId() == castOther.getSwitchId()) || (this
						.getSwitchId() != null
						&& castOther.getSwitchId() != null && this
						.getSwitchId().equals(castOther.getSwitchId())))
				&& ((this.getValue() == castOther.getValue()) || (this
						.getValue() != null && castOther.getValue() != null && this
						.getValue().equals(castOther.getValue())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getAck() == castOther.getAck()) || (this.getAck() != null
						&& castOther.getAck() != null && this.getAck().equals(
						castOther.getAck())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getSwitchId() == null ? 0 : this.getSwitchId().hashCode());
		result = 37 * result
				+ (getValue() == null ? 0 : this.getValue().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getAck() == null ? 0 : this.getAck().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		return result;
	}

}