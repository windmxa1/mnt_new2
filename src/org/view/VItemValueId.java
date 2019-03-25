package org.view;

/**
 * VItemValueId entity. @author MyEclipse Persistence Tools
 */

public class VItemValueId implements java.io.Serializable {

	// Fields

	private Long hostid;
	private String groupname;
	private String host;
	private Integer isRecording;
	private String name;
	private String value;
	private String clock;
	private String type;
	private Integer notice;
	private String nvrIp;
	private String deviceName;

	// Constructors

	/** default constructor */
	public VItemValueId() {
	}

	/** minimal constructor */
	public VItemValueId(Long hostid, String groupname, String host, String type) {
		this.hostid = hostid;
		this.groupname = groupname;
		this.host = host;
		this.type = type;
	}

	/** full constructor */
	public VItemValueId(Long hostid, String groupname, String host,
			Integer isRecording, String name, String value, String clock,
			String type, Integer notice, String nvrIp, String deviceName) {
		this.hostid = hostid;
		this.groupname = groupname;
		this.host = host;
		this.isRecording = isRecording;
		this.name = name;
		this.value = value;
		this.clock = clock;
		this.type = type;
		this.notice = notice;
		this.nvrIp = nvrIp;
		this.deviceName = deviceName;
	}

	// Property accessors

	public Long getHostid() {
		return this.hostid;
	}

	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getIsRecording() {
		return this.isRecording;
	}

	public void setIsRecording(Integer isRecording) {
		this.isRecording = isRecording;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClock() {
		return this.clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNotice() {
		return this.notice;
	}

	public void setNotice(Integer notice) {
		this.notice = notice;
	}

	public String getNvrIp() {
		return this.nvrIp;
	}

	public void setNvrIp(String nvrIp) {
		this.nvrIp = nvrIp;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VItemValueId))
			return false;
		VItemValueId castOther = (VItemValueId) other;

		return ((this.getHostid() == castOther.getHostid()) || (this
				.getHostid() != null && castOther.getHostid() != null && this
				.getHostid().equals(castOther.getHostid())))
				&& ((this.getGroupname() == castOther.getGroupname()) || (this
						.getGroupname() != null
						&& castOther.getGroupname() != null && this
						.getGroupname().equals(castOther.getGroupname())))
				&& ((this.getHost() == castOther.getHost()) || (this.getHost() != null
						&& castOther.getHost() != null && this.getHost()
						.equals(castOther.getHost())))
				&& ((this.getIsRecording() == castOther.getIsRecording()) || (this
						.getIsRecording() != null
						&& castOther.getIsRecording() != null && this
						.getIsRecording().equals(castOther.getIsRecording())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getValue() == castOther.getValue()) || (this
						.getValue() != null && castOther.getValue() != null && this
						.getValue().equals(castOther.getValue())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getNotice() == castOther.getNotice()) || (this
						.getNotice() != null && castOther.getNotice() != null && this
						.getNotice().equals(castOther.getNotice())))
				&& ((this.getNvrIp() == castOther.getNvrIp()) || (this
						.getNvrIp() != null && castOther.getNvrIp() != null && this
						.getNvrIp().equals(castOther.getNvrIp())))
				&& ((this.getDeviceName() == castOther.getDeviceName()) || (this
						.getDeviceName() != null
						&& castOther.getDeviceName() != null && this
						.getDeviceName().equals(castOther.getDeviceName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getHostid() == null ? 0 : this.getHostid().hashCode());
		result = 37 * result
				+ (getGroupname() == null ? 0 : this.getGroupname().hashCode());
		result = 37 * result
				+ (getHost() == null ? 0 : this.getHost().hashCode());
		result = 37
				* result
				+ (getIsRecording() == null ? 0 : this.getIsRecording()
						.hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getValue() == null ? 0 : this.getValue().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result
				+ (getNotice() == null ? 0 : this.getNotice().hashCode());
		result = 37 * result
				+ (getNvrIp() == null ? 0 : this.getNvrIp().hashCode());
		result = 37
				* result
				+ (getDeviceName() == null ? 0 : this.getDeviceName()
						.hashCode());
		return result;
	}

}