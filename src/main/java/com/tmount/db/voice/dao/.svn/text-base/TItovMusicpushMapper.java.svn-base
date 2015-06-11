package com.tmount.db.voice.dao;

import java.util.List;

import com.tmount.db.voice.dto.TItovMusicpush;

public interface TItovMusicpushMapper {

	/**
	 * 插入推行信息
	 * @param musicInfo
	 * @return
	 */
	int insert (TItovMusicpush musicInfo);
	/**
	 * 每次推送完删除推行的音乐
	 * @return
	 */
	int deleteByterImei(String terminalImei);
	/**
	 * 根据车机imei查询推送的音乐列表
	 * @param terminalImei
	 * @return
	 */
	List<TItovMusicpush> selectByterImei(String terminalImei);
}
