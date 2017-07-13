package org.model;

import java.io.Serializable;

public class Events implements Serializable{
	private Long eventid;
	private Long objectid;
	private Integer source;
	private Integer acknowledged ;
	private Integer value;
	private Integer clock;
	
	public Events(){
		
	}
	public Events(Long eventid, Long objectid, Integer source,
			Integer acknowledged, Integer value, Integer clock) {
		super();
		this.eventid = eventid;
		this.objectid = objectid;
		this.source = source;
		this.acknowledged = acknowledged;
		this.value = value;
		this.clock = clock;
	}
	public Integer getClock() {
		return clock;
	}
	public void setClock(Integer clock) {
		this.clock = clock;
	}
	public Long getEventid() {
		return eventid;
	}
	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}
	public Long getObjectid() {
		return objectid;
	}
	public void setObjectid(Long objectid) {
		this.objectid = objectid;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getAcknowledged() {
		return acknowledged;
	}
	public void setAcknowledged(Integer acknowledged) {
		this.acknowledged = acknowledged;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
