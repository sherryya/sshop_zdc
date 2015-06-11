package com.tmount.business.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tmount.business.util.image.ImageScalee;
import com.tmount.config.AppPropertiesConfig;
import com.tmount.config.ResOrigenType;
import com.tmount.config.ResourceType;
import com.tmount.config.StoreType;
import com.tmount.db.product.dao.GdPicResRelMapper;
import com.tmount.db.product.dao.GdResouceDicMapper;
import com.tmount.db.product.dao.SyResourceSizeDetailMapper;
import com.tmount.db.product.dao.SyResourceSizeDicMapper;
import com.tmount.db.product.dto.GdPicResRel;
import com.tmount.db.product.dto.GdResouceDic;
import com.tmount.db.product.dto.SyResourceSizeDetail;
import com.tmount.db.sys.dto.SyClientVer;

/**
* 业务图片或HTML上传管理
 * @author lugz
 *
 */
public class BusiPicHtmlUpload {
	static private void getPicNameList(List<String> picNameList, String fileContentString) {
		int curImgTagPos = fileContentString.indexOf("<img", 0);
		int curSrcTagBeginPos = 0;
		int curSrcTagEndPos = 0;
		while(curImgTagPos != -1) {
			curSrcTagBeginPos = fileContentString.indexOf("src=\"", curImgTagPos+4);
			if (curSrcTagBeginPos == -1) {
				break;
			}
			curSrcTagEndPos = fileContentString.indexOf("\"", curSrcTagBeginPos+5);
			if (curSrcTagEndPos == -1) {
				break;
			}
			picNameList.add(fileContentString.substring(curSrcTagBeginPos+5, curSrcTagEndPos));
			curImgTagPos = fileContentString.indexOf("<img", curSrcTagEndPos);
		}
	}
	
	/**
	 * 按照指定的模板保存WEB文件。
	 * @param appPropertiesConfig
	 * @param modelFileName
	 * @param file
	 * @param description
	 * @throws Exception 
	 */
	static private void saveModelFile(String homePath, AppPropertiesConfig appPropertiesConfig, String modelFileName, File file, String description) throws Exception {
		String modelFileContentReplaceString =  appPropertiesConfig.getAppPropertiesConfig().getProperty("modelFileContentReplaceString");
		if (modelFileContentReplaceString == null) {
			modelFileContentReplaceString = "fileContentReplaceString";
		}

		String newContent = "";
		if (modelFileName != null) {
			String urlModel = appPropertiesConfig.getAppPropertiesConfig().getProperty(modelFileName);
			if (urlModel == null) {
				System.out.println("saveModelFile 查找模板文件错误!");
				throw new Exception("查找模板文件错误!");
			}
			StringBuffer modelFileContent = fileToStringBuffer(homePath + urlModel, null);
			try {
				newContent = modelFileContent.toString().replace(modelFileContentReplaceString, description);
			} catch(Exception e) {
				e.printStackTrace();
				throw new Exception("读取Html模板文件错误");
			}
		}
		
		FileOutputStream fos =null;
		try {
			fos =new FileOutputStream(file);
			byte[] byteContent = null;
			if (modelFileName == null) {
				byteContent = description.getBytes("UTF-8"); 
			} else {
				byteContent = newContent.getBytes("UTF-8"); 
			}
			fos.write(byteContent);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("写入HTML文件错误");
		}finally{
			if (fos != null) {
				fos.flush();
				fos.close();
			}
		}
	}
	
