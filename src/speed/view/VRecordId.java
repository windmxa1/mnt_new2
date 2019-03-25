package speed.view;

import java.sql.Timestamp;

/**
 * VRecordId entity. @author MyEclipse Persistence Tools
 */

public class VRecordId implements java.io.Serializable {

	// Fields

	private Integer no;
	private Integer sensorindex;
	private Double sensorvalue;
	private String name;
	private String location;
	private String message;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public VRecordId() {
	}

	/** minimal constructor */
	public VRecordId(Integer no, Integer sensorindex, Timestamp time) {
		this.no = no;
		this.sensorindex = sensorindex;
		this.time = time;
	}

	/** full constructor */
	public VRecordId(Integer no, Integer sensorindex, Double sensorvalue,
			String name, String location, String message, Timestamp time) {
		this.no = no;
		this.sensorindex = sensorindex;
		this.sensorvalue = sensorvalue;
		this.name = name;
		this.location = location;
		this.message = message;
		this.time = time;
	}

	// Property accessors

	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getSensorindex() {
		return this.sensorindex;
	}

	public void setSensorindex(Integer sensorindex) {
		this.sensorindex = sensorindex;
	}

	public Double getSensorvalue() {
		return this.sensorvalue;
	}

	public void setSensorvalue(Double sensorvalue) {
		this.sensorvalue = sensorvalue;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VRecordId))
			return false;
		VRecordId castOther = (VRecordId) other;

		return ((this.getNo() == castOther.getNo()) || (this.getNo() != null
				&& castOther.getNo() != null && this.getNo().equals(
				castOther.getNo())))
				&& ((this.getSensorindex() == castOther.getSensorindex()) || (this
						.getSensorindex() != null
						&& castOther.getSensorindex() != null && this
						.getSensorindex().equals(castOther.getSensorindex())))
				&& ((this.getSensorvalue() == castOther.getSensorvalue()) || (this
						.getSensorvalue() != null
						&& castOther.getSensorvalue() != null && this
						.getSensorvalue().equals(castOther.getSensorvalue())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getLocation() == castOther.getLocation()) || (this
						.getLocation() != null
						&& castOther.getLocation() != null && this
						.getLocation().equals(castOther.getLocation())))
				&& ((this.getMessage() == castOther.getMessage()) || (this
						.getMessage() != null && castOther.getMessage() != null && this
						.getMessage().equals(castOther.getMessage())))
				&& ((this.getTime() == castOther.getTime()) || (this.getTime() != null
						&& castOther.getTime() != null && this.getTime()
						.equals(castOther.getTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getNo() == null ? 0 : this.getNo().hashCode());
		result = 37
				* result
				+ (getSensorindex() == null ? 0 : this.getSensorindex()
						.hashCode());
		result = 37
				* result
				+ (getSensorvalue() == null ? 0 : this.getSensorvalue()
						.hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getLocation() == null ? 0 : this.getLocation().hashCode());
		result = 37 * result
				+ (getMessage() == null ? 0 : this.getMessage().hashCode());
		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		return result;
	}

}