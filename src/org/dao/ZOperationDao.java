package org.dao;


public interface ZOperationDao {
	/**
	 * 修改或新增操作时间记录
	 */
	public boolean saveOrUpdate(Integer userid, Long clock);

	/**
	 * 判断当前操作时间间隔是否小于5分钟
	 */
	public boolean checkOpeartion(Integer userid,Long clock,Long interval);
}
