package org.view;

/**
 * VeMessage entity. @author MyEclipse Persistence Tools
 */

public class VMessage implements java.io.Serializable {

	// Fields

	private VMessageId id;

	// Constructors

	/** default constructor */
	public VMessage() {
	}

	/** full constructor */
	public VMessage(VMessageId id) {
		this.id = id;
	}

	// Property accessors

	public VMessageId getId() {
		return this.id;
	}

	public void setId(VMessageId id) {
		this.id = id;
	}

}