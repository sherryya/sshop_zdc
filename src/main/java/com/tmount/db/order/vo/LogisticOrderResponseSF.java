package com.tmount.db.order.vo;

public class LogisticOrderResponseSF {
	private String mailno;
	
	private LogisticStepsResponseSF steps;
	
	public LogisticStepsResponseSF getSteps() {
		return steps;
	}

	public void setSteps(LogisticStepsResponseSF steps) {
		this.steps = steps;
	}
	
    public String getMailNo() {
        return mailno;
    }

    public void setMailNo(String mailNo) {
        this.mailno = mailNo == null ? null : mailNo.trim();
    }
}
