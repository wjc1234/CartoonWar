package com.neuedu.util;


import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: 播放背景音乐
* @Description: TODO(这里用一句话描述这个类的作用)
* @author neuedu_wjc
* @date 2019年8月23日 上午10:05:53
*/
public class SoundPlayer extends Thread{
	
	private String mp3Name;
	public SoundPlayer() {
			
		}
	public SoundPlayer (String mp3Name) {
		this.mp3Name = mp3Name;
	}
	
	@Override
	public void run() {
		System.out.println(mp3Name);
		InputStream resourceAsStream = Object.class.getResourceAsStream(mp3Name);
		for(;;) {
			System.out.println("music...");
			try {
				AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
				advancedPlayer.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
	}
}
