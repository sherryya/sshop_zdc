/*
 *  Copyright (c) 2013 The CCP project authors. All Rights Reserved.
 *
 *  Use of this source code is governed by a Beijing Speedtong Information Technology Co.,Ltd license
 *  that can be found in the LICENSE file in the root of the web site.
 *
 *   http://www.cloopen.com
 *
 *  An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */
package com.tmount.business.cloopen.model.httprequest;

/**
 * 
* <p>Title: SubAccountCreate.java</p>
* <p>Description: �������ʺ�</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: http://www.cloopen.com</p>
* @author JorstinChan
* @date 2013-9-27
* @version 2.4
 */
public class SubAccountCreate {

	private String appId;
	private String friendlyName;
	private String accountSid;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getFriendlyName() {
		return friendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public SubAccountCreate(String appId, String friendlyName, String accountSid) {
		super();
		this.appId = appId;
		this.friendlyName = friendlyName;
		this.accountSid = accountSid;
	}
	public SubAccountCreate() {
		super();
	}

	
}
