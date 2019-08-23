package com.neuedu.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.KeyAgreement;

import com.neuedu.constant.Constant;
import com.neuedu.entity.BackGround;
import com.neuedu.entity.Boom;
import com.neuedu.entity.Bullet;
import com.neuedu.entity.EnemyMouse;
import com.neuedu.entity.Mouse;
import com.neuedu.entity.Prop;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SoundPlayer;

/**
* @ClassName: GameClient
* @Description: 游戏客户端
* @author neuedu_wjc
* @date 2019年8月17日 上午9:19:29
*/
public class GameClient extends Frame{
	//创建一个mouse出来
	//Mouse mouse = new Mouse(100,200,"cr1.png",this,true);
	
	//创建一个我方角色的集合
	public List<Mouse> mouses = new ArrayList();
	
	//创建一个道具出来
	//Prop prop = new Prop(500,500,"Prop/addblood.png");
	
	//创建道具集合
	public List<Prop> props = new ArrayList<>();
	
	
	//创建一个子弹的集合
	public List<Bullet> bullets = new ArrayList<>();
	
	//创建一个背景图出来
	BackGround backImg = new BackGround(0,0,"bj2.jpg");
	
	
	//创建一个敌方1号出来
	//EnemyMouse enemy1 = new EnemyMouse(1500,50,1);
	//EnemyMouse enemy2 = new EnemyMouse(1500,200,1);
	//EnemyMouse enemy3 = new EnemyMouse(1500,350,1);
	
	//创建一个爆炸集合
	public List<Boom> booms = new ArrayList<>();
	
	//创建敌方集合
	  public List<Mouse> enemys=new ArrayList<>();
	
	  //解决图片闪烁的问题
	@Override
	public void update(Graphics g) {
		Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		Graphics backg = backImg.getGraphics();
		Color color = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
	Mouse mouse = null;

	//生成客户端窗口的方法
	public void launchFrame() {
		
		SoundPlayer soundPlayer = new SoundPlayer("/FJDZBJYY.mp3");
		soundPlayer.start();
		//设置窗口大小
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		
		//设置标题
		this.setTitle("卡通大作战");
		
		//设置是否能够显示
		this.setVisible(true);
		
		//禁止最大化
		this.setResizable(false);
		
		//窗口居中
		this.setLocationRelativeTo(null);
		
		//关闭窗口 添加关闭监听器
		this.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		});
		
		mouse = new Mouse(100,200,"cr1.png",this,true);
		//往我方容器中添加自己
		mouses.add(mouse);
		
		//添加鼠标监听
		
		this.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("你点了一下鼠标");
		}
		});
		
		//添加键盘监听
	this.addKeyListener(new KeyAdapter() {
		//键盘摁下的情况下
		@Override
		public void keyPressed(KeyEvent e) {
			mouse.keyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			mouse.keyReleased(e);
		}
		});
	
		//启动线程
		new paintThread().start();
		
		
		//往敌方容器中添加敌方
		EnemyMouse enemy1 = new EnemyMouse(1500,100,1,this,false);
		EnemyMouse enemy2 = new EnemyMouse(1500,300,1,this,false);
		EnemyMouse enemy3 = new EnemyMouse(1500,500,1,this,false);
		EnemyMouse enemy4 = new EnemyMouse(1500,700,1,this,false);
		EnemyMouse enemy5 = new EnemyMouse(1500,800,1,this,false);
		EnemyMouse enemy6 = new EnemyMouse(800,200,1,this,false);
		EnemyMouse enemy7 = new EnemyMouse(1200,400,1,this,false);
		EnemyMouse enemy8 = new EnemyMouse(1000,600,1,this,false);
		EnemyMouse enemy9 = new EnemyMouse(1200,800,1,this,false);

		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		enemys.add(enemy5);
		enemys.add(enemy6);
		enemys.add(enemy7);
		enemys.add(enemy8);
		enemys.add(enemy9);
	}
	
	//重写paint方法
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		for(int i = 0;i<mouses.size();i++) {
			Mouse mouse2 = mouses.get(i);
			mouse2.draw(g);
			mouse2.containItem(props);
		}
		
		//增强循环画出每个子弹
		//增强for循环不能做任何操作
		for (int i=0;i<bullets.size();i++) {
			  Bullet bullet=bullets.get(i);
			  bullet.draw(g);
			  bullet.hitMonsters(enemys);
			  bullet.hitMonsters(mouses);
			 }
			 
		g.drawString("当前子弹的数量："+bullets.size(), 10, 50);
		g.drawString("当前敌机的数量："+enemys.size(), 10, 80);
		g.drawString("当前爆炸的数量："+booms.size(), 10, 110);
		g.drawString("当前我方的数量："+mouses.size(), 10, 140);
		g.drawString("当前道具的数量："+props.size(), 10, 160);
		setForeground(Color.WHITE);
		//循环画敌方
		for(int i = 0;i<enemys.size();i++) {
			enemys.get(i).draw(g);
		}
		//循环爆炸
		for(int i = 0;i<booms.size();i++) {
			if(booms.get(i).isLive()==true) {
				booms.get(i).draw(g);
			}
			
		}
		//循环画道具
		for(int i = 0;i<props.size();i++) {
				props.get(i).draw(g);
		}
	}
	
	//内部类
	class paintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}