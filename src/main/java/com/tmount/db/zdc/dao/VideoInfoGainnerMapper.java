package com.tmount.db.zdc.dao;

import java.util.List;

import com.tmount.db.zdc.dto.VideoSub;

public interface VideoInfoGainnerMapper {
    /**
     * 查询视频总数
     * 
     * @param videoSub
     * @return 视频总数
     */
    Integer selectVideoSize(VideoSub videoSub);

    /**
     * 查询视频类型 0-0/0-1
     * 
     * @param videoSub
     * @return
     */
    List<VideoSub> selectVideoTypeLimited(VideoSub videoSub);

    /**
     * 查询视频标题 模拟关键字模糊查询
     * 
     * @param videoSub
     * @return
     */
    List<VideoSub> selectVideoTitleLimited(VideoSub videoSub);

    /**
     * 查询视频总数
     * 
     * @param videoSub
     * @return
     */
    List<VideoSub> selectVideoLimited(VideoSub videoSub);

    /**
     * 插入视频
     * 
     * @param sub
     */
    void insertVideoBean(VideoSub sub);

    /**
     * 查询全部视频信息
     * 
     * @return
     */
    List<VideoSub> selectVideoAll(VideoSub sub);

    /**
     * 插入视频
     * 
     * @param videoSub
     */
    void insertVideo(VideoSub videoSub);

    /**
     * 根据ID查询数据
     * 
     * @param videoSub
     * @return
     */
    List<VideoSub> selectVideoID(VideoSub videoSub);

}
