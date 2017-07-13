package org.model;

/**
 * ZBoard entity. @author MyEclipse Persistence Tools
 */

public class ZBoard implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Integer time;
	private Integer userId;

	// Constructors

	/** default constructor */
	public ZBoard() {
	}

	/** minimal constructor */
	public ZBoard(String title, String content, Integer time, Integer userId) {
		this.title = title;
		this.content = content;
		this.time = time;
		this.userId = userId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}