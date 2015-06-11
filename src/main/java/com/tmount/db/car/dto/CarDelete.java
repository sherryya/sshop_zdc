package com.tmount.db.car.dto;

public class CarDelete {
Integer account_id;   //车机端id
Integer car_id;
Integer user_id;
Long accoutn_id_tel;  //手机端端account_id
String carPlateNum;  //车牌号
String imei;   //车机imei
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public Integer getAccount_id() {
	return account_id;
}
public void setAccount_id(Integer account_id) {
	this.account_id = account_id;
}
public Integer getCar_id() {
	return car_id;
}
public void setCar_id(Integer car_id) {
	this.car_id = car_id;
}
public Long getAccoutn_id_tel() {
	return accoutn_id_tel;
}
public void setAccoutn_id_tel(Long accoutn_id_tel) {
	this.accoutn_id_tel= accoutn_id_tel;
}
public String getCarPlateNum() {
	return carPlateNum;
}
public void setCarPlateNum(String carPlateNum) {
	this.carPlateNum = carPlateNum;
}
public String getImei() {
	return imei;
}
public void setImei(String imei) {
	this.imei = imei;
}


}
