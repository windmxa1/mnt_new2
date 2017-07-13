package org.view;

/**
 * VZfileId entity. @author MyEclipse Persistence Tools
 */

public class VZfileId implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String filename;
	private String time;
	private String dir;

	// Constructors

	/** default constructor */
	public VZfileId() {
	}

	/** minimal constructor */
	public VZfileId(Integer id, String username, String filename, String dir) {
		this.id = id;
		this.username = username;
		this.filename = filename;
		this.dir = dir;
	}

	/** full constructor */
	public VZfileId(Integer id, String username, String filename, String time,
			String dir) {
		this.id = id;
		this.username = username;
		this.filename = filename;
		this.time = time;
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

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDir() {
		return this.dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VZfileId))
			return false;
		VZfileId castOther = (VZfileId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& ((this.getFilename() == castOther.getFilename()) || (this
						.getFilename() != null
						&& castOther.getFilename() != null && this
						.getFilename().equals(castOther.getFilename())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())))
				&& ((this.getDir() == castOther.getDir()) || (this.getDir() != null
						&& castOther.getDir() != null && this.getDir().equals(
						castOther.getDir())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getFilename() == null ? 0 : this.getFilename().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getDir() == null ? 0 : this.getDir().hashCode());
		return result;
	}

}