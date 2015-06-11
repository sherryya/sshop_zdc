package com.tmount.business.zdc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.zdc.dao.VideoInfoGainnerMapper;
import com.tmount.db.zdc.dto.VideoSub;

@Service
public class VideoService {
    @Autowired
    VideoInfoGainnerMapper videoInfoGainnerMapper;

    public List<VideoSub> selectByType(VideoSub videoSub) {
	return videoInfoGainnerMapper.selectVideoTypeLimited(videoSub);
    }

    public List<VideoSub> selectByTitle(VideoSub videoSub) {
	return videoInfoGainnerMapper.selectVideoTitleLimited(videoSub);
    }

    public List<VideoSub> selectAll(VideoSub videoSub) {
	return videoInfoGainnerMapper.selectVideoAll(videoSub);
    }

    public void insertVideo(VideoSub videoSub) {
	videoInfoGainnerMapper.insertVideo(videoSub);
    }

    public List<VideoSub> selectSingle(VideoSub videoSub) {
	return videoInfoGainnerMapper.selectVideoID(videoSub);
    }

}
