package com.tmount.db.manage.dto;

public class TItovMenuClass {
	private Integer classId;

	private String className;

	private Integer classParentid;

	private String classUrl;

	private Integer classLevels;

	private String classNote;

	private Integer classTaxis;

	private String target;

	private String rel;
    private Integer  leaf;
	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	private Integer role_id;
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassParentid() {
		return classParentid;
	}

	public void setClassParentid(Integer classParentid) {
		this.classParentid = classParentid;
	}

	public String getClassUrl() {
		return classUrl;
	}

	public void setClassUrl(String classUrl) {
		this.classUrl = classUrl;
	}

	public Integer getClassLevels() {
		return classLevels;
	}

	public void setClassLevels(Integer classLevels) {
		this.classLevels = classLevels;
	}

	public String getClassNote() {
		return classNote;
	}

	public void setClassNote(String classNote) {
		this.classNote = classNote;
	}

	public Integer getClassTaxis() {
		return classTaxis;
	}

	public void setClassTaxis(Integer classTaxis) {
		this.classTaxis = classTaxis;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
