package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

public class EnemyMouse extends Mouse implements ActionAble{
	
	private Integer enemyType;
	
	private Integer speed;
	
	private GameClient gc;
	
	
	
	public static Image[] imgs1 = {
			GetImageUtil.getImg("enemy/01.png"),
			GetImageUtil.getImg("enemy/02.png"),
			GetImageUtil.getImg("enemy/03.png"),
			GetImageUtil.getImg("enemy/04.png"),
			GetImageUtil.getImg("enemy/05.png")
	};
	
	public EnemyMouse() {
		
	}
	public EnemyMouse(int x,int y, int enemtType,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.enemyType = enemtType;
		this.speed = 3;
		this.gc = gc;
		this.isGood = isGood;
	}
	@Override
	public void move() {
		//super.move();
		x -= speed;
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count > 4) {
			count = 0;
		}
		g.drawImage(imgs1[count++],x,y,null);
		move();
		if(random.nextInt(500)>480) {
		      fire();
		     }
		//fire();
	}
	
	//随机数
	  Random random=new Random();
	//敌军发火
	public void fire() {
		Bullet bullet = new Bullet(x,y+imgs1[0].getHeight(null)/2+20,"bullet/dfzd1.png",gc,false);
		gc.bullets.add(bullet);
	}

	//获取到敌方的矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
	}
}
