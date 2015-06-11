package com.tmount.business.res.service;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tmount.bundle.ShopBusiErrorBundle;
import com.tmount.bundle.ShopParamErrorBundle;
import com.tmount.business.util.BusiPicHtmlUpload;
import com.tmount.config.AppPropertiesConfig;
import com.tmount.config.DataDictionary;
import com.tmount.config.StoreType;
import com.tmount.db.product.dao.GdPicResRelMapper;
import com.tmount.db.product.dao.GdResouceDicMapper;
import com.tmount.db.product.dao.SyResourceSizeDetailMapper;
import com.tmount.db.product.dao.SyResourceSizeDicMapper;
import com.tmount.db.product.dto.GdResouceDic;
import com.tmount.db.product.dto.SyResourceSizeDetail;
import com.tmount.db.sys.dao.SyClientVerMapper;
import com.tmount.db.sys.dto.SyClientVer;
import com.tmount.exception.ShopBusiException;
import com.tmount.system.dal.DataSourceContextHolder;

@Service
public class ResService {
	@Autowired
	AppPropertiesConfig appPropertiesConfig;

	@Autowired
	private GdResouceDicMapper gdResouceDicMapper;

	@Autowired
	private SyResourceSizeDicMapper syResourceSizeDicMapper;

	@Autowired
	private GdPicResRelMapper gdPicResRelMapper;

	@Autowired
	private SyResourceSizeDetailMapper syResourceSizeDetailMapper;
	
	@Autowired
	private SyClientVerMapper syClientVerMapper;
	

	/**
	 * 锟矫碉拷图片锟侥硷拷锟斤拷前缀锟斤拷
	 * @param clientWidth
	 * @param picSizeType
	 * @return
	 */
	public String getGdResoucePreChar(int clientWidth, String picSizeType){
		List<SyResourceSizeDetail> syResourceSizeDetailList = DataDictionary.getSyResourceSizeDetailList();
		Iterator<SyResourceSizeDetail> it = syResourceSizeDetailList.iterator();
		int oldClientWidth =0;
		String oldPreChar = "";
		while(it.hasNext()) {
			SyResourceSizeDetail syResourceSizeDetail = it.next();
			
			if (syResourceSizeDetail.getPicSizeType().equals(picSizeType)) {
				if ((oldClientWidth == 0) ||(Math.abs((clientWidth - syResourceSizeDetail.getPicWidth().intValue())) <= Math.abs(clientWidth - oldClientWidth))) {
					oldClientWidth = syResourceSizeDetail.getPicWidth().intValue();
					oldPreChar = syResourceSizeDetail.getPreChar();
				}
			}
		}
		
		return oldPreChar;
	}
	
	/**
	 * 图片资源请求
	 * @param request
	 * @param response
	 * @throws Exception
	 */
    public void resRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//锟斤拷锟斤拷锟斤拷源锟斤拷锟斤拷锟矫★拷
		DataSourceContextHolder.clearDataSource();

		Long resourceId = new Long(request.getParameter("resid"));//
		String picSizeType = request.getParameter("pic_size_type");//
		if (picSizeType == null) {
			picSizeType = "1x1";	//默认为1x1的图片。
		}
		Integer clientWidth = new Integer(0);
		if (request.getParameter("width") == null) {
			clientWidth = 0;
		} else {
			clientWidth = new Integer(request.getParameter("width"));
		}
		
