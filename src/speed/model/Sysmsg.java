package speed.model;

import java.sql.Timestamp;

/**
 * Sysmsg entity. @author MyEclipse Persistence Tools
 */

public class Sysmsg implements java.io.Serializable {

	// Fields

	private String name;
	private Short valChar;
	private Integer valInt;
	private Double valFloat;
	private Timestamp valTime;
	private String valString;
	private Integer indexNo;
	private String groupName;

	// Constructors

	/** default constructor */
	public Sysmsg() {
	}

	/** minimal constructor */
	public Sysmsg(String name, Timestamp valTime, Integer indexNo) {
		this.name = name;
		this.valTime = valTime;
		this.indexNo = indexNo;
	}

	/** full constructor */
	public Sysmsg(String name, Short valChar, Integer valInt, Double valFloat,
			Timestamp valTime, String valString, Integer indexNo,
			String groupName) {
		this.name = name;
		this.valChar = valChar;
		this.valInt = valInt;
		this.valFloat = valFloat;
		this.valTime = valTime;
		this.valString = valString;
		this.indexNo = indexNo;
		this.groupName = groupName;
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getValChar() {
		return this.valChar;
	}

	public void setValChar(Short valChar) {
		this.valChar = valChar;
	}

	public Integer getValInt() {
		return this.valInt;
	}

	public void setValInt(Integer valInt) {
		this.valInt = valInt;
	}

	public Double getValFloat() {
		return this.valFloat;
	}

	public void setValFloat(Double valFloat) {
		this.valFloat = valFloat;
	}

	public Timestamp getValTime() {
		return this.valTime;
	}

	public void setValTime(Timestamp valTime) {
		this.valTime = valTime;
	}

	public String getValString() {
		return this.valString;
	}

	public void setValString(String valString) {
		this.valString = valString;
	}

	public Integer getIndexNo() {
		return this.indexNo;
	}

	public void setIndexNo(Integer indexNo) {
		this.indexNo = indexNo;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}