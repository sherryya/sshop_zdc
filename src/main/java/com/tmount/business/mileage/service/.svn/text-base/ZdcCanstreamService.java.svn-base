package com.tmount.business.mileage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.mileage.dao.TZdcCanstreamOriginalMapper;
import com.tmount.db.mileage.dao.ZdcCanstreamMapper;
import com.tmount.db.mileage.dto.TZdcCanstreamOriginal;
import com.tmount.db.mileage.dto.ZdcCanstream;

@Service
public class ZdcCanstreamService {
	@Autowired
	private ZdcCanstreamMapper zdcCanstreamMapper;
	@Autowired
	private TZdcCanstreamOriginalMapper zdcCanOrignalMapper;

	public void insert(ZdcCanstream zdcCanstream) {
		zdcCanstreamMapper.insert(zdcCanstream);
	}

	public ZdcCanstream selectForID1(ZdcCanstream zdcCanstream) {
		return zdcCanstreamMapper.selectForID1(zdcCanstream);
	}

	public ZdcCanstream selectForID2(ZdcCanstream zdcCanstream) {
		return zdcCanstreamMapper.selectForID2(zdcCanstream);
	}

	public List<ZdcCanstream> selectForID3(ZdcCanstream zdcCanstream) {
		return zdcCanstreamMapper.selectForID3(zdcCanstream);
	}

	/**
	 * 将计算完里程信息的记录标记为里程已经计算完成 Mileage_Finish=1
	 * 
	 * @param id
	 * @return
	 */
	public int updateMileFlag(long id) {
		return zdcCanstreamMapper.updateMileFlag(id);
	}

	/**
	 * 查询最新的can盒子数据
	 * 
	 * @param deviceuid
	 * @return
	 */
	public ZdcCanstream selectNewCan(String deviceuid) {
		return zdcCanstreamMapper.selectCanNew(deviceuid);
	}

	/**
	 * 查询车门4状态
	 * 
	 * @param deviceuid
	 * @return
	 */
	public ZdcCanstream selectCarDoorState(String deviceuid) {
		return zdcCanstreamMapper.selectCarDoorState(deviceuid);
	}

	/**
	 * 查询车窗4状态
	 * 
	 * @param deviceuid
	 * @return
	 */
	public ZdcCanstream selectCarWindowState(String deviceuid) {
		return zdcCanstreamMapper.selectCarWindowState(deviceuid);
	}

	/**
	 * 查询车胎压4状态
	 * 
	 * @param deviceuid
	 * @return
	 */
	public ZdcCanstream selectCarPressureState(String deviceuid) {
		return zdcCanstreamMapper.selectCarPressureState(deviceuid);
	}
	/**
	 * 插入数据 canstreamoriginal 表
	 * @param canorignal
	 * @return
	 */
	public int insertValues(TZdcCanstreamOriginal canorignal)
	{
		return zdcCanOrignalMapper.insertValues(canorignal);
	}
	/**
	 * 根据设备id查询开始或者结束标识
	 * @param deviceid
	 * @return
	 */
	public TZdcCanstreamOriginal selectCanOriginal(String deviceid)
	{
		return zdcCanOrignalMapper.selectCanOriginal(deviceid);
	}

}
