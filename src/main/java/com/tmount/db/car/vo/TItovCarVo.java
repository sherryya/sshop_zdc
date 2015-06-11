package com.tmount.db.car.vo;

public class TItovCarVo {
	private String voipAccount;
	
	private String subAccountSid;
	
	private String subToken;
	
	private Long user_id;
	
	private Long carId;

	private String carName;

	private String carPlateNumber;

	private String carBrands;

	private String carColor;

	private String carStyle;

	private Long accountId;
	
	private String terminal_imei;
	
	private String terminal_deviceuid;
	
	private String terminal_name;
	
	private String city_code;   //城市代码
	
	private String province_code;  //省份代码
	
	private String car_carcase_num;  //车架号
	
	private String car_engine_num;  //发动机号
    private String url_itov;
    private String  is_default;   //是否是默认车辆
	private String car_driving_license_date;
	private String strategyType;  //热车策略类型
	private String strategyValue; // 热车策略值
	private int is_use;   //热车策略状态是否启用

	public String getCar_driving_license_date() {
		return car_driving_license_date;
	}

	public void setCar_driving_license_date(String car_driving_license_date) {
		this.car_driving_license_date = car_driving_license_date;
	}


	public String getUrl_itov() {
		return url_itov;
	}

	public void setUrl_itov(String url_itov) {
		this.url_itov = url_itov;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarPlateNumber() {
		return carPlateNumber;
	}

	public void setCarPlateNumber(String carPlateNumber) {
		this.carPlateNumber = carPlateNumber;
	}

	public String getCarBrands() {
		return carBrands;
	}

	public void setCarBrands(String carBrands) {
		this.carBrands = carBrands;
	}


	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getTerminal_imei() {
		return terminal_imei;
	}

	public void setTerminal_imei(String terminal_imei) {
		this.terminal_imei = terminal_imei;
	}

	public String getTerminal_deviceuid() {
		return terminal_deviceuid;
	}

	public void setTerminal_deviceuid(String terminal_deviceuid) {
		this.terminal_deviceuid = terminal_deviceuid;
	}

	public String getTerminal_name() {
		return terminal_name;
	}

	public void setTerminal_name(String terminal_name) {
		this.terminal_name = terminal_name;
	}

	public String getVoipAccount() {
		return voipAccount;
	}

	public void setVoipAccount(String voipAccount) {
		this.voipAccount = voipAccount;
	}

	public String getSubAccountSid() {
		return subAccountSid;
	}

	public void setSubAccountSid(String subAccountSid) {
		this.subAccountSid = subAccountSid;
	}

	public String getSubToken() {
		return subToken;
	}

	public void setSubToken(String subToken) {
		this.subToken = subToken;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

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

	public String getCar_carcase_num() {
		return car_carcase_num;
	}

	public void setCar_carcase_num(String car_carcase_num) {
		this.car_carcase_num = car_carcase_num;
	}

	public String getCar_engine_num() {
		return car_engine_num;
	}

	public void setCar_engine_num(String car_engine_num) {
		this.car_engine_num = car_engine_num;
	}

	public String getIs_default() {
		return is_default;
	}

	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}

	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}

	public String getStrategyValue() {
		return strategyValue;
	}

	public void setStrategyValue(String strategyValue) {
		this.strategyValue = strategyValue;
	}

	public int getIs_use() {
		return is_use;
	}

	public void setIs_use(int is_use) {
		this.is_use = is_use;
	}
	
	

}
