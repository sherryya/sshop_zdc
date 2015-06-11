package com.tmount.business.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.car.dao.CarInfoMapper;
import com.tmount.db.car.dao.TItovCarDeleteLogMapper;
import com.tmount.db.car.dao.TZdcCarhotStrategyMapper;
import com.tmount.db.car.dto.CarDelete;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.SequenceTable;
import com.tmount.db.car.dto.TItovCar;
import com.tmount.db.car.dto.TItov_car_brand_golo3;
import com.tmount.db.car.dto.TZdcCarhotStrategy;
import com.tmount.db.car.dto.TestUpd;
import com.tmount.db.car.dto.UserRelationCarInfo;
import com.tmount.db.car.vo.TItovCarVo;
import com.tmount.db.util.dto.CommonBean;

@Service
public class CarInfoService {
	@Autowired
	private CarInfoMapper carInfoMapper;
	@Autowired
	private TZdcCarhotStrategyMapper tZdcCarhotStrategyMapper;
    @Autowired
    private TItovCarDeleteLogMapper tItovCarDeleteLogMapper;
	
	public List<CarInfo> getCarListByDrivingFieldId(int drivingFieldId){
		return carInfoMapper.getCarListByDrivingFieldId(drivingFieldId);
	}
	public List<CarInfo> getCarListByDrivingFieldIdInTrains(int drivingFieldId){
		return carInfoMapper.getCarListByDrivingFieldId(drivingFieldId);
	}
	
	public void insert(CarInfo carinfo){
		carInfoMapper.insert(carinfo);
	}
	/**
	 * 车热了吗的插入 20150421
	 * @param carinfo
	 * @return
	 */
	public int carhotInsert(CarInfo carinfo)
	{
		return carInfoMapper.carhotInsert(carinfo);
	}
	/**
	 * 车热了吗 根据车架号和车牌号查询是否存在车辆信息
	 * @param carinfo
	 * @return
	 */
	public List<CarInfo> getCarInfoByPlateNum(CarInfo carinfo)
	{
		return carInfoMapper.getCarInfoByPlateNum(carinfo);
	}
    /**
     * 判断车辆信息是否存在
     * @param carDelete
     * @return
     */
	public CarDelete getIsExistCarInfoByCarIDAndAccount(CarDelete carDelete){
		return carInfoMapper.getIsExistCarInfoByCarIDAndAccount(carDelete);
	}
	/**
	 * 删除车辆信息
	 * @param carDelete
	 */
	public void   deleteCarAccountByCarID(CarDelete carDelete)
	{
		 carInfoMapper.deleteCarAccountByCarID(carDelete);
	}
	/**
	 * 20150421 删除车辆绑定关系
	 * @param car_id
	 * @return
	 */
	public int deleteCarByCarID(int car_id)
	{
		return carInfoMapper.deleteCarByCarID(car_id);
	}
	/**
	 * 设置默认显示车辆信息
	 * @param carDelete
	 */
	public void   updateDefaultCarAccountByCarID(CarDelete carDelete)
	{
		 carInfoMapper.updateDefaultCarAccountByCarID(carDelete);
	}
	/**
	 * 删除默认车辆
	 * @param carDelete
	 */
	public void   deleteDefaultCarByUserID(CarDelete carDelete)
	{
		 carInfoMapper.deleteDefaultCarByUserID(carDelete);
	}
	/*
	public int getCarId(String car_id){
		return carInfoMapper.getCarId(car_id);
	}
	*/
	/*public int getCarId(String car_id){
		String name = car_id;
		int value = carInfoMapper.querySequenceId(name);
		SequenceTable seqTable = new SequenceTable();
		seqTable.setName(name);
		//seqTable.setId(1);
		int current_value = value+1;
		seqTable.setCurrent_value(current_value);
		carInfoMapper.updSequenceId(seqTable);
		return current_value;
	}*/
	//查询序列值
	public int queryId(String name)
	{
		return carInfoMapper.queryId(name);
	}
	//更新数据库序列值
	public int updtestupd(TestUpd testupd)
	{
		return carInfoMapper.updtestupd(testupd);
	}
	/**
	 * 查询序列id的值   改造由于 mysql  cluster 双机备份 20150407
	 * @param name
	 * @return
	 */
	public int querySequenceId(String name)
	{
		return carInfoMapper.querySequenceId(name);
	}
	/**
	 * 更新序列id的值   改造由于 mysql  cluster 双机备份 20150407
	 * @param seqTable
	 * @return
	 */
	public int updSequenceId(SequenceTable seqTable)
	{
		return carInfoMapper.updSequenceId(seqTable);
	}
	public	void deleteUserByUserIDAccountID(CarDelete cd)
	{
		 carInfoMapper.deleteUserByUserIDAccountID(cd);
	}
	public	void deleteCarInfoByCarID(CarDelete cd)
	{
		 carInfoMapper.deleteCarInfoByCarID(cd);
	}
	//20150415  begin
    public int deleteBySubAccountId(Long account_id)
    {
    	return carInfoMapper.deleteBySubAccountId(account_id);
    }
	
