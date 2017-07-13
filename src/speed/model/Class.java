package speed.model;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */

public class Class implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer parent;
	private String detail;
	private Integer sensorNo;

	// Constructors

	/** default constructor */
	public Class() {
	}

	/** minimal constructor */
	public Class(Integer parent, Integer sensorNo) {
		this.parent = parent;
		this.sensorNo = sensorNo;
	}

	/** full constructor */
	public Class(String name, Integer parent, String detail, Integer sensorNo) {
		this.name = name;
		this.parent = parent;
		this.detail = detail;
		this.sensorNo = sensorNo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent() {
		return this.parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getSensorNo() {
		return this.sensorNo;
	}

	public void setSensorNo(Integer sensorNo) {
		this.sensorNo = sensorNo;
	}

}