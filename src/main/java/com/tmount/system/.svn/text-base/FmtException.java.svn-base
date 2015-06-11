package com.tmount.system;

import java.util.ResourceBundle;
public abstract class FmtException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9193962983894537823L;
	
	/**
	 * 得到错误代码的前缀分类。FmtException的子类必须实现getErrorCatalog方法。
	 * @return
	 */
	public abstract String getErrorCatalog();

	private String errorCode;
	private String errorMessage;

	/**
	 * “%”为占位符号，使用双“%%”符号做为“%”的转义字符。
	 * {@code}		String ss1 = "sssss1";
	 * 	Integer ss2 = new Integer(333);
	 * 	throw  new ShopException("one%two%three%four", 2234, new Object[]{ss1, ss2, 444});
	 * @param message
	 * @param errorCode
	 * @param params
	 */
	public FmtException(String baseName, String errorCode, Object[] params) {
		super(baseName);
		ResourceBundle rb = ResourceBundle.getBundle(baseName);
		String message = rb.getString(errorCode);
		
		StringBuilder sb = new StringBuilder();
		int strLen = message.length();
		int paramPosition = 0;
		int i = 0;
		while(i < strLen) {
			if (message.charAt(i) == '%') {
				if (i != strLen - 1) {
					if (message.charAt(i + 1) == '%') {
						i += 2;
						sb.append('%');
						continue;
					}
				}
			}
				
			if (message.charAt(i) == '%') {
				if (paramPosition < params.length) {
					sb.append(params[paramPosition].toString());
					paramPosition ++;
				} else {
					sb.append(message.charAt(i));
				}
			} else {
				sb.append(message.charAt(i));
			}
			i ++;
		}
		this.setErrorCode(errorCode);
		errorMessage = sb.toString();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getMessage() {
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * 返回一个完成的错误代码
	 * @return
	 */
	public String getFullErrorCode() {
		return getErrorCatalog() + errorCode;
	}
}
