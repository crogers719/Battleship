/*
 * COSC 330: Battleship project
 * Colleen Rogers and Jon Gordy
 * March 8, 2016
 * 
 * Images.java
 * Description: This class handles the different sounds used througout the game 
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class Sounds extends Thread {
	// Sound Methods
	URL url;

	public void play(int soundID) {
		if (soundID==1){
			//Hit sound
			url=hit();
		}
		else if (soundID==2){
			//Game Begin Sound
			url=beginGame();
		}
		else if (soundID==3){
			//win game-applause
			url=winGame();
		}
		else if (soundID==4){
			//loose game-boo
			url=lostGame();
		}
		else if (soundID==5||soundID==6){
			//sunk ship-boom
			url=sunkShip();
		}
		else{
			//miss 
			url=miss();
		}
		
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	
	public URL hit(){
		return this.getClass().getClassLoader().getResource("soundFiles/bomb.wav");
	}
	public URL beginGame(){
		return this.getClass().getClassLoader().getResource("soundFiles/charge.wav");
	}
	public URL winGame(){
		return this.getClass().getClassLoader().getResource("soundFiles/applause.wav");
	}
	public URL lostGame(){
		return this.getClass().getClassLoader().getResource("soundFiles/boo.wav");
	}
	public URL sunkShip(){
		return this.getClass().getClassLoader().getResource("soundFiles/boom.wav");
	}
	public URL miss(){
		return this.getClass().getClassLoader().getResource("soundFiles/blip.wav");
	}

}