package com.neuedu.util;


import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: ���ű�������
* @Description: TODO(������һ�仰��������������)
* @author neuedu_wjc
* @date 2019��8��23�� ����10:05:53
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