	/**
	 * 保存HTML文件，以webFileName文件为主，替换htmlFileName文件中的内容。
	 * @param htmlResId
	 * @param description
	 * @param servletContext
	 * @return
	 * @throws Exception 
	 */
	static public void saveHtmlFile(AppPropertiesConfig appPropertiesConfig, StringBuffer htmlFileName, StringBuffer webFileName, String homePath,
			Long recordId, String tableName,
			GdResouceDicMapper gdResouceDicMapper,
			SyResourceSizeDicMapper syResourceSizeDicMapper,
			GdPicResRelMapper gdPicResRelMapper,
			SyResourceSizeDetailMapper syResourceSizeDetailMapper,
			String description, ServletContext servletContext) throws Exception {
		
		if (description == null) {
			description = "";	//不存在空文件。
		}
		
		//生成文件路径
		String webFilePath = "data"+"/"+ResourceType.html+"/web/";
		File webPathFile = new File(homePath + webFilePath);
		webPathFile.mkdirs() ;

		String htmlFilePath = "data"+"/"+ResourceType.html+"/html/";
		File htmlPathFile = new File(homePath + htmlFilePath);
		htmlPathFile.mkdirs();

		if (webFileName.toString().equals("")) {
			long resId = gdResouceDicMapper.generateResId();
			webFileName.append(webFilePath + resId + ".html");
			htmlFileName.append(htmlFilePath + resId + ".html");
		}
		String editDescriptionFileName=webFileName.toString()+".edit";

		File webFile = new File(homePath+webFileName.toString());
		File htmlFile = new File(homePath+htmlFileName.toString());
		File editFile = new File(homePath+editDescriptionFileName.toString());

		String picFilePath = "data"+"/"+ResourceType.html+"/images/";	//图片路径
		new File(homePath + picFilePath).mkdirs();
		
		List<String> oldPicNameList = new LinkedList<String>();
		if(htmlFile.exists()){
			
			//得到原文件中的图片列表
			StringBuffer oldFileContent = fileToStringBuffer(homePath+htmlFileName, null);
			getPicNameList(oldPicNameList, oldFileContent.toString());

			//删除原HTML文件
			htmlFile.delete();
		}
		if(webFile.exists()){	
			//删除原HTML文件
			webFile.delete();
		}
		if(editFile.exists()){
			//删除原HTML文件
			editFile.delete();
		}

		//得到新文件中的图片列表。
		List<String> newPicNameList = new LinkedList<String>();
		getPicNameList(newPicNameList, description);

		//剔除没有变动的图片列表
		for(String newPicName: newPicNameList) {
			for(int i =0; i< oldPicNameList.size(); i ++) {
				if (oldPicNameList.get(i).equals(newPicName)) {
					oldPicNameList.remove(i);
					break;
				}
			}
		}

		//删除无用图片文件
		for(String oldPicName: oldPicNameList) {
			String fileName = oldPicName.substring(oldPicName.lastIndexOf("/")+1);
			String fileNameNoExt = oldPicName.substring(oldPicName.lastIndexOf("/")+1,oldPicName.lastIndexOf("."));
			File oldFile = new File(homePath+picFilePath+fileName);

			oldFile.delete();

			Long resId = new Long(fileNameNoExt);

			//删除资源图片关系
			GdPicResRel gdPicResRel = new GdPicResRel();
			gdPicResRel.setResId(resId);
			gdPicResRelMapper.deleteByResId(gdPicResRel);

			//删除资源表
			gdResouceDicMapper.deleteByPrimaryKey(resId);
		}

		//移动临时目录下的图片文件到标准目录。
		for(String newPicName: newPicNameList) {
			if (newPicName.indexOf("tmp/") != -1) {
				String fileNameNoExt = newPicName.substring(newPicName.lastIndexOf("/")+1,newPicName.lastIndexOf("."));
				String fileName = newPicName.substring(newPicName.lastIndexOf("/")+1);
				File oldFile = new File(homePath+"tmp/"+fileName);
				fileName = picFilePath + fileName; 
				File newFile = new File(homePath+fileName); 
				oldFile.renameTo(newFile);

				Long resId = new Long(fileNameNoExt);
				/**
				 * 保存资源记录
				 */
				GdResouceDic res = new GdResouceDic();
				res.setResId(resId);
				res.setResType(ResourceType.html);
				res.setResourceName(fileName.substring(fileName.lastIndexOf("/")+1, fileName.lastIndexOf(".")));
				try{
					res.setResSize((long)(new FileInputStream(new File(homePath+fileName)).available()));
				}catch(Exception e){
					e.printStackTrace();
				}
				res.setResFormat("jpg");
				res.setResourcePath(fileName.substring(0,fileName.lastIndexOf("/")));
				res.setState(1);
				res.setResUri(fileName);
				res.setStoreType(StoreType.htmlImages);
				res.setOrigenType(ResOrigenType.editUpload);
				gdResouceDicMapper.deleteByPrimaryKey(resId);
				gdResouceDicMapper.insert(res);
				
				/**
				 * 保存资源图片关系
				 */
				GdPicResRel resRel = new GdPicResRel();
				resRel.setTableName(tableName);
				resRel.setRecordId(recordId);
				resRel.setOrderNo(res.getResId().intValue());//无顺序
				resRel.setStoreType(StoreType.htmlImages);
				resRel.setPicSizeType("xxy");
				resRel.setResType(ResourceType.image);
				resRel.setResId(res.getResId());
				
				gdPicResRelMapper.deleteByPrimaryKey(resRel);
				gdPicResRelMapper.deleteByResId(resRel);
				gdPicResRelMapper.insert(resRel);
			}
		}

		//1. 替换就目录到新目录。
		//1.1 如果URL中存在mshop路径，把mshop和tmp一起替换了
		String newEditDescriptionMshop = description.replace("src=\"/mshop/tmp/", "src=\"/mshop/" + picFilePath);
		
		//1.2 如果URL中不存在mshop路径，值替换tmp就可以了
		String newEditDescription = newEditDescriptionMshop.replace("src=\"/tmp/", "src=\"/" + picFilePath);
		
		//2得到webPicUrlHome配置路径
		String webPicUrlHome = appPropertiesConfig.getAppPropertiesConfig().getProperty("webPicUrlHome");
		
		//3. 替换原目录到新的目标服务器webPicUrlHome目录。
		//3.1 如果存在在mshop和tmp路径，把mshop和tmp一起替换了
		String newDescriptionMshop = description.replace("src=\"/mshop/tmp/", "src=\"" + webPicUrlHome+"/" + picFilePath);

		//3.2 如果只存在tmp路径，只替换tmp路径
		String newDescription = newDescriptionMshop.replace("src=\"/tmp/", "src=\"" + webPicUrlHome+"/" + picFilePath);
		
		//3.3 修改后，如果只存在mshop目录，只替换mshop。
		String newDescriptionFinalMshop = newDescription.replace("src=\"/mshop", "src=\"" + webPicUrlHome);

		//3.4 修改后，如果不存在任何前缀目录，相当于直接添加HOME。
		String newDescriptionFinal = newDescriptionFinalMshop.replace("src=\"/", "src=\"/" + webPicUrlHome);


		//保存Web文件
		saveModelFile(homePath, appPropertiesConfig, "webUrlModel", webFile, newDescriptionFinal);

		//保存HTML文件
		saveModelFile(homePath, appPropertiesConfig, "htmlUrlModel", htmlFile, newDescriptionFinal);

		//保存编辑文本文件
		saveModelFile(homePath, appPropertiesConfig, null, editFile, newEditDescription);
	}
	
