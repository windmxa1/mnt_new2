package speed.view;

/**
 * VRecord entity. @author MyEclipse Persistence Tools
 */

public class VRecord implements java.io.Serializable {

	// Fields

	private VRecordId id;

	// Constructors

	/** default constructor */
	public VRecord() {
	}

	/** full constructor */
	public VRecord(VRecordId id) {
		this.id = id;
	}

	// Property accessors

	public VRecordId getId() {
		return this.id;
	}

	public void setId(VRecordId id) {
		this.id = id;
	}

}