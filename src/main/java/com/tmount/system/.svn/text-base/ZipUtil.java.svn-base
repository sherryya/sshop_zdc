package com.tmount.system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class ZipUtil {

	/**
	 * 数据压缩方法
	 * 
	 * @return 返回压缩的数据流
	 * @throws IOException
	 */
	public static byte[] compress(byte[] rawData) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(rawData);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		if (bis != null) {
			byte[] buff = new byte[500]; // 定义一个500字节的缓冲区
			DeflaterOutputStream gzout = new DeflaterOutputStream(
					arrayOutputStream); // 数据压缩对象
			int len = 0;
			while ((len = bis.read(buff)) != -1) {
				gzout.write(buff, 0, len);
			}
			gzout.finish();
			gzout.close();
		}
		return arrayOutputStream.toByteArray();
	}

	/**
	 * 数据解压缩方法
	 * 
	 * @return 返回解压缩的数据流
	 * @throws IOException
	 */
	public static byte[] decompress(byte[] ripeData) throws IOException {
		if (ripeData != null) {
			int len = 0;
			byte[] buff = new byte[500];
			ByteArrayInputStream bin = new ByteArrayInputStream(ripeData);
			ripeData = null;
			InflaterInputStream gzipInput = new InflaterInputStream(bin);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((len = gzipInput.read(buff)) != -1) {
				out.write(buff, 0, len);
			}
			out.flush();
			out.close();
			gzipInput.close();
			return out.toByteArray();
		}
		return ripeData;

	}

	/**
	 * 数据解压缩方法
	 * 
	 * @return 返回解压缩的数据流
	 * @throws IOException
	 */
	public static final byte[] decompress(InputStream is, int streamLength) throws IOException {
		byte[] bufOut=new byte[streamLength];
		int offset = 0, readed = 0;
		while ((readed = is.read(bufOut, offset,streamLength - offset)) != -1) {
			offset += readed;
		}

		return decompress(bufOut);
	}

	/**
	 * 文件解压缩(未实现)
	 * 
	 * @param strRipeFile
	 * @param strRawFile
	 * @param nFlag
	 * @param strPwd
	 * @return
	 */
	public boolean decompressFile(String strRipeFile, String strRawFile,
			int nFlag, String strPwd) {
		return false;
	}

	/**
	 * 文件压缩(未实现)
	 * 
	 * @param strRawFile
	 * @param strRipeFile
	 * @param nFlag
	 * @param strPwd
	 * @return
	 */
	public boolean compressFile(String strRawFile, String strRipeFile,
			int nFlag, String strPwd) {
		return false;
	}

	public static void main(String[] args) {
		byte[] buffer;
		try {
			//buffer = compress("中国人！".getBytes());
			//System.out.println("Server: " + buffer);
			//System.out.println("after decompress = " + new String(decompress(buffer)));
			commpress1("F:\\1.png","e:\\2.png");
		} catch (Exception e) {

			System.out.println(e);

		}
	}
	
	public static  void commpress1(String fileName,String outpath) throws IOException
	{
		byte[] image;
		byte[] image1;
		byte[] image2;
		if (new File(fileName).exists()) {
			FileInputStream cInput = new FileInputStream(new File(fileName));
			image = new byte[cInput.available()];
			cInput.read(image);
			image1=compress(image);
			image2=decompress(image1);
			OutputStream out=new FileOutputStream(outpath); 
			out.write(image2, 0, image2.length); 
			out.flush(); 
			out.close(); 
			cInput.close();
		}
	}
	

}
