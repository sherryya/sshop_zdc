package com.tmount.business.ptt.service;

import org.apache.log4j.Logger;

import com.tmount.util.Common;

public class CommonAnalyCanStreamOrder {
	static Logger logger = Logger.getLogger(CommonAnalyCanStreamOrder.class.getName());

	public static String getCanStatus(String order, String Msgtype) {
		String sec_arr[] = null;// 单个指令
		String ret = "-1";//失败
		
		if (Msgtype.equalsIgnoreCase("14")||Msgtype.equalsIgnoreCase("16")) {//锁车指令
			if (order.equalsIgnoreCase("2EA101015C")) {
				ret = "0";//锁车成功   最新的状态是已落锁
			} else if(order.equalsIgnoreCase("2EA101005D")){
				ret = "1";//锁车失败  最新的状态是未落锁
			}
			else
			{
				ret="2";//操作超时
			}
		} else {
			if (order.length() > 8)// 长度大于8的才有意义
			{
				sec_arr = Common.arr(order);
				Integer type = Integer.valueOf(order.substring(2, 4));
				switch (type) {
				case 41:// 车身信息
					if (Common.calcHex(sec_arr) == 1) {
						String h_41 = sec_arr[2];
						switch (Integer.valueOf(h_41)) {
						case 01://车门状态
							String b_41_01 = Common.hexString2binaryString(sec_arr[3]);
							if (b_41_01.length() == 8) {
								// 右后门 左后门 右前门 左前门  尾箱
								ret = (Integer.valueOf(b_41_01.substring(4, 5))) + "," + 
								(Integer.valueOf(b_41_01.substring(5, 6))) + "," + 
										(Integer.valueOf(b_41_01.substring(6, 7))) + "," + 
								(Integer.valueOf(b_41_01.substring(7, 8)))+","+(Integer.valueOf(b_41_01.substring(3, 4)));
							}
							break;
						case 04:// 车窗开度
							Integer data1_04 = Integer.valueOf(sec_arr[3], 16)/2;// 左前车窗开度
							Integer data2_04 = Integer.valueOf(sec_arr[4], 16)/2;// 右前车窗开度
							Integer data3_04 = Integer.valueOf(sec_arr[5], 16)/2;// 左后车窗开度
							Integer data4_04 = Integer.valueOf(sec_arr[6], 16)/2;// 右后车窗开度
							String data1 = getOpenDegreen(data1_04);
							if (data1.equalsIgnoreCase("0")) {
								data1 = "0";
							} else if (data1.equalsIgnoreCase("1")) {
								data1 = "左前车窗完全打开";
							} else {
								data1 = "左前车窗开度为:" + data1;
							}
							String data2 = getOpenDegreen(data2_04);
							if (data2.equalsIgnoreCase("0")) {
								data2 = "0";
							} else if (data2.equalsIgnoreCase("1")) {
								data2 = "右前车窗完全打开";
							} else {
								data2 = "右前车窗开度为:" + data2;
							}
							String data3 = getOpenDegreen(data3_04);
							if (data3.equalsIgnoreCase("0")) {
								data3 = "0";
							} else if (data3.equalsIgnoreCase("1")) {
								data3 = "左后车窗完全打开";
							} else {
								data3 = "左后车窗开度为:" + data3;
							}
							String data4 = getOpenDegreen(data4_04);

							if (data4.equalsIgnoreCase("0")) {
								data4 = "0";
							} else if (data4.equalsIgnoreCase("1")) {
								data4 = "右后车窗完全打开";
							} else {
								data4 = "右后车窗开度为:" + data4;
							}
							ret = data1 + "," + data2 + "," + data3 + "," + data4;
							break;
						default:
							break;
						}
					}
					break;
				default:
					break;
				}
			}
		}
		return ret;
	}
	private static String getOpenDegreen(int i) {
		if (i < 3)// 小于3认为完全关闭
		{
			return "0";
		}
		if (i >= 3 && i <= 10) {
			return "1/10";
		}
		if (i > 10 && i <= 20) {
			return "1/5";
		}
		if (i > 20 && i <= 30) {
			return "3/10";
		}
		if (i > 30 && i <= 40) {
			return "2/5";
		}
		if (i > 40 && i <= 50) {
			return "1/2";
		}
		if (i > 50 && i <= 60) {
			return "3/5";
		}
		if (i > 60 && i <= 70) {
			return "7/10";
		}
		if (i >= 7 && i <= 80) {
			return "4/5";
		}
		if (i > 80 && i <= 90) {
			return "9/10";
		}
		if (i > 90 && i <= 100) {
			return "1";
		}
		return "0";
	}
	
	
	
	

