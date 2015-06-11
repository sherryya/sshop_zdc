package com.tmount.business.voice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.voice.dao.TItovMusicpushMapper;
import com.tmount.db.voice.dto.TItovMusicpush;

@Service
public class TItovMusicpushService {
	@Autowired
	private TItovMusicpushMapper tmusicmapper;
	
	/**
	 * 插入音乐信息
	 * @param musicInfo
	 * @return
	 */
	public int insert(TItovMusicpush musicInfo)
	{
		return tmusicmapper.insert(musicInfo);
	}

	/**
	 * 每次推送完删除推行的音乐
	 * @return
	 */
	public int deleteByterImei(String terminalImei)
	{
		return tmusicmapper.deleteByterImei(terminalImei);
	}
	/**
	 * 根据车机imei查询推送的音乐列表
	 * @param terminalImei
	 * @return
	 */
	public List<TItovMusicpush> selectByterImei(String terminalImei)
	{
		return tmusicmapper.selectByterImei(terminalImei);
	}
}
