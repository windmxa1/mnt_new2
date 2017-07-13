package org.model;

/**
 * JfHost entity. @author MyEclipse Persistence Tools
 */

public class JfHost implements java.io.Serializable {

	// Fields

	private Long id;
	private String host;
	private Long jfSerivce;

	// Constructors

	/** default constructor */
	public JfHost() {
	}

	/** full constructor */
	public JfHost(String host, Long jfSerivce) {
		this.host = host;
		this.jfSerivce = jfSerivce;
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

	public Long getJfSerivce() {
		return this.jfSerivce;
	}

	public void setJfSerivce(Long jfSerivce) {
		this.jfSerivce = jfSerivce;
	}

}