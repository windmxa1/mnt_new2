package org.model;

/**
 * HistoryText entity. @author MyEclipse Persistence Tools
 */

public class HistoryText implements java.io.Serializable {

	// Fields

	private Long id;
	private Long itemid;
	private Integer clock;
	private String value;
	private Integer ns;

	// Constructors

	/** default constructor */
	public HistoryText() {
	}

	/** full constructor */
	public HistoryText(Long itemid, Integer clock, String value, Integer ns) {
		this.itemid = itemid;
		this.clock = clock;
		this.value = value;
		this.ns = ns;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemid() {
		return this.itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getNs() {
		return this.ns;
	}

	public void setNs(Integer ns) {
		this.ns = ns;
	}

}