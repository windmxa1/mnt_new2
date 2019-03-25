package org.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Items entity. @author MyEclipse Persistence Tools
 */

public class Items  implements java.io.Serializable {


    // Fields    

     private Long itemid;
     private Long hostid;
     private String name;
     private String key;
     private int valueType;
     
     /** default constructor */
 	public Items() {
 	}
     
	public Items(Long itemid, Long hostid, String name, String key,int valueType) {
		super();
		this.itemid = itemid;
		this.hostid = hostid;
		this.name = name;
		this.key = key;
		this.valueType = valueType;
	}
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public Long getHostid() {
		return hostid;
	}
	public void setHostid(Long hostid) {
		this.hostid = hostid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public int getValueType() {
		return valueType;
	}
	public void setValueType(int valueType) {
		this.valueType = valueType;
	}
}