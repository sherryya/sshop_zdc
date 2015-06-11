package com.tmount.db.terminalAccount.dao;

import java.util.List;

import com.tmount.db.terminalAccount.dto.TZdcImeiterRelation;

public interface TZdcImeiterRelationMapper {

	int insert(TZdcImeiterRelation tZdcImeiterRelation);
	List<TZdcImeiterRelation> selectByIMEI(String imei);
	int updateByTerID(String terminal);
	int deleteByPrimaryKey(TZdcImeiterRelation tZdcImeiterRelation);
	List<TZdcImeiterRelation>  selectByTerminal(String terminal);
}
