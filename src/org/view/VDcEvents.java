package org.view;

/**
 * VDcEvents entity. @author MyEclipse Persistence Tools
 */

public class VDcEvents implements java.io.Serializable {

	// Fields

	private VDcEventsId id;

	// Constructors

	/** default constructor */
	public VDcEvents() {
	}

	/** full constructor */
	public VDcEvents(VDcEventsId id) {
		this.id = id;
	}

	// Property accessors

	public VDcEventsId getId() {
		return this.id;
	}

	public void setId(VDcEventsId id) {
		this.id = id;
	}

}