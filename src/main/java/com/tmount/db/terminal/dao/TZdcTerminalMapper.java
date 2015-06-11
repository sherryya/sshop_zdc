package com.tmount.db.terminal.dao;

import java.util.List;

import com.tmount.db.feedback.dto.TZdcFeedback;
import com.tmount.db.terminal.dto.TZdcTerminal;

public interface TZdcTerminalMapper {
	int insert(TZdcTerminal tZdcTerminal);
	List<TZdcTerminal> selectByWhere(TZdcTerminal tZdcTerminal);
    Integer selectSizeByWhere(TZdcTerminal tZdcTerminal);
	List<TZdcTerminal> selectTerminalById(int id);
	int updateTerminal(TZdcTerminal tZdcTerminal);
    Integer selectCountByStatus(int id);
    int delTerminal(int id);
	List<TZdcTerminal> selectBindingByWhere(TZdcTerminal tZdcTerminal);
    Integer selectBindingSizeByWhere(TZdcTerminal tZdcTerminal);
}
