package com.tmount.db.feedback.dao;

import java.util.List;

import com.tmount.db.feedback.dto.TZdcFeedback;

public interface TZdcFeedbackMapper {

	int insert(TZdcFeedback tZdcFeedback);
	List<TZdcFeedback> selectByWhere(TZdcFeedback tZdcFeedback);
    Integer selectSizeByWhere(TZdcFeedback tZdcFeedback);
}
