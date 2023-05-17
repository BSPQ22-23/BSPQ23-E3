package audioManagement;


import java.io.File;

import javax.sound.sampled.*;




public class AudioPlayer {
	
	private static long clipTime;
	private static File soundfile;
	private static Clip clip;
	private static AudioInputStream ais;
	private static boolean playeando = false;
	
	
	public AudioPlayer() throws LineUnavailableException {
		
		
	}
	
	
	
	public static void playNewAudioClip(String audioFile, SongPlayer sp) {
		playeando = false;
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
			playeando = true;
			clip.addLineListener((LineListener) new LineListener() {
	            @Override
	            public void update(LineEvent event) {
	                if (event.getType() == LineEvent.Type.STOP&&playeando == true) {
	                    System.out.println("El clip ha terminado");
	                    sp.nextsong();
	                }
	            }
	        });
			
		} catch(Exception exception) {
			System.out.println("Failed to play song");
			exception.printStackTrace();
		}
	}
	
	public static void playNewAudioClip1(String audioFile) {
		playeando = false;
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
			playeando = true;
		}catch(Exception exception) {
			System.out.println("Failed to play song");
			exception.printStackTrace();
		}
	}
	
	public static void CloseClip() {
		if(clip == null) return;
		playeando = false;
		clipTime = 0;
		clip.close();
		try {
			ais.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void stopAudioClip() {
		if(clip == null) return;
		playeando = false;
		clipTime = clip.getMicrosecondPosition();
		clip.stop();
	}
	public static void resumeAudioClip() {
		playeando = true;
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
	public static Clip getClip() {
		return clip;
	}
}
