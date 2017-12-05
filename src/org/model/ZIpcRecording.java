package org.model;

/**
 * ZIpcRecording entity. @author MyEclipse Persistence Tools
 */

public class ZIpcRecording implements java.io.Serializable {

	// Fields

	private Long id;
	private String host;
	private Integer isRecording;

	// Constructors

	/** default constructor */
	public ZIpcRecording() {
	}

	/** minimal constructor */
	public ZIpcRecording(String host) {
		this.host = host;
	}

	/** full constructor */
	public ZIpcRecording(String host, Integer isRecording) {
		this.host = host;
		this.isRecording = isRecording;
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

	public Integer getIsRecording() {
		return this.isRecording;
	}

	public void setIsRecording(Integer isRecording) {
		this.isRecording = isRecording;
	}

}