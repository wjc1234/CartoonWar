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
* @ClassName: ������
* @Description: TODO(������һ�仰��������������)
* @author neuedu_wjc
* @date 2019��8��20�� ����3:14:25
*/
public class Mouse extends GameObj implements ActionAble{
	
	SinglePlay play = new SinglePlay();
	
	//�ٶ�
	private int speed;
	//���򲼶�����
	private boolean  left,up,right,down;
	//�ͻ����ù���
	public GameClient gc;
	
	//�ж��Ƿ����Ҿ����ǵо�
	public boolean isGood;
	
	//��ӷɻ��ӵ��ȼ�����
	public int bulletLevel = 1;
	
	//���Ѫֵ
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
	//�ƶ��ķ���
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
	//��һ���������
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		g.drawString("��ǰѪ��"+blood, 10, 200);
	}
	//�������ı߽�����
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
	
	//�������
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
	
		
	//�����ͷ�
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

	//�ҷ����˵Ŀ���
	protected void fire() {
		play.play("com/neuedu/sound/bangbang.mp3");
		Bullet b = new Bullet(x+100,y,"bullet/BULLET_CHAR_AVIS_0"+bulletLevel+".png",gc,true);
		//Bullet b = new Bullet(x+this.img.getWidth(null),y+this.img.getHeight(null),"bullet/BULLET_CHAR_AVIS_0"+bulletLevel+".png",gc,true);
		gc.bullets.add(b);
	}
	//��ȡ����ǰ�ľ���
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
	//����ҷ���ɫ��ײ������
	public void containItem(Prop prop) {
		if(this.getRec().intersects(prop.getRect())) {
			//�Ƴ�����
			gc.props.remove(prop);
			if(bulletLevel>7) {
				bulletLevel = 8;
				return;
			}
			//�����ӵ��ĵȼ�
			this.bulletLevel +=1;
		}
	}
	//����ҷ���ɫ��ײ���������
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