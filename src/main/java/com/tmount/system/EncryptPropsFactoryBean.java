package com.tmount.system;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;

public class EncryptPropsFactoryBean extends PropertiesFactoryBean {
	/**
	 * 加密的字段列表，字段之间使用逗号分割。
	 */
	private List<String> encryptFields;
	

	public List<String> getEncryptFields() {
		return encryptFields;
	}


	public void setEncryptFields(List<String> encryptFields) {
		this.encryptFields = encryptFields;
	}


	/**
	 * 初始化方法，需要设置initParams参数。
	 * @throws IOException
	 */
	final protected void init() throws IOException{
		Properties props = super.getObject();
		for(Object key: props.keySet()) {
			for(String encrpytField: encryptFields) {
				if (key.equals(encrpytField)) {
					props.setProperty((String) key, DESUtils.getDecryptString((String) props.get(key)));
				}
			}
			
		}
	}

}