	public int deletePersonByAccountId(Long account_id)
	{
		return carInfoMapper.deletePersonByAccountId(account_id);
	}
	
	public int deleteAccountByAccountName(String account_name)
	{
		return carInfoMapper.deleteAccountByAccountName(account_name);
	}
	
	public int deleteOnlineByAccountId(String account_name)
	{
		return carInfoMapper.deleteOnlineByAccountId(account_name);
	}
	public int deleteBreakListBycarPlateNum (String car_plate_num)
	{
		return carInfoMapper.deleteBreakListBycarPlateNum(car_plate_num);
	}

	public Long getMaxId(){
		return carInfoMapper.getMaxId();
	}
	public List<CommonBean> getCarBrands(Long id){
		return carInfoMapper.getCarBrands(id);
	}
	
	public CarInfo getCarMesssageByCarId(int car_id){
		return carInfoMapper.getCarMesssageByCarId(car_id);
	}
	
	public CarInfo getAccessByAccount(int account_id){
		return carInfoMapper.getAccessByAccount(account_id);
	}
	
	public void updatecarmessage(CarInfo carinfo){
		carInfoMapper.updatecarmessage(carinfo);
	}
	public CarInfo getItovCarMesssageByCarId(int car_id){
		return carInfoMapper.getItovCarMesssageByCarId(car_id);
	}
	
	public List<UserRelationCarInfo> getuserRelationCarInfo(UserRelationCarInfo userRelationCarInfo){
		return carInfoMapper.getuserRelationCarInfo(userRelationCarInfo);
	} 
	public TItov_car_brand_golo3 getCodeByCarID(long car_id)
	{
		return carInfoMapper.getCodeByCarID(car_id);
	}
	
	public void updatecarname(CarInfo carInfo)
	{
		 carInfoMapper.updatecarname(carInfo);
	}
	
	public void insert_car_log(long car_id)
	{
		tItovCarDeleteLogMapper.insert_car_log(car_id);
	}
	/**
	 * 根据voip账号获得车的信息
	 * @param voipAccount
	 * @return
	 */
	public List<TItovCar> carList(String voipAccount)
	{
		return carInfoMapper.getCarInfoByAccountId(voipAccount);
	}
	/**
	 * 根据accountId账号获得车的信息
	 * @param voipAccount
	 * @return
	 */
	public List<TItovCarVo> selectTerminal(String accountId)
	{
		return carInfoMapper.selectTerminal(accountId);
	}
	/**
	 * 根据account_id判断是否存在车辆信息
	 * 20141105
	 * @param accountId
	 * @return
	 */
	public List<TItovCar> isExsitList(long accountId)
	{
		return carInfoMapper.getIsExsitCarInfoByAccountId(accountId);
	}
	/**
	 * 查询车辆信息  add 车辆违章查询使用
	 * 20141128
	 * @return
	 */
	public List<TItovCarVo> carInfoAll()
	{
		return carInfoMapper.qryCarInfoAll();
	}
	/**
	 * 根据accountId查询车牌号,车架号,发动机号的信息
	 * @param account_id
	 * @return
	 */
	public List<TItovCarVo> qryCarInfoByAccountID(long account_id)
	{
		return carInfoMapper.qryCarInfoByAccountID(account_id);
	}
	/**
	 * 根据车牌号查询车辆信息
	 * @param car_plate_number
	 * @return
	 */
	public List<TItovCarVo>	qryCarInfoByPlateNum(String car_plate_number)
	{
		return carInfoMapper.qryCarInfoByPlateNum(car_plate_number);
	}
	/**
	 * 添加热车策略信息
	 */
	public int saveCarHotStrategy(TZdcCarhotStrategy tZdcCarhotStrategy){
		return tZdcCarhotStrategyMapper.saveCarHotStrategy(tZdcCarhotStrategy);
	}
	/**
	 * 修改热车策略信息
	 */
	public int updateCarHotStrategy(TZdcCarhotStrategy tZdcCarhotStrategy){
		return tZdcCarhotStrategyMapper.updateCarHotStrategy(tZdcCarhotStrategy);
	}
	/**
	 * 查询此设备是否添加策略
	 */
	public int selectStrategyCount(String id){
		return tZdcCarhotStrategyMapper.selectStrategyCount(id);
	}
	/**
	 * 根据设备号查询策略信息
	 * @param device_id
	 * @return
	 */
	public TZdcCarhotStrategy selectStrategyInfo(String device_id)
	{
		return tZdcCarhotStrategyMapper.selectStrategyInfo(device_id);
	}
	/**
	 * 修改车策略的状态
	 * @param tZdcCarhotStrategy
	 * @return
	 */
	public int updateCarHotStatus(TZdcCarhotStrategy tZdcCarhotStrategy)
	{
		return tZdcCarhotStrategyMapper.updateCarHotStatus(tZdcCarhotStrategy);
	}
	/**
	 * 根据手机account_id查询绑定的所有车辆
	 * @param account_id
	 * @return
	 */
	public List<TItovCarVo> getCarByAccountId(long account_id)
	{
		return carInfoMapper.getCarByAccountId(account_id);
	}
	
}
