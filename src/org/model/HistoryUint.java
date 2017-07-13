package org.model;

/**
 * HistoryUint entity. @author MyEclipse Persistence Tools
 */

public class HistoryUint implements java.io.Serializable {

	// Fields

	private HistoryUintId id;

	// Constructors

	/** default constructor */
	public HistoryUint() {
	}

	/** full constructor */
	public HistoryUint(HistoryUintId id) {
		this.id = id;
	}

	// Property accessors

	public HistoryUintId getId() {
		return this.id;
	}

	public void setId(HistoryUintId id) {
		this.id = id;
	}

}