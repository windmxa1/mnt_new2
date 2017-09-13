package org.view;

/**
 * VSwitchAlarm entity. @author MyEclipse Persistence Tools
 */

public class VSwitchAlarm implements java.io.Serializable {

	// Fields

	private VSwitchAlarmId id;

	// Constructors

	/** default constructor */
	public VSwitchAlarm() {
	}

	/** full constructor */
	public VSwitchAlarm(VSwitchAlarmId id) {
		this.id = id;
	}

	// Property accessors

	public VSwitchAlarmId getId() {
		return this.id;
	}

	public void setId(VSwitchAlarmId id) {
		this.id = id;
	}

}