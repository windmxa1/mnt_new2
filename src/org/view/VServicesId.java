package org.view;

/**
 * VServicesId entity. @author MyEclipse Persistence Tools
 */

public class VServicesId implements java.io.Serializable {

	// Fields

	private String name;
	private Integer status;
	private String lat;
	private String lon;

	// Constructors

	/** default constructor */
	public VServicesId() {
	}

	/** full constructor */
	public VServicesId(String name, Integer status, String lat, String lon) {
		this.name = name;
		this.status = status;
		this.lat = lat;
		this.lon = lon;
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VServicesId))
			return false;
		VServicesId castOther = (VServicesId) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getLat() == castOther.getLat()) || (this.getLat() != null
						&& castOther.getLat() != null && this.getLat().equals(
						castOther.getLat())))
				&& ((this.getLon() == castOther.getLon()) || (this.getLon() != null
						&& castOther.getLon() != null && this.getLon().equals(
						castOther.getLon())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getLat() == null ? 0 : this.getLat().hashCode());
		result = 37 * result
				+ (getLon() == null ? 0 : this.getLon().hashCode());
		return result;
	}

}