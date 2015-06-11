package com.tmount.business.zdc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.zdc.dao.NewTotalInfoGainnerMapper;
import com.tmount.db.zdc.dto.NewsRollSub;
import com.tmount.db.zdc.dto.NewDetailSub;

@Service
public class NewTotalService {
    @Autowired
    NewTotalInfoGainnerMapper newTotalInfoGainnerMapper;

    /**
     * 查询新闻服务ByID
     * 
     * @param account_id
     * @return
     */
    public List<NewDetailSub> selectNewInfo(int id) {
	return newTotalInfoGainnerMapper.selectForNewsByID(id);
    }

    public List<NewsRollSub> selectNewInfo() {
	return newTotalInfoGainnerMapper.selectForNews();
    }

    public List<NewsRollSub> selectNewInfoSimple(String type) {
	return newTotalInfoGainnerMapper.selectForNewsByType(type);
    }
}
