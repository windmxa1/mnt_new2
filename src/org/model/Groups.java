package org.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Groups entity. @author MyEclipse Persistence Tools
 */

public class Groups implements java.io.Serializable {

	// Fields

	private Long groupid;
	private String name;
	private Integer internal;
	private Integer flags;

	// Constructors

	/** default constructor */
	public Groups() {
	}


	public Groups(Long groupid, String name, Integer internal, Integer flags) {
		super();
		this.groupid = groupid;
		this.name = name;
		this.internal = internal;
		this.flags = flags;
	}

	public Long getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInternal() {
		return this.internal;
	}

	public void setInternal(Integer internal) {
		this.internal = internal;
	}

	public Integer getFlags() {
		return this.flags;
	}

	public void setFlags(Integer flags) {
		this.flags = flags;
	}

}