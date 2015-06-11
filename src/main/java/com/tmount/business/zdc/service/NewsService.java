package com.tmount.business.zdc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.zdc.dao.NewsInfoGainnerMapper;
import com.tmount.db.zdc.dto.NewDetailSub;
import com.tmount.db.zdc.dto.NewsRollSub;

@Service
public class NewsService {
    @Autowired
    NewsInfoGainnerMapper newsInfoGainnerMapper;

    /**
     * 查询新闻服务
     * 
     * @param account_id
     * @return
     */
    public List<NewsRollSub> selectNewInfo() {
	return newsInfoGainnerMapper.selectForNews();
    }

    public List<NewDetailSub> selectNewInfo(int id) {
	return newsInfoGainnerMapper.selectForNewsByID(id);
    }

}
