package org.model;

/**
 * History entity. @author MyEclipse Persistence Tools
 */

public class History implements java.io.Serializable {

	// Fields

	private HistoryId id;

	// Constructors

	/** default constructor */
	public History() {
	}

	/** full constructor */
	public History(HistoryId id) {
		this.id = id;
	}

	// Property accessors

	public HistoryId getId() {
		return this.id;
	}

	public void setId(HistoryId id) {
		this.id = id;
	}

}