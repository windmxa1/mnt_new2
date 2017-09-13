package org.view;

/**
 * VDcEventsId entity. @author MyEclipse Persistence Tools
 */

public class VDcEventsId implements java.io.Serializable {

	// Fields

	private Long id;
	private String host;
	private Integer doorid;
	private String clock;
	private String event;
	private Long cardid;
	private Integer cardReader;
	private Integer isRead;
	private String groupname;
	private String cardUser;

	// Constructors

	/** default constructor */
	public VDcEventsId() {
	}

	/** minimal constructor */
	public VDcEventsId(Long id, String host, Integer doorid, String clock,
			String event) {
		this.id = id;
		this.host = host;
		this.doorid = doorid;
		this.clock = clock;
		this.event = event;
	}

	/** full constructor */
	public VDcEventsId(Long id, String host, Integer doorid, String clock,
			String event, Long cardid, Integer cardReader, Integer isRead,
			String groupname, String cardUser) {
		this.id = id;
		this.host = host;
		this.doorid = doorid;
		this.clock = clock;
		this.event = event;
		this.cardid = cardid;
		this.cardReader = cardReader;
		this.isRead = isRead;
		this.groupname = groupname;
		this.cardUser = cardUser;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getDoorid() {
		return this.doorid;
	}

	public void setDoorid(Integer doorid) {
		this.doorid = doorid;
	}

	public String getClock() {
		return this.clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Long getCardid() {
		return this.cardid;
	}

	public void setCardid(Long cardid) {
		this.cardid = cardid;
	}

	public Integer getCardReader() {
		return this.cardReader;
	}

	public void setCardReader(Integer cardReader) {
		this.cardReader = cardReader;
	}

	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getCardUser() {
		return this.cardUser;
	}

	public void setCardUser(String cardUser) {
		this.cardUser = cardUser;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VDcEventsId))
			return false;
		VDcEventsId castOther = (VDcEventsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getHost() == castOther.getHost()) || (this.getHost() != null
						&& castOther.getHost() != null && this.getHost()
						.equals(castOther.getHost())))
				&& ((this.getDoorid() == castOther.getDoorid()) || (this
						.getDoorid() != null && castOther.getDoorid() != null && this
						.getDoorid().equals(castOther.getDoorid())))
				&& ((this.getClock() == castOther.getClock()) || (this
						.getClock() != null && castOther.getClock() != null && this
						.getClock().equals(castOther.getClock())))
				&& ((this.getEvent() == castOther.getEvent()) || (this
						.getEvent() != null && castOther.getEvent() != null && this
						.getEvent().equals(castOther.getEvent())))
				&& ((this.getCardid() == castOther.getCardid()) || (this
						.getCardid() != null && castOther.getCardid() != null && this
						.getCardid().equals(castOther.getCardid())))
				&& ((this.getCardReader() == castOther.getCardReader()) || (this
						.getCardReader() != null
						&& castOther.getCardReader() != null && this
						.getCardReader().equals(castOther.getCardReader())))
				&& ((this.getIsRead() == castOther.getIsRead()) || (this
						.getIsRead() != null && castOther.getIsRead() != null && this
						.getIsRead().equals(castOther.getIsRead())))
				&& ((this.getGroupname() == castOther.getGroupname()) || (this
						.getGroupname() != null
						&& castOther.getGroupname() != null && this
						.getGroupname().equals(castOther.getGroupname())))
				&& ((this.getCardUser() == castOther.getCardUser()) || (this
						.getCardUser() != null
						&& castOther.getCardUser() != null && this
						.getCardUser().equals(castOther.getCardUser())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getHost() == null ? 0 : this.getHost().hashCode());
		result = 37 * result
				+ (getDoorid() == null ? 0 : this.getDoorid().hashCode());
		result = 37 * result
				+ (getClock() == null ? 0 : this.getClock().hashCode());
		result = 37 * result
				+ (getEvent() == null ? 0 : this.getEvent().hashCode());
		result = 37 * result
				+ (getCardid() == null ? 0 : this.getCardid().hashCode());
		result = 37
				* result
				+ (getCardReader() == null ? 0 : this.getCardReader()
						.hashCode());
		result = 37 * result
				+ (getIsRead() == null ? 0 : this.getIsRead().hashCode());
		result = 37 * result
				+ (getGroupname() == null ? 0 : this.getGroupname().hashCode());
		result = 37 * result
				+ (getCardUser() == null ? 0 : this.getCardUser().hashCode());
		return result;
	}

}