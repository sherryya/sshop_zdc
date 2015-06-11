package com.tmount.db.advertise.dto;

public class AdShowareaDict {
    private Integer showareaId;

    private String showareaName;

    private String showareaType;

    private String showareaValid;

    private Integer byArea;

    public Integer getShowareaId() {
        return showareaId;
    }

    public void setShowareaId(Integer showareaId) {
        this.showareaId = showareaId;
    }

    public String getShowareaName() {
        return showareaName;
    }

    public void setShowareaName(String showareaName) {
        this.showareaName = showareaName == null ? null : showareaName.trim();
    }

    public String getShowareaType() {
        return showareaType;
    }

    public void setShowareaType(String showareaType) {
        this.showareaType = showareaType == null ? null : showareaType.trim();
    }

    public String getShowareaValid() {
        return showareaValid;
    }

    public void setShowareaValid(String showareaValid) {
        this.showareaValid = showareaValid == null ? null : showareaValid.trim();
    }

    public Integer getByArea() {
        return byArea;
    }

    public void setByArea(Integer byArea) {
        this.byArea = byArea;
    }
}