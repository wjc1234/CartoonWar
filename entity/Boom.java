package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: 爆炸效果
* @Description: TODO(这里用一句话描述这个类的作用)
* @author neuedu_wjc
* @date 2019年8月22日 上午8:10:18
*/
public class Boom extends GameObj implements ActionAble{
	
	private boolean isLive;
	private GameClient gc;
	
	public boolean isLive() {
		return isLive;
	}
	//动态初始化爆炸图
	private static Image[] boomImgs = new Image[5];
	static {
		for(int i = 0;i<5;i++) {
			System.out.println("Boom/explode0"+(i+1)+".png");
			boomImgs[i] = GetImageUtil.getImg("Boom/explode0"+(i+1)+".png");
		}
	}
		
	
	
	public Boom() {
		
	}
	public Boom(int x,int y) {
		this.x = x;
		this.y = y;
		this.isLive = true;
	}
	
	@Override
	public void move() {
		
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(isLive){
			g.drawImage(boomImgs[count++],x,y,null);
			if(count>4) {
				//count=0;
				isLive = false;
				//this.setLive(false);
				//gc.booms.remove(this);
				return;
			}
			System.out.println("画子弹..");
		}
		
		
	}

}
