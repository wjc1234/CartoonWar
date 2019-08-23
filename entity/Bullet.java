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
* @ClassName: �ӵ�
* @Description: TODO(������һ�仰��������������)
* @author neuedu_wjc
* @date 2019��8��21�� ����9:52:26
*/
public class Bullet extends GameObj implements ActionAble{
	
	//���β������ֵĶ���
	SinglePlay singleplay = new SinglePlay();
	
	//�����ٶ�����
	private Integer speed;
	
	//�õ��ͻ���
	public GameClient gc;
	
	//�ӵ�����
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

	//�ӵ�Խ������
	public void outOfBounds() {
		if(x>Constant.GAME_WIDTH||x<0) {
			gc.bullets.remove(this);
		}
	}
	
	//������ɵ���
	Random random = new Random();
	
	//�ӵ���һ������
	public boolean hitMonster(Mouse mouse) {
			
		if(this.getRec().intersects(mouse.getRec())&&this.isGood!=mouse.isGood) {
		//	Boom boom = new Boom(mouse.x,mouse.y);
			//�Ƴ����е���
			//gc.enemys.remove(mouse);
			
			//�Ƴ��ӵ�
			//gc.bullets.remove(this);
			
			//��ӱ�ը
			//gc.booms.add(new Boom(mouse.x,mouse.y));
			//gc.booms.remove(boom);
			  //if(boom.isLive()==false) {
			  //gc.booms.add((Boom) boom);	
			
			if(mouse.isGood) {
				mouse.blood -=10;
				if(mouse.blood == 0) {
					//��������
					gc.mouses.remove(mouse);
					//��ӱ�ը
					gc.booms.add(new Boom(mouse.x,mouse.y));
					gc.booms.remove(boom);
				}
				
			}else {
				singleplay.play("com/neuedu/sound/bang.mp3");
				Boom boom = new Boom(mouse.x,mouse.y);
				//�Ƴ����е���
				gc.enemys.remove(mouse);
				
				//�Ƴ��ӵ�
				gc.bullets.remove(this);
				
				//�������һ�����߳���
				if(random.nextInt(500)>350) {
					if(mouse instanceof EnemyMouse) {
						EnemyMouse enemyMouse = (EnemyMouse)mouse;
					}
					Prop prop = new Prop(mouse.x+EnemyMouse.imgs1[0].getWidth(null)/2,mouse.y+EnemyMouse.imgs1[0].getWidth(null)/2,"Prop/addblood.png");
					gc.props.add(prop);
				}
				
			}
			//��ӱ�ը
			gc.booms.add(new Boom(mouse.x,mouse.y));
			gc.booms.remove(boom);
			//}
			//�Ƴ���ը
			gc.booms.remove(boom);
			//System.out.println("��ը����������");
			
			
			return true;
			
		}
		return false;
	}
	
	//�ӵ���������
	public boolean hitMonsters(List<Mouse> monsters) {
		for(int i = 0; i < monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	//��ȡ���ӵ��ľ���
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
}