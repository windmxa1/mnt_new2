package speed.dao;

import java.util.List;
import java.util.Set;

import speed.view.VRecordId;
import speed.view.VSensorsId;

public interface SensorsDao {
	/**
	 * 根据(自动分类)种类获取传感器
	 */
	public List<VSensorsId> getSensorsByType2(Integer start, Integer limit,
			Short type);

	/**
	 * 根据(自动分类)种类获取传感器总数
	 */
	public Long getSensorsByType2Count(Short type);

	/**
	 * 根据(自动分类)种类检测是否有设备异常
	 */
	public boolean checkAlarm(Short type);

	/**
	 * 获取设备异常的机房列表
	 */
	public Set<String> getAlarmJF(Short type);
	
	/**
	 * 获取告警设备及对应设备间名称
	 */
	public List<Object[]> getAlarmInfo();
	
	/**
	 * 获取设备异常的机房列表
	 */
	public Set<String> getAlarmJF();
	/**
	 * 获取各机房传感器故障数目和机房名
	 */
	public List<Object[]> getJFError();

	/**
	 * 获取单个设备的历史记录
	 */
	public List<VRecordId> getHistory(Integer sensorIndex, Integer start,
			Integer limit);

	/**
	 * 获取单个设备的历史记录总数
	 */
	public Long getHistoryCount(Integer sensorIndex);

	/**
	 * 初始化传感器配置表SensorConfig
	 */
	public boolean init();

	/**
	 * 更新传感器布防状态
	 */
	public boolean updateAvailable(Integer sensorIndex, Integer available);
	/**
	 * 根据sensorindex返回对应机房和设备类型
	 */
	public Object[] getSensorInfoByNo(Integer sensorIndex);
	/**
	 * 获取传感器列表（无缓存）
	 */
	List<VSensorsId> getSensorsByType1(Integer start, Integer limit, Short type);
	/**
	 * 获取单个种类的传感器总数（无缓存）
	 */
	Long getSensorsByType1Count(Short type);
	
}
