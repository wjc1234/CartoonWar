package com.neuedu.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImage  
* @Description: 获取图片的工具类
* @author neuedu_wjc
* @date 2019年8月17日 上午9:44:14
*/
public class GetImageUtil {
	//URL
	//获取图片的方法
	public static Image getImg(String imgName) {
		//反射
		URL resource = GetImageUtil.class.getClassLoader().getResource("com/neuedu/img/"+imgName);
		BufferedImage bufferedImage=null;
		try {
		 bufferedImage= ImageIO.read(resource);
		} catch (IOException e) {
		 
		e.printStackTrace();
		} 
		return bufferedImage ; 
}
	public static void main(String[] args) {
		GetImageUtil.getImg("feiji1.jpg");
	}
}