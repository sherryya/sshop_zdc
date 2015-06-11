package com.tmount.db.pub.dto;

public class DbSequence {
    private String seqName;
    private Long seqValue;
	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	public Long getSeqValue() {
		return seqValue;
	}
	public void setSeqValue(Long seqValue) {
		this.seqValue = seqValue;
	}
}