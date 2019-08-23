package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;

/**
* @ClassName: 子弹
* @Description: TODO(这里用一句话描述这个类的作用)
* @author neuedu_wjc
* @date 2019年8月21日 上午9:52:26
*/
public class Bullet extends GameObj implements ActionAble{
	
	//单次播放音乐的对象
	SinglePlay singleplay = new SinglePlay();
	
	//创建速度属性
	private Integer speed;
	
	//拿到客户端
	public GameClient gc;
	
	//子弹类型
	public boolean isGood;
	
	private Object boom;
	public Bullet() {
		
	}
	public Bullet(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 10;
		this.gc = gc;
		this.isGood = isGood;
	}
	@Override
	public void move() {
		if(isGood) {
			x +=speed;
		}else {
			x -= speed;
		}
		//x += speed;
		outOfBounds();
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}

	//子弹越界销毁
	public void outOfBounds() {
		if(x>Constant.GAME_WIDTH||x<0) {
			gc.bullets.remove(this);
		}
	}
	
	//随机生成道具
	Random random = new Random();
	
	//子弹打一个怪兽
	public boolean hitMonster(Mouse mouse) {
			
		if(this.getRec().intersects(mouse.getRec())&&this.isGood!=mouse.isGood) {
		//	Boom boom = new Boom(mouse.x,mouse.y);
			//移除打中敌人
			//gc.enemys.remove(mouse);
			
			//移除子弹
			//gc.bullets.remove(this);
			
			//添加爆炸
			//gc.booms.add(new Boom(mouse.x,mouse.y));
			//gc.booms.remove(boom);
			  //if(boom.isLive()==false) {
			  //gc.booms.add((Boom) boom);	
			
			if(mouse.isGood) {
				mouse.blood -=10;
				if(mouse.blood == 0) {
					//销毁自身
					gc.mouses.remove(mouse);
					//添加爆炸
					gc.booms.add(new Boom(mouse.x,mouse.y));
					gc.booms.remove(boom);
				}
				
			}else {
				singleplay.play("com/neuedu/sound/bang.mp3");
				Boom boom = new Boom(mouse.x,mouse.y);
				//移除打中敌人
				gc.enemys.remove(mouse);
				
				//移除子弹
				gc.bullets.remove(this);
				
				//随机生成一个道具出来
				if(random.nextInt(500)>350) {
					if(mouse instanceof EnemyMouse) {
						EnemyMouse enemyMouse = (EnemyMouse)mouse;
					}
					Prop prop = new Prop(mouse.x+EnemyMouse.imgs1[0].getWidth(null)/2,mouse.y+EnemyMouse.imgs1[0].getWidth(null)/2,"Prop/addblood.png");
					gc.props.add(prop);
				}
				
			}
			//添加爆炸
			gc.booms.add(new Boom(mouse.x,mouse.y));
			gc.booms.remove(boom);
			//}
			//移除爆炸
			gc.booms.remove(boom);
			//System.out.println("爆炸，，，，，");
			
			
			return true;
			
		}
		return false;
	}
	
	//子弹打多个怪物
	public boolean hitMonsters(List<Mouse> monsters) {
		for(int i = 0; i < monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	//获取到子弹的矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
}