	/**
	 * 删除HTML文件。
	 * @param htmlFileName
	 * @param servletContext
	 * @throws IOException
	 */
	static public void removeHtmlFile(String homePath, String webUrl,
			GdResouceDicMapper gdResouceDicMapper,
			SyResourceSizeDicMapper syResourceSizeDicMapper,
			GdPicResRelMapper gdPicResRelMapper,
			SyResourceSizeDetailMapper syResourceSizeDetailMapper,
			ServletContext servletContext) throws IOException {
		if (webUrl.equals("")) {
			return;
		}
		String webFilePath = "data"+"/"+ResourceType.html+"/web/";
		String htmlFilePath = "data"+"/"+ResourceType.html+"/html/";

		Long resId = new Long(webUrl.substring(webUrl.lastIndexOf("/")+1, webUrl.lastIndexOf(".")));
		String webFileName = webFilePath + resId + ".html";
		String htmlFileName = htmlFilePath + resId + ".html";
		String editDescriptionFileName=webFileName.toString()+".edit";

		File webFile = new File(homePath+webFileName.toString());
		File htmlFile = new File(homePath+htmlFileName.toString());
		File editFile = new File(homePath+editDescriptionFileName.toString());

		List<String> oldPicNameList = new LinkedList<String>();
		if(htmlFile.exists()){
			
			//得到原文件中的图片列表
			StringBuffer oldFileContent = fileToStringBuffer(homePath+htmlFileName, null);
			getPicNameList(oldPicNameList, oldFileContent.toString());

			//删除原HTML文件
			htmlFile.delete();
		}
		if(webFile.exists()){	
			//删除原HTML文件
			webFile.delete();
		}
		if(editFile.exists()){
			//删除原HTML文件
			editFile.delete();
		}

		//删除无用图片文件
		String picFilePath = "data"+"/"+ResourceType.html+"/images/";	//图片路径
		for(String oldPicName: oldPicNameList) {
			String fileName = oldPicName.substring(oldPicName.lastIndexOf("/")+1);
			String fileNameNoExt = oldPicName.substring(oldPicName.lastIndexOf("/")+1,oldPicName.lastIndexOf("."));
			File oldFile = new File(homePath+picFilePath+fileName);

			oldFile.delete();

			Long picResId = new Long(fileNameNoExt);

			//删除资源图片关系
			GdPicResRel gdPicResRel = new GdPicResRel();
			gdPicResRel.setResId(picResId);
			gdPicResRelMapper.deleteByResId(gdPicResRel);

			//删除资源表
			gdResouceDicMapper.deleteByPrimaryKey(picResId);
		}
	}


