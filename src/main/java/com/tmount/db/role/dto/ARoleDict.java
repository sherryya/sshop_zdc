package com.tmount.db.role.dto;

import java.util.Date;
import java.util.List;

import com.tmount.db.function.dto.AActionDict;

public class ARoleDict {
    private Integer roleCode;

    private Integer companyId;

    private Integer parentRoleCode;

    private String roleName;

    private Integer createLogin;

    private Date createDate;

    private String roleDesc;
    
    private String parentRoleName;
    
    private String companyName;
    
    private List<ARoleDict> childRole;
    
    private List<AActionDict> actionDictList;
    
    
    
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public ARoleDict()
    {
    }
    
    public ARoleDict(Integer roleCode, String roleName, Integer parentRoleCode){
  		this.roleCode = roleCode;
  		this.roleName = roleName;
  		this.parentRoleCode = parentRoleCode;
  	}

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getParentRoleCode() {
        return parentRoleCode;
    }

    public void setParentRoleCode(Integer parentRoleCode) {
        this.parentRoleCode = parentRoleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setParentRoleName(String parentRoleName) {
        this.parentRoleName = parentRoleName == null ? null : parentRoleName.trim();
    }
    
    public String getParentRoleName() {
        return parentRoleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getCreateLogin() {
        return createLogin;
    }

    public void setCreateLogin(Integer createLogin) {
        this.createLogin = createLogin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }
	public List<ARoleDict> getChildRole() {
		return childRole;
	}

	public void setChildRole(List<ARoleDict> childRole) {
		this.childRole = childRole;
	}

	public List<AActionDict> getActionDictList() {
		return actionDictList;
	}

	public void setActionDictList(List<AActionDict> actionDictList) {
		this.actionDictList = actionDictList;
	}
	
}