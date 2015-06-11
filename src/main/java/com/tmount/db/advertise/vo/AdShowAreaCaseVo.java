package com.tmount.db.advertise.vo;

public class AdShowAreaCaseVo {
	private Integer showareaId;

    private String showareaName;
    
    private Long showId;

    private String showName;
    
    private Double showWidth;

    private Double showHigh;
    
    private Integer pageCount;
    
    private Integer flag ;	//查询橱窗所属橱窗页的个数，如果个数大于0，则为配置过样式。如果个数为0则没配置过样式。

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
		this.showareaName = showareaName;
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Double getShowWidth() {
		return showWidth;
	}

	public void setShowWidth(Double showWidth) {
		this.showWidth = showWidth;
	}

	public Double getShowHigh() {
		return showHigh;
	}

	public void setShowHigh(Double showHigh) {
		this.showHigh = showHigh;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
    
    
}
