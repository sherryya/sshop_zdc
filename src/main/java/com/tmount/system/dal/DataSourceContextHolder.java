package com.tmount.system.dal;

/**
 * DataSource上下文句柄，通过此类设置需要访问的对应数据源
 * 
 */
public class DataSourceContextHolder {
	/**
	 * DataSource上下文，每个线程对应相应的数据源key
	 */
	private static final ThreadLocal<DataSourceInfo> contextHolder = new ThreadLocal<DataSourceInfo>();

	/**
	 * 数据源的调用参数统计
	 */
	private static final ThreadLocal<Integer> contextCounter = new ThreadLocal<Integer>();

	/**
	 * 得到下一个计数。
	 * @return
	 */
	public static Integer getNextCounter() {
		Integer curCounter = contextCounter.get();
		if (curCounter == null) {
			curCounter = new Integer(0);
		}
		curCounter ++;
		if (curCounter < 0) {
			curCounter = 0;
		}
		contextCounter.set(curCounter);
		return curCounter;
	}
	
	
	/**
	 * 得到当前线程的数据源。
	 * @param dataSourceType
	 */
	public static DataSourceInfo getDataSource() {
		return contextHolder.get();
	}

	/**
	 * 设置当前线程的数据源
	 * @param dataSourceType
	 */
	public static void setDataSource(DataSourceInfo dataSourceInfo) {
		contextHolder.set(dataSourceInfo);
	}
	
	/**
	 * 得到当前线程的数据源名称
	 * @return
	 */
	public static String getDataSourceName() {
		if (contextHolder.get() != null) {
			return contextHolder.get().getDataSourceName();
		} else {
			return "";	//返回空表示，使用默认数据源，即Spring配置文件中的defaultTargetDataSource。
		}
	}

	/**
	 * 清除当前线程数据源名称
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}
}
