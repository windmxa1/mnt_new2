package org.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Events entity. @author MyEclipse Persistence Tools
 */

public class Events implements java.io.Serializable {

	// Fields

	private Long eventid;
	private Integer source;
	private Integer object;
	private Long objectid;
	private Integer clock;
	private Integer value;
	private Integer acknowledged;
	private Integer ns;

	// Constructors

	/** default constructor */
	public Events() {
	}

	/** minimal constructor */
	public Events(Long eventid, Integer source, Integer object, Long objectid,
			Integer clock, Integer value, Integer acknowledged, Integer ns) {
		this.eventid = eventid;
		this.source = source;
		this.object = object;
		this.objectid = objectid;
		this.clock = clock;
		this.value = value;
		this.acknowledged = acknowledged;
		this.ns = ns;
	}

	// Property accessors

	public Long getEventid() {
		return this.eventid;
	}

	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getObject() {
		return this.object;
	}

	public void setObject(Integer object) {
		this.object = object;
	}

	public Long getObjectid() {
		return this.objectid;
	}

	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getAcknowledged() {
		return this.acknowledged;
	}

	public void setAcknowledged(Integer acknowledged) {
		this.acknowledged = acknowledged;
	}

	public Integer getNs() {
		return this.ns;
	}

	public void setNs(Integer ns) {
		this.ns = ns;
	}


}