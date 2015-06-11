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
* <p>Title: LandingCallsRequest.java</p>
* <p>Description:  Ӫ�����</p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: http://www.cloopen.com</p>
* @author JorstinChan
* @date 2013-9-27
* @version 2.4
 */
public class LandingCallsRequest {

	private String appId;
	private String mediaName;
	private String playTimes;
	private String to;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public String getPlayTimes() {
		return playTimes;
	}
	public void setPlayTimes(String playTimes) {
		this.playTimes = playTimes;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public LandingCallsRequest(String appId, String mediaName, String playTimes,
			String to) {
		super();
		this.appId = appId;
		this.mediaName = mediaName;
		this.playTimes = playTimes;
		this.to = to;
	}
	public LandingCallsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
