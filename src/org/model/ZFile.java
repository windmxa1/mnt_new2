package org.model;

/**
 * ZFile entity. @author MyEclipse Persistence Tools
 */

public class ZFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String filename;
	private Long uploadtime;
	private String dir;

	// Constructors

	/** default constructor */
	public ZFile() {
	}

	/** full constructor */
	public ZFile(String username, String filename, Long uploadtime, String dir) {
		this.username = username;
		this.filename = filename;
		this.uploadtime = uploadtime;
		this.dir = dir;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(Long uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getDir() {
		return this.dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}