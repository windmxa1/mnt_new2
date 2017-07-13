package org.dao;

import java.util.List;

import org.model.Services;
import org.model.ServicesLinks;

public interface ServicesLinksDao {
	/**
	 * 根据上级服务id获取关联表中对应的行，行中的downid则是该服务的下级服务
	 */
	public List<ServicesLinks> getDownServices(Long serviceupid);
	/**
	 * 根据下级服务id获取关联表中对应的上级id
	 */
	public Long getUpServiceId(Long servicedownid);
}
