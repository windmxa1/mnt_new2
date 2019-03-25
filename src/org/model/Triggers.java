package org.model;

import java.io.Serializable;

public class Triggers implements Serializable {
	private Long triggerid;
	private String description;
	private Integer priority;
	private Integer value;

	public Triggers(){
		
	}
	
	public Triggers(Long triggerid, String description, Integer priority,
			Integer value) {
		super();
		this.triggerid = triggerid;
		this.description = description;
		this.priority = priority;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}



	public void setValue(Integer value) {
		this.value = value;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTriggerid() {
		return triggerid;
	}

	public void setTriggerid(Long triggerid) {
		this.triggerid = triggerid;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
