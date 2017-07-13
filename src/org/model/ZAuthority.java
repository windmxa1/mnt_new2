package org.model;

/**
 * ZAuthority entity. @author MyEclipse Persistence Tools
 */

public class ZAuthority implements java.io.Serializable {

	// Fields

	private Integer id;
	private String alias;
	private String action;

	// Constructors

	/** default constructor */
	public ZAuthority() {
	}

	/** minimal constructor */
	public ZAuthority(String action) {
		this.action = action;
	}

	/** full constructor */
	public ZAuthority(String alias, String action) {
		this.alias = alias;
		this.action = action;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}