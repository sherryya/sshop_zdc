package com.tmount.db.user.dao;

import java.util.List;

import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.user.dto.UsAccount;
import com.tmount.db.user.dto.UsUser;
import com.tmount.db.util.dto.CommonBean;

public interface UsUserMapper {

	UsUser getUsUser(String userAccount);

	int insertUserInfo(UsUser usUser);

	int insertRelationUserAndCar(UserRelationCarInfo userRelationCarInfo);

	Long getUserMessage(Long account_id);

	List<CommonBean> getRetMessageList(Long account_id);

	String getRelationCarInfoCompare(int user_id);
	
	List<UserRelationCarInfo> getRelationCarInfo(long user_id);

	CommonBean getPictureMessage(String car_brands);

	String getRelationUserInfoByAccountId(int account_id);

	CommonBean getVersion(int platform);
	CommonBean getVersion_ter(CommonBean commonBean);
	int getAgentId(String agent_id);
	
	CommonBean getCommonDeviceUid(Long account_id);
	
	
	CommonBean getCommonDeviceUidForGPS(String personal_tel);
	
	void updateNickNameByAccount(UsAccount usAccount);
	
	int updatePicName(UsAccount usAccount);
	int updatePwd(UsAccount usAccount);
	
	int updateAccountName(UsAccount usAccount);
}