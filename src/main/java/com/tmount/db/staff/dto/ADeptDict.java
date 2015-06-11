package com.tmount.db.staff.dto;

import java.util.Date;
import java.util.List;


public class ADeptDict {
    private Integer deptCode;

    private Integer companyId;

    private String deptName;

    private Integer parentDeptCode;

    private String deptDesc;

    private String createStaffId;

    private Date opDate;
    
    private String parentDeptName;

    public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}

	public Integer getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Integer deptCode) {
        this.deptCode = deptCode;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getParentDeptCode() {
        return parentDeptCode;
    }

    public void setParentDeptCode(Integer parentDeptCode) {
        this.parentDeptCode = parentDeptCode;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc == null ? null : deptDesc.trim();
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
	public ADeptDict() {
	}

	public ADeptDict(int deptCode, String deptName, int parentDeptCode) {
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.parentDeptCode = parentDeptCode;
	}    
	private List<ADeptDict> childDept;

	public List<ADeptDict> getChildDept() {
		return childDept;
	}
	public void setChildDept(List<ADeptDict> childDept) {
		this.childDept = childDept;
	}	
	
}