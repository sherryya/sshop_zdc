package com.tmount.business.zdc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.zdc.dao.TrafficInfoGainnerMapper;
import com.tmount.db.zdc.dto.NewDetailSub;
import com.tmount.db.zdc.dto.TrafficRollSub;

@Service
public class TrafficService {
    @Autowired
    TrafficInfoGainnerMapper trafficInfoGainnerMapper;

    /**
     * 查询路况信息
     * 
     * @param account_id
     * @return
     */
    public List<TrafficRollSub> selectTrafficInfo() {
	return trafficInfoGainnerMapper.selectForTraffic();
    }

    public List<TrafficRollSub> selectTrafficInfo(int parseInt) {
	return trafficInfoGainnerMapper.selectForTrafficbyID(parseInt);
    }
}
