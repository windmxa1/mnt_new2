package org.dao;

import java.util.List;

import org.model.ZFile;

public interface ZFileDao {
	/**
	 * 添加文件上传信息
	 * @param f
	 * @return
	 */
	public boolean addFile(ZFile f);
	/**
	 * 获取上传文件的列表
	 * @return
	 */
	public List getFileList(Integer start,Integer limit);
	/**
	 * 通过id获取文件对象
	 * @return id
	 */
	public ZFile getFileById(int id);
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	public boolean deleteFile(int id);
	/**
	 * 获取文件总数目
	 * @return
	 */
	public Long getFileCount();
}
