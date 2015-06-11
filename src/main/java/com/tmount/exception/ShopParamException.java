package com.tmount.exception;

import com.tmount.bundle.ShopErrorCatalog;

public class ShopParamException extends ShopException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7304114067122189986L;

	public ShopParamException(String errorCode, Object[] params) {
		super("com.tmount.bundle.ShopParamErrorBundle", errorCode, params);
	}

	@Override
	public String getErrorCatalog() {
		return ShopErrorCatalog.CATALOG_PARAM;
	}
}
