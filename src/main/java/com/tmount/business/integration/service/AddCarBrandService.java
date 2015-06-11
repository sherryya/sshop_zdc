package com.tmount.business.integration.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmount.db.integration.dao.CarBrandAddMapper;
import com.tmount.db.integration.dto.CarBrand;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.integration.dto.CarStyle;
import com.tmount.db.integration.dto.SoftVersion;

@Service
public class AddCarBrandService {
	@Autowired
	private CarBrandAddMapper carBrandAddMapper;
	public int insert(CarBrand carBrand){
		return carBrandAddMapper.insert(carBrand);
	}
	
	public List<CarBrand> getCarBrand()
	{
		return carBrandAddMapper.getCarBrand();
	}
	
	public List<CarModel> getCarModel()
	{
		return carBrandAddMapper.getCarModel();
	}
	
	public List<CarStyle> getCarStyle()
	{
		return carBrandAddMapper.getCarStyle();
	}
	
	public List<SoftVersion> getSoftVersion()
	{
		return carBrandAddMapper.getSoftVersion();
	}
}
