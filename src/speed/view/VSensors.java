package speed.view;

/**
 * VSensors entity. @author MyEclipse Persistence Tools
 */

public class VSensors implements java.io.Serializable {

	// Fields

	private VSensorsId id;

	// Constructors

	/** default constructor */
	public VSensors() {
	}

	/** full constructor */
	public VSensors(VSensorsId id) {
		this.id = id;
	}

	// Property accessors

	public VSensorsId getId() {
		return this.id;
	}

	public void setId(VSensorsId id) {
		this.id = id;
	}

}