package com.tmount.db.integration.dao;
import java.util.List;
import com.tmount.db.integration.dto.CarBrand;
import com.tmount.db.integration.dto.CarModel;
import com.tmount.db.integration.dto.CarStyle;
import com.tmount.db.integration.dto.SoftVersion;
public interface CarBrandAddMapper {
   //添加品牌
   int  insert(CarBrand carBrand);
   
   public List<CarBrand> getCarBrand();
   
   public List<CarModel> getCarModel();
   
   public List<CarStyle> getCarStyle();
   
   public List<SoftVersion> getSoftVersion();
}