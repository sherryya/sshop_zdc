package com.tmount.business.feedback.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.feedback.dao.TZdcFeedbackMapper;
import com.tmount.db.feedback.dto.TZdcFeedback;

@Service
public class FeedbackService {
	@Autowired
	private TZdcFeedbackMapper tZdcFeedbackMapper;
	public int insert(TZdcFeedback tZdcFeedback)
	{
		return tZdcFeedbackMapper.insert(tZdcFeedback);
	}
	
	public List<TZdcFeedback> selectByWhere(TZdcFeedback tzdc_feedback)
	{
		return tZdcFeedbackMapper.selectByWhere(tzdc_feedback);
	}
	
	public  Integer selectSizeByWhere(TZdcFeedback tzdc_feedback)
	{
		return tZdcFeedbackMapper.selectSizeByWhere(tzdc_feedback);
	}
}
