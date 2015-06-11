package com.tmount.db.car.dao;

import java.util.List;

import com.tmount.db.car.dto.CarDelete;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.SequenceTable;
import com.tmount.db.car.dto.TItovCar;
import com.tmount.db.car.dto.TItov_car_brand_golo3;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.db.util.dto.CommonBean;

public interface CarInfoMapper {
	List<CarInfo> getCarListByDrivingFieldId(int drivingFieldId);

	List<CarInfo> getCarListByDrivingFieldIdInTrains(int drivingFieldId);

	int insert(CarInfo carinfo);
	
	int carhotInsert(CarInfo carinfo);
	
	List<CarInfo> getCarInfoByPlateNum(CarInfo carinfo);

	int getCarId(String car_id);
	
	Integer querySequenceId(String name);
	Integer updSequenceId(SequenceTable seqTable);
	
	Integer updtestupd(TestUpd testupd);
	Integer queryId(String name);
	CarDelete getIsExistCarInfoByCarIDAndAccount(CarDelete carDelete);

	void deleteCarAccountByCarID(CarDelete carDelete);
	
	int deleteCarByCarID(int car_id);

	void updateDefaultCarAccountByCarID(CarDelete carDelete);

	void deleteDefaultCarByUserID(CarDelete carDelete);

	void deleteUserByUserIDAccountID(CarDelete cd);

	void deleteCarInfoByCarID(CarDelete cd);
	
	int deleteBySubAccountId(Long account_id);
	
	int deletePersonByAccountId(Long account_id);
	
	int deleteAccountByAccountName(String account_name);
	
	int deleteOnlineByAccountId(String account_name);
	int deleteBreakListBycarPlateNum (String car_plate_num);

	Long getMaxId();

	List<CommonBean> getCarBrands(Long id);

	CarInfo getCarMesssageByCarId(int car_id);

	CarInfo getAccessByAccount(int account_id);

	CarInfo getItovCarMesssageByCarId(int car_id);

	void updatecarmessage(CarInfo carinfo);

	List<UserRelationCarInfo> getuserRelationCarInfo(
			UserRelationCarInfo userRelationCarInfo);

	TItov_car_brand_golo3 getCodeByCarID(long car_id);

	void updatecarname(CarInfo carInfo);
	/**
	 * 根据voip号获得车的信息
	 * @param voipAccount
	 * @return
	 */
	List<TItovCar> getCarInfoByAccountId(String voipAccount);
	/**
	 * 根据voip号获得terminal信息
	 * @param voipAccount
	 * @return
	 */
	List<TItovCarVo> selectTerminal(String accountId);
	
	List<TItovCar> getIsExsitCarInfoByAccountId(long account_id);
	
	List<TItovCarVo> qryCarInfoAll();
	/**
	 * 根据accountId查询车牌号,车架号,发动机号的信息
	 * @param account_id
	 * @return
	 */
	List<TItovCarVo> qryCarInfoByAccountID(long account_id);
	
	List<TItovCarVo> qryCarInfoByPlateNum(String car_plate_number);
	/**
	 * 根据手机account_id查询绑定的所有车辆
	 * @param account_id
	 * @return
	 */
	List<TItovCarVo> getCarByAccountId(long account_id);
}
