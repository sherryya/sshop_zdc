package com.tmount.db.function.dto;

import java.util.Date;
import java.util.List;

public class AFunctionDict {
    private String functionCode;

    private String parentFunctionCode;

    private String sysCode;

    private String functionName;
    
		private  String parentFunctionName;

    private String pagePath;

    private String rel;

    private String target;

    private String createStaffId;

    private Date opDate;
    
    private List<AFunctionDict> childFunction = null;
    
    public AFunctionDict(){
  	}
     
  	public AFunctionDict(String functionCode, String functionName, String parentFunctionCode) {
	 		this.functionCode = functionCode;
	 		this.functionName = functionName;
	 		this.parentFunctionCode = parentFunctionCode;
		}

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode == null ? null : functionCode.trim();
    }

    public String getParentFunctionCode() {
        return parentFunctionCode;
    }

    public void setParentFunctionCode(String parentFunctionCode) {
        this.parentFunctionCode = parentFunctionCode == null ? null : parentFunctionCode.trim();
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }
    
    public String getParentFunctionName() {
        return parentFunctionName;
    }

    public void setParentFunctionName(String parentFunctionName) {
        this.parentFunctionName = parentFunctionName == null ? null : parentFunctionName.trim();
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath == null ? null : pagePath.trim();
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel == null ? null : rel.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(String createStaffId) {
        this.createStaffId = createStaffId == null ? null : createStaffId.trim();
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }
    public List<AFunctionDict> getChildFunction() {
			return childFunction;
		}
		public void setChildFunction(List<AFunctionDict> childFunction) {
			this.childFunction = childFunction;
		}
}