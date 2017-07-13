package org.model;

/**
 * AlarmTab entity. @author MyEclipse Persistence Tools
 */

public class AlarmTab implements java.io.Serializable {

	// Fields

	private Long id;
	private String deviceType;
	private String alarmType;
	private String clock;
	private Long hostid;
	private String host;
	private Integer confirm;
	private Integer time;

	// Constructors

	/** default constructor */
	public AlarmTab() {
	}

	/** full constructor */
	public AlarmTab(String deviceType, String alarmType, String clock,
			Long hostid, String host, Integer confirm, Integer time) {
		this.deviceType = deviceType;
		this.alarmType = alarmType;
		this.clock = clock;
		this.hostid = hostid;
		this.host = host;
		this.confirm = confirm;
		this.time = time;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getClock() {
		return this.clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public Long getHostid() {
		return this.hostid;
	}

	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getConfirm() {
		return this.confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

}