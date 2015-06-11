package com.tmount.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class AppPropertiesConfig {
	/**
	 * 应用程序属性
	 */
	private Properties appPropertiesConfig = new Properties();

	/**
	 * 报文加密规则属性
	 */
	private Properties appKeyProps = new Properties();

	private void initAppConfig(Properties properties, String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);
		if (url != null) {
			String urlStr = url.toString().substring(5);
			String empUrl = urlStr.replace("%20", " ");// 替换成空格。
			System.out.println("empUrl" + empUrl);
			InputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(empUrl));
				properties.load(in);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void init() {
		initAppConfig(appPropertiesConfig, "appConfig.properties");
		initAppConfig(appKeyProps, "appKey.properties");
	}
	
	public Properties getAppPropertiesConfig() {
		return appPropertiesConfig;
	}

	public void setAppConfigProps(Properties appPropertiesConfig) {
		this.appPropertiesConfig = appPropertiesConfig;
	}


	public Properties getAppKeyProps() {
		return appKeyProps;
	}

	public void setAppKeyProps(Properties appKeyProps) {
		this.appKeyProps = appKeyProps;
	}
}