		GdResouceDic gdResouceDic = gdResouceDicMapper.selectByPrimaryKey(resourceId);
		System.out.println("ResService:gdResouceDic OK");
		if (gdResouceDic == null) {
			response.sendRedirect("images/default.gif");
		} else {
			if (picSizeType.equals("xxy")) {
				StringBuilder picName = new StringBuilder();
				picName.append(appPropertiesConfig.getAppPropertiesConfig().getProperty("picUrlHome"));
				picName.append("/");
				picName.append(gdResouceDic.getResourcePath());
				picName.append("/");
				picName.append(gdResouceDic.getResourceName()).append(".");
				picName.append(gdResouceDic.getResFormat());
				String urlStr = picName.toString().replace('\\', '/');	//替换路径分割符号位斜线。
				System.out.println("ResService xxy:" + urlStr);
				response.sendRedirect(urlStr);
			} else {
				System.out.println("ResService:begin getGdResoucePreChar");
				String preChar = getGdResoucePreChar(clientWidth, picSizeType);
				if (preChar.equals("")) {
					response.sendRedirect("images/default.gif");
				} else {
					StringBuilder picName = new StringBuilder();
					picName.append(appPropertiesConfig.getAppPropertiesConfig().getProperty("picUrlHome"));
					picName.append("/");
					picName.append(gdResouceDic.getResourcePath());
					picName.append("/");
					picName.append(preChar).append("_");
					picName.append(gdResouceDic.getResourceName()).append(".");
					picName.append(gdResouceDic.getResFormat());
					String urlStr = picName.toString().replace('\\', '/');	//替换路径分割符号位斜线。
					System.out.println("ResService other:" + urlStr);
					response.sendRedirect(urlStr);
				}
			}
		}
	}

	/**
	 * 程序资源请求
	 * @param request
	 * @param response
	 * @throws Exception
	 */
    public void progResRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//清除当前线程数据源名称
		DataSourceContextHolder.clearDataSource();

		Integer clientId = new Integer(request.getParameter("client_id"));//客户端ID。
		
		SyClientVer syClientVer = syClientVerMapper.selectByPrimaryKey(clientId);
		
		if (syClientVer == null) {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_EXISTS_CLIENTID, new Object[]{clientId});
		} else {
			StringBuilder urlName = new StringBuilder();
			urlName.append(appPropertiesConfig.getAppPropertiesConfig().getProperty("progUrlHome"));
			urlName.append("/");
			urlName.append(syClientVer.getUpdateUrl());
			System.out.println("progResRequest:" + urlName.toString());
			response.sendRedirect(urlName.toString());
		}
   }
    
	/**
	 * 资源上传
	 * @param request
	 * @param response
	 * @throws Exception
	 */
    public void saveResUpload(HttpServletRequest request, HttpServletResponse response, CommonsMultipartFile picFile, ServletContext servletContext) throws Exception {
		DataSourceContextHolder.clearDataSource();

		Integer picSizeId = new Integer(request.getParameter("pic_size_id"));
		Long resourceId = new Long(request.getParameter("res_id"));
		
		String tableName = request.getParameter("table_name");
		if (tableName == null) {
			throw new ShopBusiException(ShopParamErrorBundle.LOST_IN_PARAM, new Object[]{"table_name"});
		}

		Long recordId = new Long(request.getParameter("record_id"));

		Integer storeType = new Integer(0);
		if (tableName.equals("gd_items_info")) {
			storeType = StoreType.itemsImage;
		} else if (tableName.equals("gd_shop_info")) {
			storeType = StoreType.shopImage;
		} else if (tableName.equals("us_user_info")) {
			storeType = StoreType.userImage;
		} else if (tableName.equals("ac_action_info")) {
			storeType = StoreType.actionImage;
		} else {
			throw new ShopBusiException(ShopBusiErrorBundle.NOT_SUPPERT_STORE_TYPE, new Object[]{tableName});
		}
		resourceId = BusiPicHtmlUpload.saveZipPic(appPropertiesConfig.getAppPropertiesConfig().getProperty("picHomePath")+"/", recordId, tableName, resourceId,
				picSizeId, storeType, picFile, servletContext,
				gdResouceDicMapper, syResourceSizeDicMapper, syResourceSizeDetailMapper, gdPicResRelMapper);

		String respJson = "{\"res_id\":" + resourceId + "}";
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(respJson.getBytes("UTF-8"));
		response.flushBuffer();
	}
}
