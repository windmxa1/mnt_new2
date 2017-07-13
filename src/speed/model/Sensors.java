package speed.model;

/**
 * Sensors entity. @author MyEclipse Persistence Tools
 */

public class Sensors implements java.io.Serializable {

	// Fields

	private Integer no;
	private String ip;
	private Short id;
	private Short type;
	private Short prop;
	private Short unit;
	private String voiceFileName;
	private String displayName;
	private Integer checkTime;
	private Short alarmEnable;
	private Double overResult;
	private Double lowerResult;
	private Short overAction;
	private Short lowerAction;
	private Short hbjvoiceId;
	private Short lbjvoiceId;
	private Integer sensorTemp;
	private Integer index0;
	private Short modifyFlag;
	private Integer posX;
	private Integer posY;
	private Short meterType;

	// Constructors

	/** default constructor */
	public Sensors() {
	}

	/** minimal constructor */
	public Sensors(Short id, Short type, Short prop, Short modifyFlag) {
		this.id = id;
		this.type = type;
		this.prop = prop;
		this.modifyFlag = modifyFlag;
	}

	/** full constructor */
	public Sensors(String ip, Short id, Short type, Short prop, Short unit,
			String voiceFileName, String displayName, Integer checkTime,
			Short alarmEnable, Double overResult, Double lowerResult,
			Short overAction, Short lowerAction, Short hbjvoiceId,
			Short lbjvoiceId, Integer sensorTemp, Integer index0,
			Short modifyFlag, Integer posX, Integer posY, Short meterType) {
		this.ip = ip;
		this.id = id;
		this.type = type;
		this.prop = prop;
		this.unit = unit;
		this.voiceFileName = voiceFileName;
		this.displayName = displayName;
		this.checkTime = checkTime;
		this.alarmEnable = alarmEnable;
		this.overResult = overResult;
		this.lowerResult = lowerResult;
		this.overAction = overAction;
		this.lowerAction = lowerAction;
		this.hbjvoiceId = hbjvoiceId;
		this.lbjvoiceId = lbjvoiceId;
		this.sensorTemp = sensorTemp;
		this.index0 = index0;
		this.modifyFlag = modifyFlag;
		this.posX = posX;
		this.posY = posY;
		this.meterType = meterType;
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

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getProp() {
		return this.prop;
	}

	public void setProp(Short prop) {
		this.prop = prop;
	}

	public Short getUnit() {
		return this.unit;
	}

	public void setUnit(Short unit) {
		this.unit = unit;
	}

	public String getVoiceFileName() {
		return this.voiceFileName;
	}

	public void setVoiceFileName(String voiceFileName) {
		this.voiceFileName = voiceFileName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Integer checkTime) {
		this.checkTime = checkTime;
	}

	public Short getAlarmEnable() {
		return this.alarmEnable;
	}

	public void setAlarmEnable(Short alarmEnable) {
		this.alarmEnable = alarmEnable;
	}

	public Double getOverResult() {
		return this.overResult;
	}

	public void setOverResult(Double overResult) {
		this.overResult = overResult;
	}

	public Double getLowerResult() {
		return this.lowerResult;
	}

	public void setLowerResult(Double lowerResult) {
		this.lowerResult = lowerResult;
	}

	public Short getOverAction() {
		return this.overAction;
	}

	public void setOverAction(Short overAction) {
		this.overAction = overAction;
	}

	public Short getLowerAction() {
		return this.lowerAction;
	}

	public void setLowerAction(Short lowerAction) {
		this.lowerAction = lowerAction;
	}

	public Short getHbjvoiceId() {
		return this.hbjvoiceId;
	}

	public void setHbjvoiceId(Short hbjvoiceId) {
		this.hbjvoiceId = hbjvoiceId;
	}

	public Short getLbjvoiceId() {
		return this.lbjvoiceId;
	}

	public void setLbjvoiceId(Short lbjvoiceId) {
		this.lbjvoiceId = lbjvoiceId;
	}

	public Integer getSensorTemp() {
		return this.sensorTemp;
	}

	public void setSensorTemp(Integer sensorTemp) {
		this.sensorTemp = sensorTemp;
	}

	public Integer getIndex0() {
		return this.index0;
	}

	public void setIndex0(Integer index0) {
		this.index0 = index0;
	}

	public Short getModifyFlag() {
		return this.modifyFlag;
	}

	public void setModifyFlag(Short modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public Integer getPosX() {
		return this.posX;
	}

	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	public Integer getPosY() {
		return this.posY;
	}

	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public Short getMeterType() {
		return this.meterType;
	}

	public void setMeterType(Short meterType) {
		this.meterType = meterType;
	}

}