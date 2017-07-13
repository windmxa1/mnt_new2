package org.dao;

import java.util.List;

import org.model.ServiceHost;

public interface ServiceHostDao {
	public List<ServiceHost> getList();
	public boolean insert(Long hostid,Long serviceid);
	public boolean del(Integer id);
//	public boolean rewrite(Long hostid);
	public ServiceHost getSHByHostid(Long hostid);
}
