/**
 *
 */
package com.tmount.business.util.image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 
 * @version 
 * 
 */
public class ImageScalee {

	public void compressPics(String sourcename,int width,int height,String format,int screenWidth,int screenHeight) throws IOException
	{
		Image image = javax.imageio.ImageIO.read(new File(sourcename));
		int imageWidth = image.getWidth(null); 
		int imageHeight = image.getHeight(null); 
		float scale = this.getRatioWidth(imageWidth, width);
		imageWidth = (int)(scale*imageWidth); 
		imageHeight = (int)(scale*imageHeight); 
		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH ); 
		// Make a BufferedImage from the Image. 
		BufferedImage mBufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB); 
		Graphics2D g2 = mBufferedImage.createGraphics(); 
		g2.drawImage(image, 0, 0,imageWidth, imageHeight,Color.white, null); 
		g2.dispose(); 
		String tagetname = buildTargetFilename(sourcename,screenWidth,screenHeight,format);
		ImageIO.write(mBufferedImage,format, new File(tagetname));
	}

	public void deletePicsScale(String sourcename,String prefix,String format,int screenWidth,int screenHeight)
	{
		String tagetname = buildTargetFilenameScale(sourcename,prefix,screenWidth,screenHeight,format);
		/**
		 */
		File targetFile = new File(tagetname);
		if(targetFile.exists()){
			targetFile.delete();
		}
	}
	
	//
	public void compressPicsScale(String sourcename,String prefix,int width,int height,String format,int screenWidth,int screenHeight) throws IOException
	{
		Image image = javax.imageio.ImageIO.read(new File(sourcename));
		if (image == null)
			return;
		int imageWidth = image.getWidth(null); 
		int imageHeight = image.getHeight(null); 
		float scale = this.getRatioWidth(imageWidth, width);
//		imageWidth = (int)(scale*imageWidth); 
//		imageHeight = (int)(scale*imageHeight);
		if(scale<1)
		{
			imageWidth = width; 
			imageHeight = height;
		}
		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH ); 
		BufferedImage mBufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB ); 
		Graphics2D g2 = mBufferedImage.createGraphics(); 
		if(format.toLowerCase().equals("png"))
		{
			mBufferedImage = g2.getDeviceConfiguration().createCompatibleImage(imageWidth,imageHeight,Transparency.TRANSLUCENT);
			g2.dispose(); 
			g2 = mBufferedImage.createGraphics();
			g2.drawImage(image, 0, 0,imageWidth, imageHeight, null); 
		}
		//-----
		else
			g2.drawImage(image, 0, 0,imageWidth, imageHeight,Color.white, null); 
		g2.dispose(); 
		String tagetname = buildTargetFilenameScale(sourcename,prefix,screenWidth,screenHeight,format);
		/**
		 */
		File targetFile = new File(tagetname);
		if(targetFile.exists()){
			targetFile.delete();
		}
		ImageIO.write(mBufferedImage,format,targetFile );
	}
	//
	public void compressPicsScale(String sourcename,int width,int height,String format,int screenWidth,int screenHeight,String oriname) throws IOException
	{
		Image image = javax.imageio.ImageIO.read(new File(sourcename));
		int imageWidth = image.getWidth(null); 
		int imageHeight = image.getHeight(null); 
		float scale = this.getRatioWidth(imageWidth, width);
		imageWidth = (int)(scale*imageWidth); 
		imageHeight = (int)(scale*imageHeight);
//		imageWidth = width; 
//		imageHeight = height; 
		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH ); 
		BufferedImage mBufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB ); 
		Graphics2D g2 = mBufferedImage.createGraphics(); 
		if(format.toLowerCase().equals("png"))
		{
			mBufferedImage = g2.getDeviceConfiguration().createCompatibleImage(imageWidth,imageHeight,Transparency.TRANSLUCENT);
			g2.dispose(); 
			g2 = mBufferedImage.createGraphics();
			g2.drawImage(image, 0, 0,imageWidth, imageHeight, null); 
		}
		//-----
		else
			g2.drawImage(image, 0, 0,imageWidth, imageHeight,Color.white, null); 
		g2.dispose(); 
		String tagetname = buildTargetFilenameScale(oriname,screenWidth,screenHeight,format);
		ImageIO.write(mBufferedImage,format, new File(tagetname));
	}
	
	//not use
//	public void compressPics(File f,int width,int height,String format) throws IOException
//	{
//		Image image = javax.imageio.ImageIO.read(f);
//		int imageWidth = image.getWidth(null); 
//		int imageHeight = image.getHeight(null); 
//		float scale = this.getRatioWidth(imageWidth, width);
//		imageWidth = (int)(scale*imageWidth); 
//		imageHeight = (int)(scale*imageHeight); 
//		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH ); 
//		BufferedImage mBufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB ); 
//		Graphics2D g2 = mBufferedImage.createGraphics(); 
//		g2.drawImage(image, 0, 0,imageWidth, imageHeight,Color.white, null); 
//		g2.dispose(); 
//		String tagetname = buildTargetFilename(f.getName(),width,height,format);
//		ImageIO.write(mBufferedImage,format, new File(tagetname));
//	}
	//not use
