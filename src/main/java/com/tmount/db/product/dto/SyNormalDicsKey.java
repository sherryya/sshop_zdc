package com.tmount.db.product.dto;

public class SyNormalDicsKey {
    private String columnName;

    private Integer dicValue;

    private String tableName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Integer getDicValue() {
        return dicValue;
    }

    public void setDicValue(Integer dicValue) {
        this.dicValue = dicValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }
}