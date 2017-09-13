package org.dao;

import java.util.List;

import org.model.ZIpcRecord;

public interface ZIPCRecordDao {
	/**
	 * 获取本地录像列表
	 */
	public List<ZIpcRecord> getList(Integer start,
			Integer limit);

	/**
	 * 保存本地录像文件的路径
	 */
	public Long saveOrUpdate(ZIpcRecord record);
	/**
	 * 获取录像文件个数
	 */
	public Long getCount();
}
