package speed.model;

import java.sql.Timestamp;

/**
 * Record entity. @author MyEclipse Persistence Tools
 */

public class Record implements java.io.Serializable {

	// Fields

	private Integer no;
	private Integer sensorIndex;
	private Double sensorValue;
	private Double sensorOverValue;
	private Double sensorLowerValue;
	private Short sensorOverAction;
	private Short sensorLowerAction;
	private Short flag;
	private String message;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** minimal constructor */
	public Record(Integer sensorIndex, Timestamp time) {
		this.sensorIndex = sensorIndex;
		this.time = time;
	}

	/** full constructor */
	public Record(Integer sensorIndex, Double sensorValue,
			Double sensorOverValue, Double sensorLowerValue,
			Short sensorOverAction, Short sensorLowerAction, Short flag,
			String message, Timestamp time) {
		this.sensorIndex = sensorIndex;
		this.sensorValue = sensorValue;
		this.sensorOverValue = sensorOverValue;
		this.sensorLowerValue = sensorLowerValue;
		this.sensorOverAction = sensorOverAction;
		this.sensorLowerAction = sensorLowerAction;
		this.flag = flag;
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

	public Integer getSensorIndex() {
		return this.sensorIndex;
	}

	public void setSensorIndex(Integer sensorIndex) {
		this.sensorIndex = sensorIndex;
	}

	public Double getSensorValue() {
		return this.sensorValue;
	}

	public void setSensorValue(Double sensorValue) {
		this.sensorValue = sensorValue;
	}

	public Double getSensorOverValue() {
		return this.sensorOverValue;
	}

	public void setSensorOverValue(Double sensorOverValue) {
		this.sensorOverValue = sensorOverValue;
	}

	public Double getSensorLowerValue() {
		return this.sensorLowerValue;
	}

	public void setSensorLowerValue(Double sensorLowerValue) {
		this.sensorLowerValue = sensorLowerValue;
	}

	public Short getSensorOverAction() {
		return this.sensorOverAction;
	}

	public void setSensorOverAction(Short sensorOverAction) {
		this.sensorOverAction = sensorOverAction;
	}

	public Short getSensorLowerAction() {
		return this.sensorLowerAction;
	}

	public void setSensorLowerAction(Short sensorLowerAction) {
		this.sensorLowerAction = sensorLowerAction;
	}

	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
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

}