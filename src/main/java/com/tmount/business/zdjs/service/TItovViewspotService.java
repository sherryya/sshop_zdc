package com.tmount.business.zdjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tmount.db.zdjs.dao.TItovViewspotMapper;
import com.tmount.db.zdjs.dao.TItovViewspotPictureMapper;
import com.tmount.db.zdjs.dto.TItovViewspot;
import com.tmount.db.zdjs.dto.TItovViewspotPicture;

@Service
public class TItovViewspotService {
	@Autowired
	private TItovViewspotMapper tItovViewspotMapper;
	@Autowired
	private TItovViewspotPictureMapper tItovViewspotPictureMapper;

	public void insert_viewspot(TItovViewspot tItovViewspot) {
		tItovViewspotMapper.insert(tItovViewspot);
	}

	public void insert_viewspot_picture(
			TItovViewspotPicture tItovViewspotPicture) {
		tItovViewspotPictureMapper.insert(tItovViewspotPicture);
	}

	public void updateByPrimaryKeySelective(TItovViewspot tItovViewspot) {
		tItovViewspotMapper.updateByPrimaryKeySelective(tItovViewspot);
	}

	public TItovViewspot selectByLonlat_id(String lonlatId) {
		return tItovViewspotMapper.selectByLonlat_id(lonlatId);
	}

	public void deleteViewSpotByLonlat_id(TItovViewspot tItovViewspot) {
		tItovViewspotMapper.deleteViewSpotByLonlatId(tItovViewspot);
	}

	public void deleteViewSpotPictureByLonlat_id(
			TItovViewspotPicture tItovViewspotPicture) {
		tItovViewspotPictureMapper.deleteByLonlat_id(tItovViewspotPicture);
	}

	public List<TItovViewspot> selectAll() {
		return tItovViewspotMapper.selectAll();
	}

	public List<TItovViewspotPicture> selectBylonlat_id(String lonlatId) {
		return tItovViewspotPictureMapper.selectBylonlat_id(lonlatId);
	}

	
	public List<TItovViewspot> selectByUID(String deviceuid) {
		return tItovViewspotMapper.selectByUID(deviceuid);
	}
	
	public   void deleteByPic_id(TItovViewspotPicture tItovViewspotPicture)
	{
		tItovViewspotPictureMapper.deleteByPic_id(tItovViewspotPicture);
	}
}
