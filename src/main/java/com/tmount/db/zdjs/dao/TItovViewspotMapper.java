package com.tmount.db.zdjs.dao;

import java.util.List;

import com.tmount.db.zdjs.dto.TItovViewspot;
public interface TItovViewspotMapper {
	void insert(TItovViewspot tItovViewspot);
    void updateByPrimaryKeySelective(TItovViewspot tItovViewspot);
    TItovViewspot selectByLonlat_id(String lonlatId);
    void deleteViewSpotByLonlatId(TItovViewspot tItovViewspot);
    List<TItovViewspot> selectAll();
    
    List<TItovViewspot> selectByUID(String deviceuid);
}