package com.tmount.crontab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmount.business.car.service.CarInfoService;
import com.tmount.business.car.service.DrivingFieldService;
import com.tmount.business.car.service.WarningMessageService;
import com.tmount.db.car.dto.CarInfo;
import com.tmount.db.car.dto.DrivingField;
import com.tmount.util.CarUtil;

public class DrivingFieldWarning {
	@Autowired
	private DrivingFieldService drivingFieldService;

	@Autowired
	private CarInfoService carInfoService;
	
	@Autowired
	private WarningMessageService warningMessageService;
	
	Logger logger = Logger.getLogger(DrivingFieldWarning.class);

	public void run() {
		logger.debug("围栏告警定时器开始");
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH");
			int compareTime = Integer.parseInt(sdf.format(date));
			//compareTime = 23;
			// 1.判断数据库中表tbl_ellipse，字段state=1，并且当前时间在startTime和endTime之间的数据，然后才能进行判断是否执行告警算法
			List<DrivingField> list = drivingFieldService.getWarning();
			if (list != null) {
				for (DrivingField drivingfield : list) {
					int startTime = drivingfield.getStartTime();
					int endTime = drivingfield.getEndTime();
					if (compareTime >= startTime && compareTime < endTime) {
						// 2.根据围栏id去tbl_carinfo找对应的绑定的车辆
						int drivingFieldId = drivingfield.getId();
						List<CarInfo> carInfoList = carInfoService
								.getCarListByDrivingFieldId(drivingFieldId);
						// 3.根据围栏id去tbl_trains去找对应的绑定的车组,wlid
						List<CarInfo> carInfoArrList = carInfoService
								.getCarListByDrivingFieldIdInTrains(drivingFieldId);
						// 去重
						for (int i = 0; i < carInfoList.size(); i++) {
							if (!carInfoArrList.contains(carInfoList.get(i))) {
								carInfoArrList.add(carInfoList.get(i));
							}
						}
						// 4.根据车来查询当前gprs信息，然后，与DrivingField的圆心，半径向比较。
						for (CarInfo carinfo : carInfoArrList) {
							String circleCenter = drivingfield.getCtr();// 圆心
							String circleRadius = drivingfield.getX();// 半径
							String drivingFieldType = drivingfield.getType();// 围栏类型1驶入触发2驶出触发3驶入驶出触发
							// 根据车去找设备的deviceuid,然后，调用数据接口，返回经纬度，然后，进行反转成图吧地图的坐标，进行比较。
							String deviceuid = carinfo.getDeviceUID();
							String warningFlag = CarUtil.getWarningFlag(
									deviceuid, circleCenter, circleRadius);
							//4.判断围栏类型、1驶入触发2驶出触发3驶入驶出触发
							if("1".equals(warningFlag)&&"1".equals(drivingFieldType)){
								warningMessageService.insertWarningMessage(carinfo,drivingfield);
							}else if("0".equals(warningFlag)&&"2".equals(drivingFieldType)){
								//增加告警记录
								warningMessageService.insertWarningMessage(carinfo,drivingfield);
							}else if("3".equals(drivingFieldType)){
								warningMessageService.insertWarningMessage(carinfo,drivingfield);
							}
						}
					}
				}
			}
			logger.debug("围栏告警定时器结束");
		} catch (Exception e) {
			logger.debug("围栏告警定时器出错");
			e.printStackTrace();
		}

	}
}
