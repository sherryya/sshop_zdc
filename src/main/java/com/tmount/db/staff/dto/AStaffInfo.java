package com.tmount.db.staff.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AStaffInfo {
    private Integer staffId;

    private Integer deptCode;

    private Integer companyId;

    private Long shopId;

    private String opNo;

    private String opPassword;

    private String staffName;

    private String idType;

    private String idNo;

    private String mobileNo;

    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date workDate;

    private String address;

    private String validFlag;

    private String staffPosition;

    private String staffDesc;

    private int createStaffId;

    private Date createTime;

    private String nickName;

    private Long userAvatar;
    
    private String deptName;
    
    private String companyName;
    
    private String shopName;

    private String roleName;
    
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOpNo() {
        return opNo;
    }

    public void setOpNo(String opNo) {
        this.opNo = opNo == null ? "空" : opNo.trim();
    }

    public String getOpPassword() {
        return opPassword;
    }

    public void setOpPassword(String opPassword) {
        this.opPassword = opPassword == null ? "空" : opPassword.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? "空" : staffName.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? "空" : idType.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? "空" : idNo.trim();
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? "空" : mobileNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "空" : email.trim();
    }
    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "空" : address.trim();
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag == null ? "空" : validFlag.trim();
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition == null ? "空" : staffPosition.trim();
    }

    public String getStaffDesc() {
        return staffDesc;
    }

    public void setStaffDesc(String staffDesc) {
        this.staffDesc = staffDesc == null ? "空" : staffDesc.trim();
    }

    public int getCreateStaffId() {
        return createStaffId;
    }

    public void setCreateStaffId(int createStaffId) {
        this.createStaffId = createStaffId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? "空" : nickName.trim();
    }

    public Long getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(Long userAvatar) {
        this.userAvatar = userAvatar;
    }

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}
}