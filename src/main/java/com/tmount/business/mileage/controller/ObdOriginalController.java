package com.tmount.business.mileage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.business.mileage.service.ZdcObdOriginalService;
import com.tmount.business.mileage.util.CommonUtil;
import com.tmount.business.mileage.util.OBDBean;
import com.tmount.db.mileage.dto.TZdcObdOriginal;
import com.tmount.exception.ShopException;
import com.tmount.util.ControllerBase;
import com.tmount.util.ParamData;
import com.tmount.util.RequestParam;
/**
 * 车机上传故障码到平台
 * 
 * @author
 * 
 */
@Controller
public class ObdOriginalController extends ControllerBase {
	Logger logger = Logger.getLogger(ObdOriginalController.class);
	@Autowired
	private ZdcObdOriginalService zdcObdOriginalService;
	@RequestMapping(value = "ObdOriginal.insert")
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sendData(request, response);
	}

	@Override
	protected void doService(RequestParam requestParam, JsonGenerator responseBodyJson) throws ShopException, JsonGenerationException, IOException {

		String deviceuid = ParamData.getString(requestParam.getBodyNode(), "deviceuid"); //设备id
		String obd_code = ParamData.getString(requestParam.getBodyNode(), "obd_code"); // 2E5107000000F10000F23B#2014-11-12
		TZdcObdOriginal obdOriginal = new TZdcObdOriginal();
		obdOriginal.setDeviceId(deviceuid);
		if(StringUtils.isNotBlank(obd_code))
		{
			//截取日期前的obc数据流
			obdOriginal.setObdCode(obd_code.substring(0, obd_code.lastIndexOf("#")));
			//截取的日期
			obdOriginal.setObdMakeDate(obd_code.substring(obd_code.lastIndexOf("#")+1,obd_code.length()));
			
		}
		//插入数据
		zdcObdOriginalService.insert(obdOriginal);
		//obd的数据流
		String obcode= obd_code.substring(0, obd_code.lastIndexOf("#"));
		String obs[] = obcode.split("#");
		List<OBDBean> list=new ArrayList<OBDBean>();
		for(String stream:obs)
		{
			String typeFlag = stream.substring(10, 12);  //标志着类型
	        int deci = Integer.parseInt(typeFlag, 16);   //十六进制转换为十进制
	        //03
	        if(deci==3)
	        {
	        	 
	        	 //第一个字节的判断
	        	 String binStr = CommonUtil.hexString2binaryString(stream.substring(12, 14));
	        	 char []chr = binStr.toCharArray();
	        	 for(int i=0;i<chr.length;i++)
	        	 {
	        		 OBDBean obd = new OBDBean();
	        		 obd.setType("03 燃料系统状态");  //设置名称及序号
	        		 if(i==3&&chr[i]==1)
	        		 {
	        			 obd.setStream("开环-不满足闭环条件");
	        			 list.add(obd);
	        		 }
	        		 if(i==4&&chr[i]==1)
	        		 {
	        			 obd.setStream("闭环-使用氧传感器作为燃油控制反馈");
	        			 list.add(obd);
	        		 }
	        		 if(i==5&&chr[i]==1)
	        		 {
	        			 obd.setStream("开环-驾驶原因");
	        			 list.add(obd);
	        		 }
	        		 if(i==6&&chr[i]==1)
	        		 {
	        			 obd.setStream("开环-检测到系统错误");
	        			 list.add(obd);
	        		 }
	        		 if(i==7&&chr[i]==1)
	        		 {
	        			 obd.setStream("闭环-至少检测到一个氧传感器错误");
	        			 list.add(obd);
	        		 }
	        	 }
	        	 //第二个字节的判断
	        	 String bin2Str = CommonUtil.hexString2binaryString(stream.substring(14, 16));
	        	 char []cr2 = bin2Str.toCharArray();
	        	 for(int i=0;i<cr2.length;i++)
	        	 {
	        		 OBDBean obd = new OBDBean();
	        		 obd.setType("03 燃料系统状态");  //设置名称及序号
	        		 if(i==3&&cr2[i]==1)
	        		 {
	        			 obd.setStream("开环-不满足闭环条件");
	        			 list.add(obd);
	        		 }
	        		 if(i==4&&cr2[i]==1)
	        		 {
	        			 obd.setStream("闭环-使用氧传感器作为燃油控制反馈");
	        			 list.add(obd);
	        		 }
	        		 if(i==5&&cr2[i]==1)
	        		 {
	        			 obd.setStream("开环-驾驶原因");
	        			 list.add(obd);
	        		 }
	        		 if(i==6&&cr2[i]==1)
	        		 {
	        			 obd.setStream("开环-检测到系统错误");
	        			 list.add(obd);
	        		 }
	        		 if(i==7&&cr2[i]==1)
	        		 {
	        			 obd.setStream("闭环-至少检测到一个氧传感器错误");
	        			 list.add(obd);
	        		 }
	        	 }
	        	
	        }
	        //04
	        if(deci==4)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = (result*100.0)/255;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("04 计算负载值");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("LOAD_PCT");
        		 list.add(obd);
            }
	        //05
	        if(deci==5)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 int dd = result-40;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("05 发动机冷却液温度");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("ECT");
        		 list.add(obd);
            }
	        //06
	        if(deci==6)
	        {
	        	 //第一个字节
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = ((double)result*100)/128;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("06短期燃油修正-气缸列1");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("SHRTFT1");
        		 list.add(obd);    
	        	
	        	//如果大于16则有第二个字节
	        	if(stream.length()>16)
	        	{
	        		 //第一个字节
		        	 int res = Integer.parseInt(stream.substring(14, 16), 16);  //十六进制转换为十进制
		        	 double temp = ((double)res*100)/128;  //根据公式计算的结果
		        	 OBDBean obd2 = new OBDBean();
	        		 obd2.setType("06短期燃油修正-气缸列3");  //设置名称及序号
	        		 obd2.setStream(String.valueOf(temp));	   
	        		 obd2.setExp("SHRTFT3");
	        		 list.add(obd2);    
	        	}
            }
	        //07
	        if(deci==7)
	        {
	        	 //第一个字节
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = ((double)result*100)/128;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("07长期燃油修正-气缸列1");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("LONGFT1");
        		 list.add(obd);    
	        	
	        	//如果大于16则有第二个字节
	        	if(stream.length()>16)
	        	{
	        		 //第一个字节
		        	 int res = Integer.parseInt(stream.substring(14, 16), 16);  //十六进制转换为十进制
		        	 int temp = (res*100)/128;  //根据公式计算的结果
		        	 OBDBean obd2 = new OBDBean();
	        		 obd2.setType("07长期燃油修正-气缸列3");  //设置名称及序号
	        		 obd2.setStream(String.valueOf(temp));	   
	        		 obd2.setExp("LONGFT3");
	        		 list.add(obd2);    
	        	}
            }
	        //10
	        if(deci==10)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 int dd = result*3;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("10油管压力");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("FRP");
        		 list.add(obd);
            }
	        //11
	        if(deci==11)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 int dd = result;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("11 进气歧管绝对压力");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("MAP");
        		 list.add(obd);
            }
	        //14
	        if(deci==14)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = (double)result/2-64;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("14 气缸1点火正时提前角");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("SPARKADV");
        		 list.add(obd);
            }
	        //15
	        if(deci==15)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 int dd = result-40;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("15 进气温度");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("IAT");
        		 list.add(obd);
            }
	        //17
	        if(deci==17)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd =((double)result*100.0)/255;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("17 节气门位置传感器");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("TP");
        		 list.add(obd);
            }
	        //19
	        if(deci==19)
	        {
	        	 //第一个字节的判断
	        	 String binStr = CommonUtil.hexString2binaryString(stream.substring(12, 14)); //十六进制转二进制
	        	 char []bitchar = binStr.toCharArray();
	        	 for(int i=0;i<bitchar.length;i++)
	        	 {
	        	   if(i==0&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列1传感器1");	   
	          		 obd.setExp("O2S11");
	          		 list.add(obd);
	        	   }
	        	   if(i==1&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列1传感器2");	   
	          		 obd.setExp("O2S12");
	          		 list.add(obd);
	        	   }
	        	   if(i==2&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列1传感器3");	   
	          		 obd.setExp("O2S13");
	          		 list.add(obd);
	        	   }
	        	   if(i==3&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列1传感器4");	   
	          		 obd.setExp("O2S14");
	          		 list.add(obd);
	        	   }
	        	   if(i==4&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列2传感器1");	   
	          		 obd.setExp("O2S21");
	          		 list.add(obd);
	        	   }
	        	   if(i==5&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列2传感器2");	   
	          		 obd.setExp("O2S22");
	          		 list.add(obd);
	        	   }
	        	   if(i==6&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列2传感器3");	   
	          		 obd.setExp("O2S23");
	          		 list.add(obd);
	        	   }
	        	   if(i==7&&bitchar[i]==1)
	        	   {
	        		 OBDBean obd = new OBDBean();
	          		 obd.setType("19 氧传感器位置");  //设置名称及序号
	          		 obd.setStream("气缸列2传感器4");	   
	          		 obd.setExp("O2S24");
	          		 list.add(obd);
	        	   }
	        	 }
            }
	        //20
	        if(deci==20)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd =(double)result*0.005;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("20 气缸列1传感器1状态");  //设置名称及序号
        		 obd.setStream("氧传感器输出电压"+String.valueOf(dd)+"V");	   
        		 obd.setExp("O2Sxy");
        		 list.add(obd);
        		 int result1 = Integer.parseInt(stream.substring(14, 16), 16);  //十六进制转换为十进制
        		 double dd1 =((double)result1*100)/128;  //根据公式计算的结果
	        	 OBDBean obd1 = new OBDBean();
        		 obd1.setType("20 气缸列1传感器1状态");  //设置名称及序号
        		 obd1.setStream("短时燃油修正"+String.valueOf(dd1));	   
        		 obd1.setExp("SHRTFTXY");
        		 list.add(obd1);
            }
	        //21
	        if(deci==21)
	        {
	        	 int result = Integer.parseInt(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd =(double)result*0.005;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("20 气缸列1传感器2状态");  //设置名称及序号
        		 obd.setStream("氧传感器输出电压"+String.valueOf(dd)+"V");	   
        		 obd.setExp("O2Sxy");
        		 list.add(obd);
        		 int result1 = Integer.parseInt(stream.substring(14, 16), 16);  //十六进制转换为十进制
        		 double dd1 =((double)result1*100)/128;  //根据公式计算的结果
	        	 OBDBean obd1 = new OBDBean();
        		 obd1.setType("20 气缸列1传感器2状态");  //设置名称及序号
        		 obd1.setStream("短时燃油修正"+String.valueOf(dd1));	   
        		 obd1.setExp("SHRTFTXY");
        		 list.add(obd1);
            }
	        //31 两个字节 低字节在前高字节在后
	        if(deci==31)
	        {
	        	 int result = Integer.parseInt(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 int dd =result;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("31 从发动机开始启动的时间");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd)+"(s)");	   
        		 obd.setExp("RUNTM");
        		 list.add(obd);
            }
	        //33两个字节 低字节在前高字节在后
	        if(deci==33)
	        {
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 long dd =result;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("33 故障指示灯点亮的行驶距离");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd)+"(km)");	   
        		 obd.setExp("MIL_DIST");
        		 list.add(obd);
            }
	        //35 两个字节 低字节在前高字节在后
	        if(deci==35)
	        {
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 long dd =result*100;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("35 汽油分供管相对油压");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd)+"(kPa)");	   
        		 obd.setExp("FRP");
        		 list.add(obd);
            }
	        //46
	        if(deci==46)
	        {
	        	 long result = Long.parseLong(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd =((double)result*100.0)/255;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("46 燃料蒸发净化命令");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("EVAP_PCT");
        		 list.add(obd);
            }
	        if(deci==48)
	        {
	        	 long result = Long.parseLong(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("48 故障码清除后的暖机次数");  //设置名称及序号
        		 obd.setStream(String.valueOf(result));	   
        		 obd.setExp("WARM_UPS");
        		 list.add(obd);
            }
	        //35 两个字节 低字节在前高字节在后
	        if(deci==49)
	        {
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 //long dd =result*100;  //根据公式计算的结果
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("49 故障码清除后的形式里程");  //设置名称及序号
        		 obd.setStream(String.valueOf(result));	   
        		 obd.setExp("CLR_DIST");
        		 list.add(obd);
            }
	       
	        if(deci==51)
	        {
	        	 long result = Long.parseLong(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("51 大气压");  //设置名称及序号
        		 obd.setStream(String.valueOf(result)+"(kpa)");	   
        		 obd.setExp("BARO");
        		 list.add(obd);
            }
	        if(deci==60)
	        {
	        	//低字节在前高字节在后
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = (result*0.1)-40;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("60 催化剂温度B1S1");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("CATEMP11");
        		 list.add(obd);
            }
	        //66 控制器电压
	        if(deci==66)
	        {
	        	//低字节在前高字节在后
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = result*0.001;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("66 控制器电压");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("VPWR");
        		 list.add(obd);
            }
	        //67 负载绝对值
	        if(deci==67)
	        {
	        	//低字节在前高字节在后
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = (result*100.0)/255;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("67 负载绝对值");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("LOAD_ABS");
        		 list.add(obd);
            }
	        //68 空气燃料当量比命令
	        if(deci==68)
	        {
	        	//低字节在前高字节在后
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd = result*0.0000305;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("68 空气燃料当量比命令");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("EQ_RAT");
        		 list.add(obd);
            }
	        if(deci==69)
	        {
	        	 long result = Long.parseLong(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd=result*100.0/255;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("69 节气门相对位置");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("TP_R");
        		 list.add(obd);
            }
	        if(deci==70)
	        {
	        	 long result = Long.parseLong(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 long dd=result-40;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("70 外界大气温度");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("AAT");
        		 list.add(obd);
            }
	        if(deci==71)
	        {
	        	 long result = Long.parseLong(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd=result*100.0/255;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("71 节气门绝对位置B");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("TP_B");
        		 list.add(obd);
            }
	        if(deci==72)
	        {
	        	 long result = Long.parseLong(stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 double dd=result*100.0/255;
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("72 节气门绝对位置C");  //设置名称及序号
        		 obd.setStream(String.valueOf(dd));	   
        		 obd.setExp("TP_C");
        		 list.add(obd);
            }
	        if(deci==78)
	        {
	        	 long result = Long.parseLong(stream.substring(14, 16)+stream.substring(12, 14), 16);  //十六进制转换为十进制
	        	 System.out.println("stream---->"+stream+"----->"+stream.substring(14, 16)+stream.substring(12, 14));
	        	 OBDBean obd = new OBDBean();
        		 obd.setType("78 故障码清除后时间");  //设置名称及序号
        		 obd.setStream(String.valueOf(result)+"(min)");	   
        		 obd.setExp("CLR_TIME");
        		 list.add(obd);
            }
	        responseBodyJson.writeFieldName("list");
	        ObjectMapper om = new ObjectMapper();
	        om.writeValue(responseBodyJson, list);
		} 
	
	}
}
