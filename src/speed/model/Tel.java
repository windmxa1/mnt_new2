package speed.model;

/**
 * Tel entity. @author MyEclipse Persistence Tools
 */

public class Tel implements java.io.Serializable {

	// Fields

	private Integer no;
	private String name;
	private String telnum;
	private String detail;
	private Integer classId;
	private Integer enableTel;

	// Constructors

	/** default constructor */
	public Tel() {
	}

	/** minimal constructor */
	public Tel(String telnum, Integer classId) {
		this.telnum = telnum;
		this.classId = classId;
	}

	/** full constructor */
	public Tel(String name, String telnum, String detail, Integer classId,
			Integer enableTel) {
		this.name = name;
		this.telnum = telnum;
		this.detail = detail;
		this.classId = classId;
		this.enableTel = enableTel;
	}

	// Property accessors

	public Integer getNo() {
		return this.no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelnum() {
		return this.telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getEnableTel() {
		return this.enableTel;
	}

	public void setEnableTel(Integer enableTel) {
		this.enableTel = enableTel;
	}

}