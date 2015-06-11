package com.tmount.exception;

import com.tmount.bundle.ShopErrorCatalog;

public class ShopBusiException extends ShopException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 666137039563821958L;

	public ShopBusiException(String errorCode, Object[] params) {
		super("com.tmount.bundle.ShopBusiErrorBundle", errorCode, params);
	}

	@Override
	public String getErrorCatalog() {
		return ShopErrorCatalog.BUSI_PARAM;
	}

}
