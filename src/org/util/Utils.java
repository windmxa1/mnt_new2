package org.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用工具类
 * 
 * @author marshall
 */
public class Utils {
	/**
	 * 保存的文件路径 根目录
	 */
	// public final static String BASESRC =
	// "d:\\Tomcat 7.0\\webapps\\CellBank\\upload\\";
	// public final static String BASEURL =
	// "http://192.168.1.102:8080/CellBank/upload/";
	public final static String BASESRC = "/opt/apache-tomcat-7.0.70/webapps/CellBank/upload/";
	public final static String BASEURL = "http://120.76.22.150:8080/CellBank/upload/";

	public final static String PICSRC = "d:\\Tomcat 7.0\\webapps\\LifeBank\\upload\\";
	public final static String PICURL = "http://192.168.1.150:8080/LifeBank/upload/";

	// public final static String PICSRC =
	// "/opt/apache-tomcat-7.0.70/webapps/bank/upload/";
	// public final static String PICURL =
	// "http://120.76.22.150:8080/bank/upload/";

	/**
	 * 将数据封装成目标格式
	 * 
	 * @param entityList
	 *            实体类的数组
	 * @param total
	 *            数据总和
	 * @return
	 */
	public static Object setToResult(List entityList, long total) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("data", entityList);
		map2.put("total", total);
		map1.put("value", map2);
		return map1;
	}

	/**
	 * 将数据封装成目标格式
	 * 
	 * @param entityList
	 *            实体类的数组
	 * @return
	 */
	public static Object setToResult1(List entityList) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("data", entityList);
		map1.put("value", map2);
		return map1;
	}

	/**
	 * 将数据封装成目标格式
	 * 
	 * @param entityList
	 *            实体类的数组
	 * @return
	 */
	public static Object setToResult2(List entityList) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("data", entityList);
		return map1;
	}

	/**
	 * 删除文件
	 */
	public static boolean delFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 判空之后转成空串
	 */
	public static Object isNull(Object a) {
		if (a == null) {
			return "";
		} else {
			return a;
		}
	}

	/**
	 * 判断是否为Integer
	 */
	public static boolean isInteger(String a) {
		try {
			Integer.parseInt((String) a);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 转换Integer
	 */
	public static Integer parseInt(String a) {
		try {
			return Integer.parseInt(a);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 判断是否为Long
	 */
	public static boolean isLong(String a) {
		try {
			Long.parseLong((String) a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static Long parseLong(String a) {
		try {
			return Long.parseLong((String) a);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 批量删除文件
	 */
	public static boolean delFile(List<String> urlList) {
		boolean a = true;
		if (urlList != null && urlList.size() > 0) {
			for (String url : urlList) {
				if (!Utils.delFile(url.replace(Utils.BASEURL, Utils.BASESRC))) {
					System.out.println("删除图片失败了");
					a = false;
				}
			}
			return a;
		} else {
			System.out.println("删除的话题或评论中没有包含任何图片");
			return true;
		}
	}
}
