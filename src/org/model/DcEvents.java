package org.model;

/**
 * DcEvents entity. @author MyEclipse Persistence Tools
 */

public class DcEvents implements java.io.Serializable {

	// Fields

	private Long id;
	private String host;
	private Integer doorid;
	private String clock;
	private String event;
	private Long cardid;
	private Integer cardReader;

	// Constructors

	/** default constructor */
	public DcEvents() {
	}

	/** minimal constructor */
	public DcEvents(String host, Integer doorid, String clock, String event) {
		this.host = host;
		this.doorid = doorid;
		this.clock = clock;
		this.event = event;
	}

	/** full constructor */
	public DcEvents(String host, Integer doorid, String clock, String event,
			Long cardid, Integer cardReader) {
		this.host = host;
		this.doorid = doorid;
		this.clock = clock;
		this.event = event;
		this.cardid = cardid;
		this.cardReader = cardReader;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getDoorid() {
		return this.doorid;
	}

	public void setDoorid(Integer doorid) {
		this.doorid = doorid;
	}

	public String getClock() {
		return this.clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Long getCardid() {
		return this.cardid;
	}

	public void setCardid(Long cardid) {
		this.cardid = cardid;
	}

	public Integer getCardReader() {
		return this.cardReader;
	}

	public void setCardReader(Integer cardReader) {
		this.cardReader = cardReader;
	}

}