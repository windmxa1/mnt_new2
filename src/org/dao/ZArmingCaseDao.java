package org.dao;

import java.util.List;

import org.model.ZArmingCase;

public interface ZArmingCaseDao {
	/**
	 * 添加或修改
	 */
	public Integer saveOrUpdate(ZArmingCase zCase);
	/**
	 * 获取单个方案，不传参则显示当前激活的方案
	 */
	public ZArmingCase getZalarmCase(Integer id);
	/**
	 * 选择并使用方案
	 */
	public ZArmingCase choseZalarmCase(Integer id);
	/**
	 * 获取方案列表
	 */
	public List<ZArmingCase> getZalarmCaseList();
}
