package com.tmount.business.terminal.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.feedback.dao.TZdcFeedbackMapper;
import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.db.terminal.dao.TZdcTerminalMapper;
import com.tmount.db.terminal.dto.TZdcTerminal;

@Service
public class TermianlService {
	@Autowired
	private TZdcTerminalMapper tZdcTerminalMapper;
	public int insert(TZdcTerminal tZdcTerminal)
	{
		return tZdcTerminalMapper.insert(tZdcTerminal);
	}
	
	public List<TZdcTerminal> selectByWhere(TZdcTerminal tZdcTerminal)
	{
		return tZdcTerminalMapper.selectByWhere(tZdcTerminal);
	}
	
	public  Integer selectSizeByWhere(TZdcTerminal tZdcTerminal)
	{
		return tZdcTerminalMapper.selectSizeByWhere(tZdcTerminal);
	}
	public List<TZdcTerminal> selectTerminalById(int id)
	{
		return tZdcTerminalMapper.selectTerminalById(id);
	}
	public int updateTerminal(TZdcTerminal tZdcTerminal)
	{
		return tZdcTerminalMapper.updateTerminal(tZdcTerminal);
	}
	public  Integer selectCountByStatus(int id)
	{
		return tZdcTerminalMapper.selectCountByStatus(id);
	}
	public int delTerminal(int id)
	{
		return tZdcTerminalMapper.delTerminal(id);
	}
	public  Integer selectBindingSizeByWhere(TZdcTerminal tZdcTerminal)
	{
		return tZdcTerminalMapper.selectBindingSizeByWhere(tZdcTerminal);
	}
	public List<TZdcTerminal> selectBindingByWhere(TZdcTerminal tZdcTerminal)
	{
		return tZdcTerminalMapper.selectBindingByWhere(tZdcTerminal);
	}
}
