package org.model;

/**
 * HistoryId entity. @author MyEclipse Persistence Tools
 */

public class HistoryId implements java.io.Serializable {

	// Fields

	private Long itemid;
	private Integer clock;
	private Double value;
	private Integer ns;

	// Constructors

	/** default constructor */
	public HistoryId() {
	}

	/** full constructor */
	public HistoryId(Long itemid, Integer clock, Double value, Integer ns) {
		this.itemid = itemid;
		this.clock = clock;
		this.value = value;
		this.ns = ns;
	}

	// Property accessors

	public Long getItemid() {
		return this.itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getNs() {
		return this.ns;
	}

	public void setNs(Integer ns) {
		this.ns = ns;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HistoryId))
			return false;
		HistoryId castOther = (HistoryId) other;

		return ((this.getItemid() == castOther.getItemid()) || (this
				.getItemid() != null && castOther.getItemid() != null && this
				.getItemid().equals(castOther.getItemid())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getValue() == castOther.getValue()) || (this
						.getValue() != null && castOther.getValue() != null && this
						.getValue().equals(castOther.getValue())))
				&& ((this.getNs() == castOther.getNs()) || (this.getNs() != null
						&& castOther.getNs() != null && this.getNs().equals(
						castOther.getNs())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getItemid() == null ? 0 : this.getItemid().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getValue() == null ? 0 : this.getValue().hashCode());
		result = 37 * result + (getNs() == null ? 0 : this.getNs().hashCode());
		return result;
	}

}