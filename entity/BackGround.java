package com.neuedu.entity;

import java.awt.Graphics;

import com.neuedu.action.ActionAble;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: 背景图片
* @Description: TODO(这里用一句话描述这个类的作用)
* @author neuedu_wjc
* @date 2019年8月21日 上午11:30:58
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
