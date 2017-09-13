package org.view;

/**
 * VSwitch entity. @author MyEclipse Persistence Tools
 */

public class VSwitch implements java.io.Serializable {

	// Fields

	private VSwitchId id;

	// Constructors

	/** default constructor */
	public VSwitch() {
	}

	/** full constructor */
	public VSwitch(VSwitchId id) {
		this.id = id;
	}

	// Property accessors

	public VSwitchId getId() {
		return this.id;
	}

	public void setId(VSwitchId id) {
		this.id = id;
	}

}