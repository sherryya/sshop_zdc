package com.tmount.db.car.dto;

import java.util.Date;


public class CarInfo {
	private Integer id;
	private String carname;//车辆名称
	private String carnum;//车辆牌号
	private String carlx;//车辆类型	
	private Date carsptime;//车辆上牌时间
	
	private Date carcctime;	//车辆出厂时间/
	private String firm	;//厂商
	private String models;//车型	
	private String fdjh	;//发动机号	
	private String cjh	;//车架号	
	private String pl	;//排量
	private String color;	//颜色
	private String zws	;//座位数	
	private String carbh;	//车辆编号	
	private String xsznum;	//行驶证号	
	private Date xszyxq	;//行驶证有效期	
	private String yyznum;	//运营证号	
	private int dept;	//部门id		
	private int groupId;	//车组id			
	private String jingweidu;	
	private int userid	;//车主id		
	private String pic	;//图片	
	private String huichuansj;	
	private String xingshisd;	
	private String xingshifx;	
	private String usergroup;//用户组Id	

	//itov
	private int car_id;
	private String car_name;//车辆名称
	private String car_plate_number;//车牌号
	private int car_type;//关联参数表		
	private String car_brand_date;//车辆上牌时间（YYYYMMDD）
	private String car_factory_date;//出厂时间（YYYYMMDD）
	private String car_engine_num;//发动机号	
	private String car_carcase_num;//车架号
	private String car_displacement;//排量
	private String car_color;
	private int car_sites;//座位数	
	private String car_driving_license;//行驶证号
	private String car_driving_license_date;//行驶证号期限
	private String car_operation_certificate;//运营证号
	
	private int wlid;	//	行车围栏id	
	private int qyid;	//企业id		
	private String DeviceUID;	///设备UID	
	private String DeviceType;	///设备类型	
	private String DeviceSN;	///设备序列号	
	private String DeviceTimeZone;///设备归属时区	
	

	
	private String car_brands;//车品牌
	private String car_model;//车型
	private String car_style;//车款
	
	private String access_id;//元征接口字段
	private String access_token;//元征接口字段
	private long account_id;//用户ID
	
	private String city_code;
	private String province_code;
	
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(long account_id) {
		this.account_id = account_id;
	}
	public String getAccess_id() {
		return access_id;
	}
	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getCar_plate_number() {
		return car_plate_number;
	}
	public void setCar_plate_number(String car_plate_number) {
		this.car_plate_number = car_plate_number;
	}
	public int getCar_type() {
		return car_type;
	}
	public void setCar_type(int car_type) {
		this.car_type = car_type;
	}
	public String getCar_brand_date() {
		return car_brand_date;
	}
	public void setCar_brand_date(String car_brand_date) {
		this.car_brand_date = car_brand_date;
	}
	public String getCar_factory_date() {
		return car_factory_date;
	}
	public void setCar_factory_date(String car_factory_date) {
		this.car_factory_date = car_factory_date;
	}
	public String getCar_engine_num() {
		return car_engine_num;
	}
	public void setCar_engine_num(String car_engine_num) {
		this.car_engine_num = car_engine_num;
	}
	public String getCar_carcase_num() {
		return car_carcase_num;
	}
	public void setCar_carcase_num(String car_carcase_num) {
		this.car_carcase_num = car_carcase_num;
	}
	public String getCar_displacement() {
		return car_displacement;
	}
	public void setCar_displacement(String car_displacement) {
		this.car_displacement = car_displacement;
	}
	public String getCar_color() {
		return car_color;
	}
	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}
	public int getCar_sites() {
		return car_sites;
	}
	public void setCar_sites(int car_sites) {
		this.car_sites = car_sites;
	}
	public String getCar_driving_license() {
		return car_driving_license;
	}
	public void setCar_driving_license(String car_driving_license) {
		this.car_driving_license = car_driving_license;
	}
	public String getCar_driving_license_date() {
		return car_driving_license_date;
	}
	public void setCar_driving_license_date(String car_driving_license_date) {
		this.car_driving_license_date = car_driving_license_date;
	}
	public String getCar_operation_certificate() {
		return car_operation_certificate;
	}
	public void setCar_operation_certificate(String car_operation_certificate) {
		this.car_operation_certificate = car_operation_certificate;
	}
	
	
	
	
	public String getCar_brands() {
		return car_brands;
	}
	public void setCar_brands(String car_brands) {
		this.car_brands = car_brands;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getCar_style() {
		return car_style;
	}
	public void setCar_style(String car_style) {
		this.car_style = car_style;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getCarlx() {
		return carlx;
	}
	public void setCarlx(String carlx) {
		this.carlx = carlx;
	}
	public Date getCarsptime() {
		return carsptime;
	}
	public void setCarsptime(Date carsptime) {
		this.carsptime = carsptime;
	}
	public Date getCarcctime() {
		return carcctime;
	}
	public void setCarcctime(Date carcctime) {
		this.carcctime = carcctime;
	}
	public String getFirm() {
		return firm;
	}
	public void setFirm(String firm) {
		this.firm = firm;
	}
	public String getModels() {
		return models;
	}
	public void setModels(String models) {
		this.models = models;
	}
	public String getFdjh() {
		return fdjh;
	}
	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}
	public String getCjh() {
		return cjh;
	}
	public void setCjh(String cjh) {
		this.cjh = cjh;
	}
	public String getPl() {
		return pl;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getZws() {
		return zws;
	}
	public void setZws(String zws) {
		this.zws = zws;
	}
	public String getCarbh() {
		return carbh;
	}
	public void setCarbh(String carbh) {
		this.carbh = carbh;
	}
	public String getXsznum() {
		return xsznum;
	}
	public void setXsznum(String xsznum) {
		this.xsznum = xsznum;
	}
	public Date getXszyxq() {
		return xszyxq;
	}
	public void setXszyxq(Date xszyxq) {
		this.xszyxq = xszyxq;
	}
	public String getYyznum() {
		return yyznum;
	}
	public void setYyznum(String yyznum) {
		this.yyznum = yyznum;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getJingweidu() {
		return jingweidu;
	}
	public void setJingweidu(String jingweidu) {
		this.jingweidu = jingweidu;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getHuichuansj() {
		return huichuansj;
	}
	public void setHuichuansj(String huichuansj) {
		this.huichuansj = huichuansj;
	}
	public String getXingshisd() {
		return xingshisd;
	}
	public void setXingshisd(String xingshisd) {
		this.xingshisd = xingshisd;
	}
	public String getXingshifx() {
		return xingshifx;
	}
	public void setXingshifx(String xingshifx) {
		this.xingshifx = xingshifx;
	}
	public int getWlid() {
		return wlid;
	}
	public void setWlid(int wlid) {
		this.wlid = wlid;
	}
	public int getQyid() {
		return qyid;
	}
	public void setQyid(int qyid) {
		this.qyid = qyid;
	}
	public String getDeviceUID() {
		return DeviceUID;
	}
	public void setDeviceUID(String deviceUID) {
		DeviceUID = deviceUID;
	}
	public String getDeviceType() {
		return DeviceType;
	}
	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}
	public String getDeviceSN() {
		return DeviceSN;
	}
	public void setDeviceSN(String deviceSN) {
		DeviceSN = deviceSN;
	}
	public String getDeviceTimeZone() {
		return DeviceTimeZone;
	}
	public void setDeviceTimeZone(String deviceTimeZone) {
		DeviceTimeZone = deviceTimeZone;
	}
	public String getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

}