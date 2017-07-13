package org.dao;

import java.util.List;

public interface JfHostDao {
	/**
	 * 找到机房下的所有设备Ip
	 */
	public List<String> getHostip(Long serviceid);
}