//	public void compressPics(String sourcename,int width,int height,String format) throws IOException
//	{
//		Image image = javax.imageio.ImageIO.read(new File(sourcename));
//		int imageWidth = image.getWidth(null); 
//		int imageHeight = image.getHeight(null); 
//		float scale = this.getRatioWidth(imageWidth, width);
//		imageWidth = (int)(scale*imageWidth); 
//		imageHeight = (int)(scale*imageHeight); 
//		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH ); 
//		// Make a BufferedImage from the Image. 
//		BufferedImage mBufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB); 
//		Graphics2D g2 = mBufferedImage.createGraphics(); 
//		g2.drawImage(image, 0, 0,imageWidth, imageHeight,Color.white, null); 
//		g2.dispose(); 
//		String tagetname = buildTargetFilename(sourcename,width,height,format);
//		ImageIO.write(mBufferedImage,format, new File(tagetname));
//	}
	
	public void compressPicsHd(String sourcename,int width,int height,String format,int screenWidth,int screenHeight) throws IOException
	{
		Image image = javax.imageio.ImageIO.read(new File(sourcename));
		int imageWidth = image.getWidth(null); 
		int imageHeight = image.getHeight(null); 
		float scale = this.getRatio(imageWidth,imageHeight, width,height);
		imageWidth = (int)(scale*imageWidth); 
		imageHeight = (int)(scale*imageHeight); 
		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH ); 
		// Make a BufferedImage from the Image. 
		BufferedImage mBufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB); 
		Graphics2D g2 = mBufferedImage.createGraphics(); 
		g2.drawImage(image, 0, 0,imageWidth, imageHeight,Color.white, null); 
		g2.dispose(); 
		String tagetname = buildTargetFilename(sourcename,screenWidth,screenHeight,format);
		ImageIO.write(mBufferedImage,format, new File(tagetname));
	}
	
	public void compressPicsScaleHd(String sourcename,int width,int height,String format,int screenWidth,int screenHeight) throws IOException
	{
		Image image = javax.imageio.ImageIO.read(new File(sourcename));
		int imageWidth = image.getWidth(null); 
		int imageHeight = image.getHeight(null); 
		float scale = this.getRatio(imageWidth,imageHeight, width,height);
		imageWidth = (int)(scale*imageWidth); 
		imageHeight = (int)(scale*imageHeight);
//		if(scale<1)
//		{
//			imageWidth = width; 
//			imageHeight = height;
//		}
		image = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH ); 
		BufferedImage mBufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB ); 
		Graphics2D g2 = mBufferedImage.createGraphics(); 
		if(format.toLowerCase().equals("png"))
		{
			mBufferedImage = g2.getDeviceConfiguration().createCompatibleImage(imageWidth,imageHeight,Transparency.TRANSLUCENT);
			g2.dispose(); 
			g2 = mBufferedImage.createGraphics();
			g2.drawImage(image, 0, 0,imageWidth, imageHeight, null); 
		}
		//-----
		else
			g2.drawImage(image, 0, 0,imageWidth, imageHeight,Color.white, null); 
		g2.dispose(); 
		String tagetname = buildTargetFilenameScale(sourcename,screenWidth,screenHeight,format);
		ImageIO.write(mBufferedImage,format, new File(tagetname));
	}
	
	/**
	 */
	public static void main(String[] args) {
		try {
			String s = "D:/2.jpg";
			ImageScalee m = new ImageScalee();
			//m.compressPics(s, 200, 290, "jpg",220,320);
			m.compressPicsScale(s,s+"_80x80", 80, 80, "jpg",220,320);
//			m.compressPics(s, 220, 320, "jpg",220,320);
//			m.compressPics(s, 145, 210, "jpg",220,320);
		} catch (IOException e) {
			//add some operate
			System.out.println("error");
			e.printStackTrace();
		}
	}
	
	private String buildTargetFilename(String sourcename,int width,int height,String ext)
	{
		String name = sourcename.substring(0, sourcename.lastIndexOf("."));
		return name+"_"+width+"x"+height+"."+ext;
	}
	
	private String buildTargetFilenameScale(String sourcename,int width,int height,String ext)
	{
		String name = sourcename.substring(0, sourcename.lastIndexOf("."));
		return name+"-p_"+width+"x"+height+"."+ext;
	}
	
	private String buildTargetFilenameScale(String sourcename,String prefix,int width,int height,String ext)
	{
		String tmpSrc = sourcename.replace('\\', '/');

		String path = tmpSrc.substring(0, tmpSrc.lastIndexOf("/"));
		String name = tmpSrc.substring(tmpSrc.lastIndexOf("/")+1,tmpSrc.lastIndexOf("."));
		return path+"/"+prefix+"_"+name+"."+ext;
	}
	
	private float getRatio(int sourcewidth,int sourceheight,int targetWidth,int targetHeight){ 
	    float Ratio = 1.0f; 
	    float widthRatio ; 
	    float heightRatio ; 
	    
	    widthRatio = (float)targetWidth/sourcewidth; 
	    heightRatio = (float)targetHeight/sourceheight; 
	    if(widthRatio<1.0 || heightRatio<1.0){ 
	      Ratio = widthRatio<=heightRatio?widthRatio:heightRatio; 
	    } 
	    return Ratio; 
	} 
	
	private float getRatioWidth(int sourceWidth,int targetWidth)
	{ 
		float Ratio = 1.0f; 
		float widthRatio ; 
		widthRatio = (float)targetWidth/sourceWidth; 
		if(widthRatio<1.0)
		{ 
			Ratio = widthRatio; 
		} 
		return Ratio; 
	} 
}
