package org.view;

/**
 * VZfile entity. @author MyEclipse Persistence Tools
 */

public class VZfile implements java.io.Serializable {

	// Fields

	private VZfileId id;

	// Constructors

	/** default constructor */
	public VZfile() {
	}

	/** full constructor */
	public VZfile(VZfileId id) {
		this.id = id;
	}

	// Property accessors

	public VZfileId getId() {
		return this.id;
	}

	public void setId(VZfileId id) {
		this.id = id;
	}

}