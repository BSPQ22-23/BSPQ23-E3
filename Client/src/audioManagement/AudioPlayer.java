package audioManagement;


import java.io.File;

import javax.sound.sampled.*;




public class AudioPlayer {
	
	private static long clipTime;
	private static File soundfile;
	private static Clip clip;
	private static AudioInputStream ais;
	
	
	public AudioPlayer() {
		
	}
	
	
	
	public static void playNewAudioClip(String audioFile) {
		clipTime = 0;
		if(clip!=null && clip.isActive()) {
			clip.stop();
		}
		
		try {
			soundfile = new File(audioFile);
			clip = AudioSystem.getClip();
			ais = AudioSystem.getAudioInputStream(soundfile);
			clip.open(ais);
			clip.start();
		} catch(Exception exception) {
			System.out.println("Failed to play song");
			exception.printStackTrace();
		}
	}
	public static void stopAudioClip() {
		clipTime = clip.getMicrosecondPosition();
		clip.stop();
	}
	public static void resumeAudioClip() {
		clip.setMicrosecondPosition(clipTime);
		clip.start();
	}
	
	public static long getTime() {
		return clipTime;
	}
	public static Boolean playing() {
		if(clip != null) {
			return clip.isRunning();
		}
		return false;
		
	}
	public static Boolean active() {
		return clip.isActive();
	}
	
}
