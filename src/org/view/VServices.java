package org.view;

/**
 * VServices entity. @author MyEclipse Persistence Tools
 */

public class VServices implements java.io.Serializable {

	// Fields

	private VServicesId id;

	// Constructors

	/** default constructor */
	public VServices() {
	}

	/** full constructor */
	public VServices(VServicesId id) {
		this.id = id;
	}

	// Property accessors

	public VServicesId getId() {
		return this.id;
	}

	public void setId(VServicesId id) {
		this.id = id;
	}

}