	/**
	 * 保存图片成功返回，保存成功的resId
	 * @param recordId
	 * @param tableName
	 * @param pathId
	 * @param resId
	 * @param picSizeId
	 * @param smallFile
	 * @param servletContext
	 * @return
	 * @throws Exception 
	 */
	static public Long saveZipPic(String homePath, Long recordId, String tableName, Long resId, Integer picSizeId, Integer storeType,
			CommonsMultipartFile picFile, ServletContext servletContext,
			GdResouceDicMapper gdResouceDicMapper, SyResourceSizeDicMapper syResourceSizeDicMapper,
			SyResourceSizeDetailMapper syResourceSizeDetailMapper, GdPicResRelMapper gdPicResRelMapper) throws Exception {
		//如果上传了图片，则保存图片
		if (!picFile.isEmpty()) {
			if (resId == 0) {	//无原始图片
				resId = gdResouceDicMapper.generateResId();
			} else {
			
				//删除不属于本月路径的文件。
				GdResouceDic oldRes = gdResouceDicMapper.selectByPrimaryKey(resId);
				if (oldRes != null) {
					File file2 = new File(homePath + oldRes.getResUri());
					if(file2.exists()){
						file2.delete();
					}
				}
				ImageScalee m = new ImageScalee();
				List<SyResourceSizeDetail> sizeList = syResourceSizeDetailMapper.selectByPicSizeId(picSizeId);
				for(SyResourceSizeDetail sizeDetail : sizeList){
					//xxy:表示只有原图，无固定尺寸。
					if (!sizeDetail.getPicSizeType().equals("xxy")) {
						m.deletePicsScale(homePath+oldRes.getResUri(),sizeDetail.getPreChar(), "jpg",sizeDetail.getPicWidth(), sizeDetail.getPicHeight());
					}
				}
			}
			
			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			String yearMonth = calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH)+1);

			//文件的详细路径
			String filePath = "data"+"/"+ResourceType.image+"/"+storeType+"/"+yearMonth+"/";
			String fileName =filePath+resId+".jpg";
			File file = new File(homePath + fileName);

			//生成文件路径
			File pathFile = new File( homePath+filePath);
			pathFile.mkdirs() ;	      
		      
			/**
			 * 有原文件的话 删除 再重新生成
			 */
			if(file.exists()){
				file.delete();
			}
			picFile.getFileItem().write(file);
			
			/**
			 * 保存资源记录
			 */
			GdResouceDic res = new GdResouceDic();
			res.setResId(resId);
			res.setResType(ResourceType.image);
			res.setResourceName(fileName.substring(fileName.lastIndexOf("/")+1, fileName.lastIndexOf(".")));
			try{
				res.setResSize((long)(new FileInputStream(new File(homePath+fileName)).available()));
			}catch(Exception e){
				e.printStackTrace();
			}
			res.setResFormat("jpg");
			res.setResourcePath(fileName.substring(0,fileName.lastIndexOf("/")));
			res.setState(1);
			res.setResUri(fileName);
			res.setStoreType(storeType);
			res.setOrigenType(ResOrigenType.editUpload);
			gdResouceDicMapper.deleteByPrimaryKey(resId);
			gdResouceDicMapper.insert(res);
			
			/**
			 * 保存资源图片关系
			 */
			GdPicResRel resRel = new GdPicResRel();
			resRel.setTableName(tableName);
			resRel.setRecordId(recordId);
			resRel.setOrderNo(picSizeId);//无顺序
			resRel.setStoreType(storeType);//缩略图
			resRel.setPicSizeType(syResourceSizeDicMapper.selectByPrimaryKey(picSizeId).getPicSizeType());
			resRel.setResType(ResourceType.image);
			resRel.setResId(res.getResId());
			
			gdPicResRelMapper.deleteByPrimaryKey(resRel);
			gdPicResRelMapper.deleteByResId(resRel);
			gdPicResRelMapper.insert(resRel);
			
