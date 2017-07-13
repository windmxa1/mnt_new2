package speed.model;

/**
 * Video entity. @author MyEclipse Persistence Tools
 */

public class Video implements java.io.Serializable {

	// Fields

	private Integer no;
	private String name;
	private String ip;
	private String user;
	private String password;
	private String detail;
	private Integer type;

	// Constructors

	/** default constructor */
	public Video() {
	}

	/** minimal constructor */
	public Video(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}

	/** full constructor */
	public Video(String name, String ip, String user, String password,
			String detail, Integer type) {
		this.name = name;
		this.ip = ip;
		this.user = user;
		this.password = password;
		this.detail = detail;
		this.type = type;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}