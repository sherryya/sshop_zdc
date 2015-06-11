package com.tmount.db.product.vo;

import java.util.List;

public class SyNormalDicsInfo {
	private String columnName;

    private List<SyNormalDicsList> syNormalDicsList;
    
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public List<SyNormalDicsList> getSyNormalDicsList() {
        return syNormalDicsList;
    }

    public void setSyNormalDicsList(List<SyNormalDicsList> syNormalDicsList) {
        this.syNormalDicsList = syNormalDicsList;
    }

}
