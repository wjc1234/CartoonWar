package com.neuedu.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class SinglePlay extends Thread{
	private String mp3Name;
	
	public SinglePlay() {
		
	}
	
	public SinglePlay(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	
	public void play(String mp3Name) {
		SinglePlay singlePlay = new SinglePlay(mp3Name);
		singlePlay.start();
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
