package org.view;

/**
 * VEvents entity. @author MyEclipse Persistence Tools
 */

public class VEvents implements java.io.Serializable {

	// Fields

	private VEventsId id;

	// Constructors

	/** default constructor */
	public VEvents() {
	}

	/** full constructor */
	public VEvents(VEventsId id) {
		this.id = id;
	}

	// Property accessors

	public VEventsId getId() {
		return this.id;
	}

	public void setId(VEventsId id) {
		this.id = id;
	}

}