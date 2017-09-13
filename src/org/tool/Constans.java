package org.tool;

import java.io.File;

import org.apache.struts2.ServletActionContext;

public class Constans {
	/**
	 * 适应任何系统的文件分隔符
	 */
	public static final String dot = File.separator;
	public static final String pdfDir = ServletActionContext.getRequest()
			.getRealPath("/") + "pdf" + dot;
	public static final String pdfUrl = "http://"
			+ ServletActionContext.getRequest().getLocalAddr() + ":"
			+ ServletActionContext.getRequest().getLocalPort()
			+ ServletActionContext.getRequest().getContextPath() + "/" + "pdf/";
	public static final String watermark = ServletActionContext.getRequest()
			.getRealPath("/") + "watermark.jpg";

	/**
	 * 自动注销是否启用，默认启动
	 */
	public static boolean isAutoLogout = true;
	/**
	 * 自动注销的时间，默认5分钟
	 */
	public static Long interval = 5*60L;
}
