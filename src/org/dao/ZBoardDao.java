package org.dao;

import java.util.List;

import org.model.ZBoard;

public interface ZBoardDao {
	/**
	 * 添加公告
	 * @param board
	 * @return
	 */
	public boolean addBoard(ZBoard board);
	/**
	 * 删除公告
	 * @param id
	 * @return
	 */
	public boolean deleteBoard(Integer[] id);
	/**
	 * 查询公告，view类，时间戳转时间，绑定用户名
	 * @return
	 */
	public List getBoardList(Integer start , Integer limit);
	/**
	 * 统计数目
	 */
	public Long getBoardCount();
}
