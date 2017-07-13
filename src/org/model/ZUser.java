package org.model;

/**
 * ZUser entity. @author MyEclipse Persistence Tools
 */

public class ZUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String name;

	// Constructors

	/** default constructor */
	public ZUser() {
	}

	/** full constructor */
	public ZUser(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}