package org.dao;

import java.util.List;

import org.model.Triggers;

public interface TriggersDao {
	/**
	 * 通过hostid找到对应的所有TriggerId
	 */
	public List getTriggersByHostId(Long hostId);
}
