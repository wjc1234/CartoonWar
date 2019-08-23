package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;
/**
* @ClassName: 老鼠类
* @Description: TODO(这里用一句话描述这个类的作用)
* @author neuedu_wjc
* @date 2019年8月20日 下午3:14:25
*/
public class Mouse extends GameObj implements ActionAble{
	
	SinglePlay play = new SinglePlay();
	
	//速度
	private int speed;
	//方向布尔变量
	private boolean  left,up,right,down;
	//客户端拿过来
	public GameClient gc;
	
	//判断是否是我军还是敌军
	public boolean isGood;
	
	//添加飞机子弹等级变量
	public int bulletLevel = 1;
	
	//添加血值
	public int blood;
	
	public Mouse() {
		
	}
	public Mouse(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 20;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 100;
	}
	//移动的方法
	@Override
	public void move() {
		if(left){
			x-=speed;
		}
		if(up) {
			y-=speed;
		}
		if(right) {
			x+=speed;
		}
		if(down) {
			y+=speed;
		}
		outOfBound();
	}
	//画一个仓鼠出来
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		g.drawString("当前血量"+blood, 10, 200);
	}
	//处理仓鼠的边界问题
	public void outOfBound() {
		if(y<=30) {
			y=40;
		}
		if(x<=10) {
			x=5;
		}
		if(x>=Constant.GAME_WIDTH-img.getWidth(null)-50) {
			x = Constant.GAME_WIDTH-img.getWidth(null)-80;
		}
		if(y>=Constant.GAME_HEIGHT-img.getWidth(null)-20) {
			y = Constant.GAME_HEIGHT-img.getWidth(null)-20;
		}
	}
	
	//鼠标摁下
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_M:
			fire();
			break;
			default:
				break;
		}
		
	}
	
		
	//键盘释放
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
			default:
				break;
	}
	}

	//我方超人的开火
	protected void fire() {
		play.play("com/neuedu/sound/bangbang.mp3");
		Bullet b = new Bullet(x+100,y,"bullet/BULLET_CHAR_AVIS_0"+bulletLevel+".png",gc,true);
		//Bullet b = new Bullet(x+this.img.getWidth(null),y+this.img.getHeight(null),"bullet/BULLET_CHAR_AVIS_0"+bulletLevel+".png",gc,true);
		gc.bullets.add(b);
	}
	//获取到当前的矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
	//检测我方角色碰撞到道具
	public void containItem(Prop prop) {
		if(this.getRec().intersects(prop.getRect())) {
			//移除道具
			gc.props.remove(prop);
			if(bulletLevel>7) {
				bulletLevel = 8;
				return;
			}
			//更改子弹的等级
			this.bulletLevel +=1;
		}
	}
	//检测我方角色碰撞到多个道具
	public void containItem(List<Prop> props) {
		if(props==null) {
			return;
		}else {
			for(int i = 0;i<props.size();i++) {
				containItem(props.get(i));
			}
		}
	}
}