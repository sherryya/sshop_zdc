package com.tmount.db.terminalAccount.dao;

import java.util.List;

import com.tmount.db.terminalAccount.dto.TZdcDevices;

public interface TZdcDevicesMapper {

	List<TZdcDevices> selectByDeviceId(String deviceId);
}