			/**
			 * 根据配置生成多个尺寸的缩略图
			 */
			ImageScalee m = new ImageScalee();
			List<SyResourceSizeDetail> sizeList = syResourceSizeDetailMapper.selectByPicSizeId(picSizeId);
			for(SyResourceSizeDetail sizeDetail : sizeList){
				if (!sizeDetail.getPicSizeType().equals("xxy")) {
					m.compressPicsScale(homePath+fileName,sizeDetail.getPreChar(), sizeDetail.getPicWidth(), sizeDetail.getPicHeight(), "jpg",sizeDetail.getPicWidth(), sizeDetail.getPicHeight());
				}
			}
		}
		return resId;
	}

	/**
	 * 删除图片
	 * @param recordId
	 * @param resUri
	 * @param resId
	 * @param picSizeId
	 * @param smallFile
	 * @param servletContext
	 * @param gdResouceDicMapper
	 * @param syResourceSizeDetailMapper
	 * @param gdPicResRelMapper
	 * @throws Exception
	 */
	static public void removeZipPic(String homePath, String resUri, Long resId, Integer picSizeId,	ServletContext servletContext,
			GdResouceDicMapper gdResouceDicMapper, SyResourceSizeDetailMapper syResourceSizeDetailMapper,
			GdPicResRelMapper gdPicResRelMapper) throws Exception {
		//删除多个尺寸的缩略图
		ImageScalee m = new ImageScalee();
		List<SyResourceSizeDetail> sizeList = syResourceSizeDetailMapper.selectByPicSizeId(picSizeId);
		for(SyResourceSizeDetail sizeDetail : sizeList){
			if (!sizeDetail.getPicSizeType().equals("xxy")) {
				m.deletePicsScale(homePath + resUri, sizeDetail.getPreChar(), "jpg",sizeDetail.getPicWidth(), sizeDetail.getPicHeight());
			}
		}
		
		//删除资源图片关系
		GdPicResRel gdPicResRel = new GdPicResRel();
		gdPicResRel.setResId(resId);
		gdPicResRelMapper.deleteByResId(gdPicResRel);

		//删除原图。
		File file = new File(homePath + resUri);
		if(file.exists()){
			file.delete();
		}

		//删除资源表
		gdResouceDicMapper.deleteByPrimaryKey(resId);
	}


	/**
	 * 获取头图路径列表
	 * @param itemsId
	 * @return
	 */
	static public List<String> getPicList(Long recordId,String tableName, int storeType, GdResouceDicMapper gdResouceDicMapper){
		GdPicResRel resRel = new GdPicResRel();
		resRel.setTableName(tableName);
		resRel.setRecordId(recordId);
		resRel.setStoreType(storeType);
		return gdResouceDicMapper.getResourceUrlByType(resRel);
	}

	/**
	 * 提取文件中的内容到StringBuffer中。
	 * @param strFilePath
	 * @param strCoding
	 * @return
	 * @throws IOException
	 */
	public static StringBuffer fileToStringBuffer(String strFilePath, String strCoding) throws IOException {
		StringBuffer strBuffResult = new StringBuffer();
		int i = 0;
		if (strCoding == null || strCoding.trim().length() <= 0) {
			strCoding = "UTF-8";
		}
		BufferedReader bufferedReader = null;
		try {
			if (strCoding == null || strCoding.trim().length() <= 0) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						new FileInputStream(strFilePath)));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader(
						new FileInputStream(strFilePath), strCoding));
			}
			while ((i = bufferedReader.read()) != -1) {
				strBuffResult.append((char) i);
			}
			bufferedReader.close();
		} finally {
			bufferedReader = null;
		}
		return strBuffResult;
	}

	/**
	 * 上传一个程序文件。
	 * @param syClientVer
	 * @param progFile
	 * @param servletContext
	 * @param updateUrlBuffer
	 * @throws Exception
	 */
	public static void uploadProgFile(SyClientVer syClientVer, CommonsMultipartFile progFile,
			ServletContext servletContext, StringBuffer updateUrlBuffer) throws Exception {
		if (!progFile.isEmpty()) {
			String homePath = servletContext.getRealPath("/").replace('\\', '/')+"/";
			File oldFile = new File(homePath + syClientVer.getUpdateUrl());
			if(oldFile.exists()){
				oldFile.delete();
			}
			
			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			String yearMonth = calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH)+1);

			//文件的详细路径
			String filePath = "data/progFile/"+syClientVer.getClientType()+"/"+yearMonth+"/";
			String fileName =filePath+syClientVer.getClientId() + "_" + progFile.getOriginalFilename();
			updateUrlBuffer.append(fileName);
			File file = new File(homePath + fileName);

			//生成文件路径
			File pathFile = new File( homePath+filePath);
			pathFile.mkdirs() ;
			
			/**
			 * 有原文件的话 删除 再重新生成
			 */
			if(file.exists()){
				file.delete();
			}
			progFile.getFileItem().write(file);
		}
	}

	/**
	 * 删除一个程序文件
	 * @param syClientVer
	 * @param servletContext
	 * @throws Exception
	 */
	public static void removeProgFile(SyClientVer syClientVer,
			ServletContext servletContext) throws Exception {
		if (syClientVer.getUpdateUrl() != null) {
			String homePath = servletContext.getRealPath("/").replace('\\', '/')+"/";
			File oldFile = new File(homePath + syClientVer.getUpdateUrl());
			if(oldFile.exists()){
				oldFile.delete();
			}
		}
	}
}
