package org.model;

/**
 * ZSwitchAlarm entity. @author MyEclipse Persistence Tools
 */

public class ZSwitchAlarm implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer switchId;
	private String value;
	private Integer clock;
	private Integer ack;

	// Constructors

	/** default constructor */
	public ZSwitchAlarm() {
	}

	/** full constructor */
	public ZSwitchAlarm(Integer switchId, String value, Integer clock,
			Integer ack) {
		this.switchId = switchId;
		this.value = value;
		this.clock = clock;
		this.ack = ack;
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

}