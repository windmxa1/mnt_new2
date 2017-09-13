package org.model;

/**
 * ZHostConfig entity. @author MyEclipse Persistence Tools
 */

public class ZHostConfig implements java.io.Serializable {

	// Fields

	private Long id;
	private String host;
	private Integer available;
	private Integer notice;

	// Constructors

	/** default constructor */
	public ZHostConfig() {
	}

	/** minimal constructor */
	public ZHostConfig(String host) {
		this.host = host;
	}

	/** full constructor */
	public ZHostConfig(String host, Integer available, Integer notice) {
		this.host = host;
		this.available = available;
		this.notice = notice;
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

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getNotice() {
		return this.notice;
	}

	public void setNotice(Integer notice) {
		this.notice = notice;
	}

}