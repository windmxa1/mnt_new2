package speed.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String userName;
	private String passWord;
	private Short type;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String userName, String passWord, Short type) {
		this.userName = userName;
		this.passWord = passWord;
		this.type = type;
	}

	// Property accessors

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}