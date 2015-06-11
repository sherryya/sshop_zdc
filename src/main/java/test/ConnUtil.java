package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmount.bundle.ShopParamErrorBundle;
import com.tmount.exception.ShopParamException;
import com.tmount.system.MD5;
import com.tmount.system.ZipUtil;

public class ConnUtil {
	private static Logger logger = Logger.getLogger(ConnUtil.class);
 
	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
 
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 设置超时时间
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			// 建立实际的连接
			conn.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "---&gt;" + map.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
 
	/**
	 * 向指定URL发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */

	public static String sendPostString(String url, String param) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			//conn.setRequestProperty("Content-Type", "application/octet-stream"); 
			// 设置超时时间
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			//String requestParam = "{\"head\":{\"company_id\":1234,\"platform\":11,\"width\":640,\"height\":480,\"term_id\":\"abcdefgsdfs\",\"command_id\":1000,\"timestamp\":123456},\"body\":" + param + "}";
			String requestParam = "{\"head\":{\"appid\":101,\"platform\":11,\"width\":640,\"height\":480,\"term_id\":\"abcdefgsdfs\",\"command_id\":1000,\"timestamp\":123456,\"user_id\":1000},\"body\":" + param + "}";

			out.print(requestParam);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			
			String resutlUtf8="";
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					resutlUtf8 += "\n" + line;
				}
			result = new String(resutlUtf8.getBytes(),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	
	/**
	 * 向指定URL发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPostZip(String url, String param) {
		String result = "";
		OutputStream os = null;
		InputStream is = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/octet-stream;charset=UTF-8"); 
			// 设置超时时间
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			os=conn.getOutputStream();
			
			String sign = MD5.getMD5(param + "1qazxsw2");
			
			// 发送请求参数
			String requestParam = "{\"head\":{\"appid\":101,\"platform\":11,\"width\":640,\"height\":480,\"term_id\":\"abcdefgsdfs\",\"command_id\":1000,\"timestamp\":123456,\"user_id\":1000,\"tt\":\"测试中文\",\"ver\":\"1.0\",\"sign_m\":\"md5\",\"app_key\":\"sshop\",\"sign\":\""+sign+"\"},\"body\":" + param + "}";

			
			byte[] in = ZipUtil.compress(requestParam.toString().getBytes("UTF-8"));
			os.write(in);
			// flush输出流的缓冲
			os.flush();
			// 定义BufferedReader输入流来读取URL的响应
			
			int outLen = conn.getContentLength();
			is = conn.getInputStream();
			byte[] utf8Str = ZipUtil.decompress(is, outLen);
			result = new String(utf8Str,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendPostHead(String url, String param) {
		String result = "";
		OutputStream os = null;
		InputStream is = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性

			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/octet-stream;charset=UTF-8"); 
			// 设置超时时间
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			os=conn.getOutputStream();
			// 发送请求参数
			//String requestParam = "{\"head\":{\"company_id\":1234,\"platform\":11,\"width\":640,\"height\":480,\"term_id\":\"abcdefgsdfs\",\"command_id\":1000,\"timestamp\":123456},\"body\":" + param + "}";
			String requestParam = param;

			
			byte[] in = ZipUtil.compress(requestParam.toString().getBytes("UTF-8"));
			os.write(in);
			// flush输出流的缓冲
			os.flush();
			// 定义BufferedReader输入流来读取URL的响应
			
			int outLen = conn.getContentLength();
			is = conn.getInputStream();
			byte[] utf8Str = ZipUtil.decompress(is, outLen);
			result = new String(utf8Str,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (os != null) {
					os.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	private static void validRequestParam(String gbkStr) throws ShopParamException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(gbkStr);
			
	    	int bodyIndex  = gbkStr.indexOf("\"body\":{");
	    	if (bodyIndex == -1) {
	    		throw new ShopParamException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{"body"});
	    	}
	    	String bodyStr = gbkStr.substring(bodyIndex + 7,gbkStr.length() -1);	//只验证body里两个大括号中的内容，包括大括号。

	    	String signValid = MD5.getMD5(bodyStr + "1qazxsw2");
	    	JsonNode headNode = jsonNode.get("head");
	    	int errorCode = headNode.get("error_code").asInt();
	    	if (errorCode == 0) {
		    	String sign = headNode.get("sign").textValue();
		    	if (!sign.equals(signValid)) {
		    		throw new ShopParamException(ShopParamErrorBundle.INVALID_SIGN_PARAM, new Object[]{sign});
		    	}
	    	}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public static String sendPost(String url, String param) throws ShopParamException {
		//String jsonStr = sendPostString(url, param);
		String jsonStr = sendPostZip(url, param);
		validRequestParam(jsonStr);
		return jsonStr;
	}

	public static void ttUtf8() {
		String ch = "上地店";
		System.getProperties().list(System.out);
		try {
			String utf81 = new String(ch.getBytes("UTF-8"), "ISO-8859-1");
			String utf82 = new String(ch.getBytes("ISO-8859-1"), "GBK");
			System.out.println("ssss" + utf81);
			System.out.println("ssss3" + utf82);
			String chinese = new String(utf81.getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("ssss2" + chinese);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ShopParamException {
		
		
		System.out.println("获取设备信息列表="
				+ ConnUtil.sendPost("http://localhost:9080/sshop/devicelist.get", "{\"bar_code\":\"1\"}"));

		//获得图片：
		//http://localhost:8080/sshop/res?resid=110&width=40&pic_size_type=4x3
		
//		//1. 获取公司信息getCompanyInfo
//		System.out.println("获取公司信息=" + ConnUtil.sendPost("http://localhost:8080/sshop/company.get", "{\"company_id\":0,\"update_time\":\"2001-01-02 03:04:05\"}"));
//
//		//2. 获取公司的商品分类列表getShopItemsType
//		System.out.println("获取公司的商品分类列表=" + ConnUtil.sendPost("http://localhost:8080/sshop/shop.itemstype.get", "{\"shop_id\":106,\"data_type\":10}"));
//
//		//3.1 获取单公司某个商品分类下商品列表信息 ——全部获取 getItemFromType
//		System.out.println("单公司某个商品分类下商品列表信息(全部获取)="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.itemstype.items.get", "{\"shop_id\":100,\"sitems_type\":120}"));
//
//		//3.2 获取单公司某个商品分类下商品列表信息 ——分页 getItemFromType
//		System.out.println("单公司某个商品分类下商品列表信息(分页)="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.itemstype.items.get", "{\"shop_id\":106,\"sitems_type\":107,\"orders_time\":123456789012345,\"fetch_rows\":2}"));
//
//		//4 获取单个商品的明细信息getItemsInfo
//		System.out.println("获取单个商品的明细信息="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.items.get", "{\"items_id\":57}"));
//
		//4 获取单个商品的明细信息getItemsInfo
	/*	System.out.println("获取单个商品的明细信息="
				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.items.get", "{\"bar_code\":\"1\"}"));
*/
//		//5 获取用户余额信息getUserAccount
//		System.out.println("获取用户余额信息="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.account.get", "{\"user_id\":1000}"));
//
//		//6.1 得到商品下级商品列表（全部）。getSubItemFromItem
//		System.out.println("得到商品下级商品列表（全部）="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.items.subitems.get", "{\"items_id\":12}"));
//
//		//6.2 得到商品下级商品列表（部分）。getSubItemFromItem
//		System.out.println("得到商品下级商品列表（部分）="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.items.subitems.get", "{\"items_id\":12,\"orders_time\":1388505400000,\"fetch_rows\":2}"));
//
//		//7 发送单个订单。
//		System.out.println("订单ID="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.add", "{\"pay_way_id\":1, \"shop_id\":106,\"contact_name\":\"张三\",\"contact_phone\":\"1390000tmountx\", \"fee_date\":\"2014-01-01 02:03:04\",\"mark\":\"测试\",\"price\":33.9,\"item_list\":[{\"items_id\":22,\"acount\":2,\"price\":44,\"data_type\":10}]}"));
//
//		//7 发送批量订单。
//		System.out.println("订单ID="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.add", "{\"order_list\":[{\"pay_way_id\":1, \"shop_id\":106,\"contact_name\":\"张三\",\"contact_phone\":\"1390000tmountx\", \"fee_date\":\"2014-01-01 02:03:04\",\"mark\":\"测试\",\"price\":33.9,\"item_list\":[{\"items_id\":22,\"acount\":2,\"price\":44,\"data_type\":10}]}]}"));
//
//		//7 用户确认接收订单
//		System.out.println("用户确认接收订单="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.confirm.receipt", "{\"order_no\":76,\"mark\":\"张三\"}"));
//
//		//7 用户订单退货
//		System.out.println("用户订单退货="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.back", "{\"order_no\":59,\"mark\":\"张三\"}"));
//
//		//7 用户申请退货
//		System.out.println("用户申请退货="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.request.back", "{\"order_no\":59,\"mark\":\"张三\"}"));
//
//		//8 获取用户订单列表信息getUserOrder
//		System.out.println("获取用户订单列表信息="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.get", "{\"state\":10}"));
//
//		//8 获取用户订单列表信息
//		System.out.println("获取用户订单列表信息="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.orders.get", "{\"user_id\":1000,\"state\":10}"));
//
//		//9 获取用户订购商品详情。getUserOrderDetail
//		System.out.println("获取用户订购商品详情="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.detail.get", "{\"order_no\":56}"));
//
//		//10 取消订单。removeUserOrder
//		System.out.println("订单ID="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.delete", "{\"order_no\":70,\"mark\":\"取消\"}"));
//
//		//10 用户订单状态统计
//		System.out.println("用户订单状态统计="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.order.state.stat", "{\"user_id\":1000}"));
//
//		//11 获取酒店的楼层信息getShopFloor
//		System.out.println("获取酒店的楼层信息="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.floor.get", "{\"shop_id\":106}"));
//
//		//12 获取房间类商品明细getRoomInfo
//		System.out.println("获取房间类商品明细="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.items.room.get", "{\"items_id\":12}"));
//
//		//13 获取某商品的图片介绍列表getItemPics
//		System.out.println("获取某商品的图片介绍列表="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/shop.items.pics.get", "{\"items_id\":31}"));
//
//		//14 新用户注册。newUser
//		System.out.println("新用户注册="
//			+ ConnUtil.sendPost("http://localhost:8080/sshop/user.add", "{\"user_account\":\"aaaaa\", \"password\":\"123456\"}"));
//
//		//15 用户登录。userLogin
//		System.out.println("用户登录="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.login", "{\"user_account\":\"11\", \"password\":\"123456\"}"));
//
//		//15 用户退出登陆。userLogout
//		System.out.println("用户退出登录="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.logout", "{\"user_id\":11}"));
//
//		//16 用户密码变更
//		System.out.println("用户密码变更="
//				+ ConnUtil.sendPost("http://localhost:8080/sshop/user.password.change", "{\"user_id\":1000, \"new_password\":\"123456\", \"old_password\":\"123456\"}"));
//
//		//16. 平台目录信息(product.catalog.get)
//		System.out.println("平台目录信息=" + ConnUtil.sendPost("http://localhost:8080/sshop/product.catalog.get", "{\"company_id\":100}"));
//
//		//17. 获取取平台目录下商品列表信息(product.catalog.items.get)
//		System.out.println("获取取平台目录下商品列表信息=" + ConnUtil.sendPost("http://localhost:8080/sshop/product.catalog.items.get", "{\"items_type\":109, \"start_rows\":0, \"fetch_rows\":20, \"order_name\":\"price\", \"order_value\":\"desc\"}"));
//
//		//18. 获取商店活动列表getShopActvity
//		System.out.println("获取商店活动列表=" + ConnUtil.sendPost("http://localhost:8080/sshop/shop.activity.get", "{\"shop_id\":106,\"begin_time\":\"2010-01-01 00:00:00\"}"));
//
//		//19. 获取商店活动下的商品列表AcActionItemsInfo
//		System.out.println("获取商店活动下的商品列表=" + ConnUtil.sendPost("http://localhost:8080/sshop/shop.activity.items.get", "{\"actions_id\":100}"));
//
//		//20. 获取系统热词。
//		System.out.println("获取系统热词=" + ConnUtil.sendPost("http://localhost:8080/sshop/sys.hotwords.get", "{}"));
//
//		//21. 获取系统搜索词。
//		System.out.println("获取系统搜索词=" + ConnUtil.sendPost("http://localhost:8080/sshop/sys.searchwords.get", "{}"));
//
//		//22.商城商品检索
//		//System.out.println("resutl=" + ConnUtil.sendPost("http://localhost:8080/sshop/shop.platitems.get", "{\"serchStr\":106,\"keyword\":\"one\"}"));
//	
//		//23.商品详细信息获取
//		//System.out.println("resutl=" + ConnUtil.sendPost("http://localhost:8080/sshop/shop.itemsdetail.get", "{\"serchStr\":106,\"itemsId\":\"4\"}"));
//
//		//24.商店详细信息获取
//		//		System.out.println("resutl=" + ConnUtil.sendPost("http://localhost:8080/sshop/shop.get", "{\"serchStr\":106,\"shopId\":\"1\"}"));
//
//		//25.评论信息获取
//		//System.out.println("resutl=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.comment.get","{\"shopId\":1}"));
//
//		//26.统计当月指定商品，各个买家的购买情况。
//		System.out.println("统计当月指定商品，各个买家的购买情况。=" + ConnUtil.sendPost("http://localhost:8080/sshop/product.month.consumer.stat","{\"items_id\":22,\"fetch_rows\":3,\"start_rows\":0}"));
//
//		//27.得到用户对商品的评论情况，按照时间倒序排列。
//		System.out.println("得到用户对商品的评论情况，按照时间倒序排列=" + ConnUtil.sendPost("http://localhost:8080/sshop/product.user.comment.get","{\"items_id\":12,\"comment_level\":1,\"fetch_rows\":3,\"start_rows\":0}"));
//
//		//28.获取用户购物车中的信息。
//		System.out.println("获取用户购物车中的信息=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.cart.get","{\"user_id\":11}"));
//
//		//28.查询用户购物车中的商品数量。
//		System.out.println("查询用户购物车中的商品数量=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.cart.items.count.get","{\"user_id\":11}"));
//
//		//29.用户收藏商品添加。
//		System.out.println("用户收藏商品添加=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.favourite.items.add","{\"user_id\":11,\"shop_id\":106,\"items_id\":12,\"user_tag_id\":11,\"discount\":11}"));
//
//		//29.用户收藏商品查询。
//		System.out.println("用户收藏商品查询=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.favourite.items.get","{\"user_id\":11,\"shop_id\":106,\"items_id\":12}"));
//
//		//29.用户收藏商品删除。
//		System.out.println("用户收藏商品查询=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.favourite.items.delete","{\"user_id\":11,\"shop_id\":106,\"items_id\":12}"));
//
//		//29.1.用户收藏商店添加。
//		System.out.println("用户收藏商店添加=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.favourite.shop.add","{\"user_id\":22,\"shop_id\":100,\"user_tag_id\":11}"));
//
//		//30.用户加入购物车
//		System.out.println("用户加入购物车=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.cart.add","{\"item_list\":[{\"user_id\":11,\"shop_id\":11,\"items_id\":11,\"acount\":11,\"discount\":11},{\"user_id\":22,\"shop_id\":22,\"items_id\":22,\"acount\":22,\"discount\":22}]}"));
//
//		//31.用户移除购物车中商品
//		System.out.println("用户移除购物车中商品=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.cart.delete","{\"user_id\":22,\"shop_id\":22,\"items_id\":22,\"acount\":99}"));
//
//		//32.用户地址增加
//		System.out.println("用户地址增加=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.address.add","{\"user_id\":23,\"orders\":2,\"post_addr\":\"1\",\"province_code\":110000,\"city_code\":110000,\"area_code\":110000,\"address\":\"22\",\"post_code\":\"22\",\"content_phone\":\"22\",\"mobile\":\"22\",\"user_name\":\"22\",\"arrival_time\":1}"));
//		
//		//32.用户地址修改
//		System.out.println("用户地址修改=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.address.update","{\"user_id\":22,\"orders\":2,\"post_addr\":\"1\",\"province_code\":110000,\"city_code\":110000}"));
//		
//		//33.获取用户地址数据列表
//		System.out.println("获取用户地址数据列表=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.address.get","{\"user_id\":23,\"update_time\":\"2010-01-01 00:00:00\"}"));
//
//		//34.记录用户订单评价
//		System.out.println("记录用户订单评价=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.order.items.comment","{\"item_list\":[{\"user_id\":23,\"shop_id\":11,\"items_id\":12,\"comment_level\":1,\"items_desc\":1,\"delive_speed\":1,\"server_attitude\":1,\"goods_speed\":1,\"average_value\":1,\"comment_content\":\"sdf\",\"order_no\":26},{\"user_id\":23,\"shop_id\":11,\"items_id\":12,\"comment_level\":1,\"items_desc\":1,\"delive_speed\":1,\"server_attitude\":1,\"goods_speed\":1,\"average_value\":1,\"comment_content\":\"sdf\",\"order_no\":27}]}"));
//
//		//35.获取商店发票科目
//		System.out.println("获取商店发票科目=" + ConnUtil.sendPost("http://localhost:8080/sshop/shop.invoice.get","{\"shop_id\":11}"));
//
//		//36.用户订单付款
//		System.out.println("用户订单付款=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.order.pay","{\"order_no\":69,\"user_id\":22,\"pay_way_id\":1,\"mark\":\"paaa\",\"pay_type\":2,\"pay_money\":33.33,\"pay_evk_money\":0,\"pay_other_id\":\"AAA222\"}"));
//
//		//36.用户订单付款
//		System.out.println("用户订单付款=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.order.msg.pay","{\"order_no\":84,\"user_id\":102,\"pay_way_id\":1,\"mark\":\"paaa\",\"pay_type\":2,\"pay_money\":33.33,\"pay_evk_money\":0,\"pay_other_id\":\"AAA222\"}"));
//
//		//37.用户银行订单付款
//		System.out.println("用户银行订单付款_招商=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.bank.order.pay","{\"bank_accept\":25,\"succeed\":\"N\",\"coNo\":\"0\",\"amount\":0.0,\"date\":\"20121026\",\"msg\":\"00\",\"merchantPara\":\"0\",\"signature\":\"0\",\"baMessage\":\"0\",\"pay_type\":0,\"mark\":\"00\"}"));
//		System.out.println("用户银行订单付款_易宝=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.bank.order.pay","{\"bank_accept\":1000000002,\"succeed\":\"Y\",\"coNo\":null,\"amount\":null,\"date\":null,\"msg\":null,\"merchantPara\":null,\"signature\":null,\"baMessage\":\"p1_MerId=10001126856&r0_Cmd=Buy&r1_Code=1&r2_TrxId=09876543211234567890&r3_Amt=0.09&r4_Cur=CNY&r5_Pid=&r6_Order=1000000002&r7_Uid=&r8_MP=&r9_BType=1&rb_BankId=CMBCHINA-NET-B2C&ro_BankOrderId=123123&rp_PayDate=20091023153058&rq_CardNo=&ru_Trxtime=20091023153106&hmac=22f732141aa2493c806d6fcd411e227a\",\"pay_type\":0,\"mark\":\"\"}"));
//
//		//37.获取订单待评论信息
//		System.out.println("获取订单待评论信息=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.comment.log.get","{\"user_id\":1000}"));
//
//		//38.发送用户邮箱验证邮件
//		System.out.println("用户邮箱验证=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.mailverifycode.update","{\"user_id\":104,\"email\":\"wangzinan@126.com\"}"));
//		
//		//39.用户邮箱验证
//		System.out.println("用户邮箱验证=" + ConnUtil.sendPost("http://localhost:8080/sshop/user.mailverify.update","{\"user_id\":104,\"verify_code\":\"TozW93gea7AQvs4fdTes\"}"));
//		
//		//40.记录银行接口流水表 获取验证码
//		System.out.println("获取验证码_易宝"+ConnUtil.sendPost("http://localhost:8080/sshop/order.bank.accept.add","{\"user_id\":104,\"org_id\":2,\"price\":0.09,\"date\":\"20121115\",\"merchant_para\":\"\",\"merchant_url\":\"http://localhost:8080/JAVA/callback.jsp\",\"client_ip\":\"127.0.0.1\",\"order_list\":[{\"order_no\":1},{\"order_no\":2}],\"frp_id\":\"CMBCHINA-NET-B2C\"}"));
//
//		//41.查询客户端版本表
//		System.out.println("查询客户端版本表="+ConnUtil.sendPost("http://localhost:8080/sshop/sys.client.ver.get","{\"client_id\":14}"));
//
	}
}

