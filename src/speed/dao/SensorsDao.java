package speed.dao;

import java.util.List;

import speed.view.VSensorsId;

public interface SensorsDao {
	/**
	 * 获取所有传感器
	 */
	public List<VSensorsId> getAllSensors(Integer start, Integer limit);
	/**
	 * 根据(手动分类)种类获取传感器
	 */
	public List<VSensorsId> getSensorsByType(Integer start, Integer limit,Integer type);
	/**
	 * 根据(自动分类)种类获取传感器
	 */
	public List<VSensorsId> getSensorsByType2(Integer start, Integer limit,Short type);
}
