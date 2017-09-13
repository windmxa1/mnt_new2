package org.model;

/**
 * ZArmingCase entity. @author MyEclipse Persistence Tools
 */

public class ZArmingCase implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hours;
	private String switches;
	private Integer chosen;

	// Constructors

	/** default constructor */
	public ZArmingCase() {
	}

	/** full constructor */
	public ZArmingCase(String hours, String switches, Integer chosen) {
		this.hours = hours;
		this.switches = switches;
		this.chosen = chosen;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHours() {
		return this.hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getSwitches() {
		return this.switches;
	}

	public void setSwitches(String switches) {
		this.switches = switches;
	}

	public Integer getChosen() {
		return this.chosen;
	}

	public void setChosen(Integer chosen) {
		this.chosen = chosen;
	}

}