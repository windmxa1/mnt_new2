package org.model;

/**
 * ZSwitch entity. @author MyEclipse Persistence Tools
 */

public class ZSwitch implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Long groupid;
	private Integer available;

	// Constructors

	/** default constructor */
	public ZSwitch() {
	}

	/** full constructor */
	public ZSwitch(String name, Long groupid, Integer available) {
		this.name = name;
		this.groupid = groupid;
		this.available = available;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

}