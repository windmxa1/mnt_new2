package speed.model;

/**
 * SensorConfig entity. @author MyEclipse Persistence Tools
 */

public class SensorConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sensorId;
	private Integer available;

	// Constructors

	/** default constructor */
	public SensorConfig() {
	}

	/** minimal constructor */
	public SensorConfig(Integer sensorId) {
		this.sensorId = sensorId;
	}

	/** full constructor */
	public SensorConfig(Integer sensorId, Integer available) {
		this.sensorId = sensorId;
		this.available = available;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSensorId() {
		return this.sensorId;
	}

	public void setSensorId(Integer sensorId) {
		this.sensorId = sensorId;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

}