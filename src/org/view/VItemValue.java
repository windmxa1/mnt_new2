package org.view;

/**
 * VItemValue entity. @author MyEclipse Persistence Tools
 */

public class VItemValue implements java.io.Serializable {

	// Fields

	private VItemValueId id;

	// Constructors

	/** default constructor */
	public VItemValue() {
	}

	/** full constructor */
	public VItemValue(VItemValueId id) {
		this.id = id;
	}

	// Property accessors

	public VItemValueId getId() {
		return this.id;
	}

	public void setId(VItemValueId id) {
		this.id = id;
	}

}