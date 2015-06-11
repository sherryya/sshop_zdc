package com.tmount.db.zdc.dao;

import java.util.List;

import com.tmount.db.zdc.dto.NewDetailSub;
import com.tmount.db.zdc.dto.NewsRollSub;
import com.tmount.db.zdc.dto.TrafficRollSub;

public interface TrafficInfoGainnerMapper {
    /**
     * 查询全部新闻方法
     * 
     * @param account_id
     * @return
     */
    List<TrafficRollSub> selectForTraffic();

    /**
     * 查询制定ID的路况信息
     * 
     * @param parseInt
     * @return
     */
    List<TrafficRollSub> selectForTrafficbyID(int parseInt);
}