    //2E5107000000F10000F23B
	public static void getFaultCode(String order) {
		 String type="";//CAN类型
		 Integer len=0;//长度
		 Integer fault_type=0;//单元类型   0,1,2,3,4,5
		 Integer toal_len=0;//指令总长度
		 String fault="";//所有故障码
		 Integer times=0;//故障码个数
		 String sub_fault="";
         if(order.trim().length()>10)
         {
        	  type=order.trim().substring(2,4);
        	  if(type.equals("51"))
        	  {
        		  len=Integer.valueOf(order.trim().substring(4,6));
        		  toal_len=len*2+8;
        		  if(order.trim().length()==toal_len)
        		  {
        			  fault_type=Integer.valueOf(order.trim().substring(6,7));
        			  times=(len-1)/3;
                      fault=(order.trim().substring(8,8+2*(len-1)));
                      for(int i=0;i<times;i++)
                      {
                    	   sub_fault=fault.substring(i*6, 6*i+6);
                      }
        		  }
        	  }
         }
	}
	
	
	/* 16 进制字符串转换成十进制字符串 */
	private static int hexConver(String hexStr) {
		int result = Integer.valueOf(hexStr, 16);
		return result;
	}

	/**
	 * 得到发动机的转速等信息
	 * @param can
	 * @return
	 */
	public static String getCarStatus(String can) {
		String sec_arr[] = null;// 单个指令
		int Brake = -1;// 刹车状态
		int door = -1;// 车门状态
		int engine_Speed = -1;// 发动机转速
		int speed_instant = -1;// 瞬时速度
		int window = -1;// 车窗状态
		sec_arr = Common.arr(can);
		if (sec_arr.length > 10) {
			String b_41_07_01 = Common.hexString2binaryString(sec_arr[3]);
			if (b_41_07_01.length() == 8) {
				Brake = Integer.valueOf(b_41_07_01.substring(2, 3));// 刹车状态 0:放下  1 未放下
				if ((Integer.valueOf(b_41_07_01.substring(3, 4)) == 0) && (Integer.valueOf(b_41_07_01.substring(4, 5)) == 0) && (Integer.valueOf(b_41_07_01.substring(5, 6)) == 0) && (Integer.valueOf(b_41_07_01.substring(6, 7)) == 0) && (Integer.valueOf(b_41_07_01.substring(7, 8)) == 0)) {
					door = 0;// 车门全部关闭
				} else {
					door = 1;// 有车门未关闭
				}
			}
			double engine_Speed1 = Double.valueOf(hexConver(sec_arr[4]) * 256 + hexConver(sec_arr[5]));// 发动机转速
			if (engine_Speed1 > 0) {
				engine_Speed = 1;// 发动机转速大于0
			} else {
				engine_Speed = 0;// 发动机转速为0
			}
			double speed_instant1 = Double.valueOf(hexConver(sec_arr[6]) * 256 + hexConver(sec_arr[7])) * 0.01;// 瞬时速度
			if (speed_instant1 > 0) {
				speed_instant = 1;// 速度大于0
			} else {
				speed_instant = 0;// 速度为0
			}
			Integer data1_04 = Integer.valueOf(sec_arr[17], 16) / 2;// 左前车窗开度
			Integer data2_04 = Integer.valueOf(sec_arr[18], 16) / 2;// 右前车窗开度
			Integer data3_04 = Integer.valueOf(sec_arr[19], 16) / 2;// 左后车窗开度
			Integer data4_04 = Integer.valueOf(sec_arr[20], 16) / 2;// 右后车窗开度
			if ((data1_04 < 3) && (data2_04 < 3) && (data3_04 < 3) && (data4_04 < 3)) {
				window = 0;//车窗完全关闭
			} else {
				window = 1;//有车窗未关闭
			}
		}
		return Brake+","+door+","+engine_Speed+","+speed_instant+","+window;
	}
	public static void main(String[] args) {
		
	System.out.println(CommonAnalyCanStreamOrder.getCarStatus("2E4113070002BF0000056900C30010532C0000000001A4"));
	//	System.out.println("1234".substring(2,3));
	}
}
