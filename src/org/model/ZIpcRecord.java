package org.model;

/**
 * ZIpcRecord entity. @author MyEclipse Persistence Tools
 */

public class ZIpcRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private String localPath;

	// Constructors

	/** default constructor */
	public ZIpcRecord() {
	}

	/** full constructor */
	public ZIpcRecord(String localPath) {
		this.localPath = localPath;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}