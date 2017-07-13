package org.model;

/**
 * DoorCardreader entity. @author MyEclipse Persistence Tools
 */

public class DoorCardreader implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hostip;
	private Integer cardReader;
	private Integer flag;

	// Constructors

	/** default constructor */
	public DoorCardreader() {
	}

	/** full constructor */
	public DoorCardreader(String hostip, Integer cardReader, Integer flag) {
		this.hostip = hostip;
		this.cardReader = cardReader;
		this.flag = flag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHostip() {
		return this.hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public Integer getCardReader() {
		return this.cardReader;
	}

	public void setCardReader(Integer cardReader) {
		this.cardReader = cardReader;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}