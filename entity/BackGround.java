package com.neuedu.entity;

import java.awt.Graphics;

import com.neuedu.action.ActionAble;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: ����ͼƬ
* @Description: TODO(������һ�仰��������������)
* @author neuedu_wjc
* @date 2019��8��21�� ����11:30:58
*/
public class BackGround extends GameObj implements ActionAble{
	private Integer speed;
	public BackGround() {
		
	}
	public BackGround(int x,int y,String imgName) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 2;
	}
	@Override
	public void move() {
			x -= speed;
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y,null);
		move();
	}

}
