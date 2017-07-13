package org.view;

/**
 * VHostGroup entity. @author MyEclipse Persistence Tools
 */

public class VHostGroup implements java.io.Serializable {

	// Fields

	private VHostGroupId id;

	// Constructors

	/** default constructor */
	public VHostGroup() {
	}

	/** full constructor */
	public VHostGroup(VHostGroupId id) {
		this.id = id;
	}

	// Property accessors

	public VHostGroupId getId() {
		return this.id;
	}

	public void setId(VHostGroupId id) {
		this.id = id;
	}

}