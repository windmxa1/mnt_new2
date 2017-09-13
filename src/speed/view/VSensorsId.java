package speed.view;

/**
 * VSensorsId entity. @author MyEclipse Persistence Tools
 */

public class VSensorsId implements java.io.Serializable {

	// Fields

	private Integer no;
	private String ip;
	private Short type;
	private Short unit;
	private String displayname;
	private Double overresult;
	private Double lowerresult;
	private Double sensorvalue;
	private String location;
	private Integer status;
	private String message;
	private Integer available;
	private Double temp=0d;
	private Integer wet=0;
	private Integer state=0;
	private String runModel="";
	// Constructors

	/** default constructor */
	public VSensorsId() {
	}

	/** minimal constructor */
	public VSensorsId(Integer no, Short type) {
		this.no = no;
		this.type = type;
	}

	/** full constructor */
	public VSensorsId(Integer no, String ip, Short type, Short unit,
			String displayname, Double overresult, Double lowerresult,
			Double sensorvalue, String location, Integer status,
			String message, Integer available) {
		this.no = no;
		this.ip = ip;
		this.type = type;
		this.unit = unit;
		this.displayname = displayname;
		this.overresult = overresult;
		this.lowerresult = lowerresult;
		this.sensorvalue = sensorvalue;
		this.location = location;
		this.status = status;
		this.message = message;
		this.available = available;
	}

	// Property accessors

	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getUnit() {
		return this.unit;
	}

	public void setUnit(Short unit) {
		this.unit = unit;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public Double getOverresult() {
		return this.overresult;
	}

	public void setOverresult(Double overresult) {
		this.overresult = overresult;
	}

	public Double getLowerresult() {
		return this.lowerresult;
	}

	public void setLowerresult(Double lowerresult) {
		this.lowerresult = lowerresult;
	}

	public Double getSensorvalue() {
		return this.sensorvalue;
	}

	public void setSensorvalue(Double sensorvalue) {
		this.sensorvalue = sensorvalue;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Integer getWet() {
		return wet;
	}

	public void setWet(Integer wet) {
		this.wet = wet;
	}

	public Integer getState() {
		return state;
	}
	/**
	 * 设置空调运行状态，默认为0，代表关闭
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	public String getRunModel() {
		return runModel;
	}

	public void setRunModel(String runModel) {
		this.runModel = runModel;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VSensorsId))
			return false;
		VSensorsId castOther = (VSensorsId) other;

		return ((this.getNo() == castOther.getNo()) || (this.getNo() != null
				&& castOther.getNo() != null && this.getNo().equals(
				castOther.getNo())))
				&& ((this.getIp() == castOther.getIp()) || (this.getIp() != null
						&& castOther.getIp() != null && this.getIp().equals(
						castOther.getIp())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getUnit() == castOther.getUnit()) || (this.getUnit() != null
						&& castOther.getUnit() != null && this.getUnit()
						.equals(castOther.getUnit())))
				&& ((this.getDisplayname() == castOther.getDisplayname()) || (this
						.getDisplayname() != null
						&& castOther.getDisplayname() != null && this
						.getDisplayname().equals(castOther.getDisplayname())))
				&& ((this.getOverresult() == castOther.getOverresult()) || (this
						.getOverresult() != null
						&& castOther.getOverresult() != null && this
						.getOverresult().equals(castOther.getOverresult())))
				&& ((this.getLowerresult() == castOther.getLowerresult()) || (this
						.getLowerresult() != null
						&& castOther.getLowerresult() != null && this
						.getLowerresult().equals(castOther.getLowerresult())))
				&& ((this.getSensorvalue() == castOther.getSensorvalue()) || (this
						.getSensorvalue() != null
						&& castOther.getSensorvalue() != null && this
						.getSensorvalue().equals(castOther.getSensorvalue())))
				&& ((this.getLocation() == castOther.getLocation()) || (this
						.getLocation() != null
						&& castOther.getLocation() != null && this
						.getLocation().equals(castOther.getLocation())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getMessage() == castOther.getMessage()) || (this
						.getMessage() != null && castOther.getMessage() != null && this
						.getMessage().equals(castOther.getMessage())))
				&& ((this.getAvailable() == castOther.getAvailable()) || (this
						.getAvailable() != null
						&& castOther.getAvailable() != null && this
						.getAvailable().equals(castOther.getAvailable())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getNo() == null ? 0 : this.getNo().hashCode());
		result = 37 * result + (getIp() == null ? 0 : this.getIp().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getUnit() == null ? 0 : this.getUnit().hashCode());
		result = 37
				* result
				+ (getDisplayname() == null ? 0 : this.getDisplayname()
						.hashCode());
		result = 37
				* result
				+ (getOverresult() == null ? 0 : this.getOverresult()
						.hashCode());
		result = 37
				* result
				+ (getLowerresult() == null ? 0 : this.getLowerresult()
						.hashCode());
		result = 37
				* result
				+ (getSensorvalue() == null ? 0 : this.getSensorvalue()
						.hashCode());
		result = 37 * result
				+ (getLocation() == null ? 0 : this.getLocation().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getMessage() == null ? 0 : this.getMessage().hashCode());
		result = 37 * result
				+ (getAvailable() == null ? 0 : this.getAvailable().hashCode());
		return result;
	}

}