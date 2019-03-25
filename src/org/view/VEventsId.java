package org.view;

/**
 * VEventsId entity. @author MyEclipse Persistence Tools
 */

public class VEventsId implements java.io.Serializable {

	// Fields

	private Long eventid;
	private String vtime;
	private Integer clock;
	private String name;
	private String type;

	// Constructors

	/** default constructor */
	public VEventsId() {
	}

	/** minimal constructor */
	public VEventsId(Long eventid, Integer clock) {
		this.eventid = eventid;
		this.clock = clock;
	}

	/** full constructor */
	public VEventsId(Long eventid, String vtime, Integer clock, String name,
			String type) {
		this.eventid = eventid;
		this.vtime = vtime;
		this.clock = clock;
		this.name = name;
		this.type = type;
	}

	// Property accessors

	public Long getEventid() {
		return this.eventid;
	}

	public void setEventid(Long eventid) {
		this.eventid = eventid;
	}

	public String getVtime() {
		return this.vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VEventsId))
			return false;
		VEventsId castOther = (VEventsId) other;

		return ((this.getEventid() == castOther.getEventid()) || (this
				.getEventid() != null && castOther.getEventid() != null && this
				.getEventid().equals(castOther.getEventid())))
				&& ((this.getVtime() == castOther.getVtime()) || (this
						.getVtime() != null && castOther.getVtime() != null && this
						.getVtime().equals(castOther.getVtime())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getEventid() == null ? 0 : this.getEventid().hashCode());
		result = 37 * result
				+ (getVtime() == null ? 0 : this.getVtime().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		return result;
	}

}