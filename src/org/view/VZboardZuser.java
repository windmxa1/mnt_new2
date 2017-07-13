package org.view;

/**
 * VZboardZuser entity. @author MyEclipse Persistence Tools
 */

public class VZboardZuser implements java.io.Serializable {

	// Fields

	private VZboardZuserId id;

	// Constructors

	/** default constructor */
	public VZboardZuser() {
	}

	/** full constructor */
	public VZboardZuser(VZboardZuserId id) {
		this.id = id;
	}

	// Property accessors

	public VZboardZuserId getId() {
		return this.id;
	}

	public void setId(VZboardZuserId id) {
		this.id = id;
	}

}