package com.neuedu.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImage  
* @Description: ��ȡͼƬ�Ĺ�����
* @author neuedu_wjc
* @date 2019��8��17�� ����9:44:14
*/
public class GetImageUtil {
	//URL
	//��ȡͼƬ�ķ���
	public static Image getImg(String imgName) {
		//����
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