package test.java;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import main.java.audioManagement.AudioPlayer;

public class AudioPlayerTest {


    @After
    public void tearDown() throws Exception {
        AudioPlayer.stopAudioClip();
        AudioPlayer.CloseClip();
    }

    @Test
    public void testPlayNewAudioClip() throws InterruptedException {
        AudioPlayer.playNewAudioClip1("popipo.wav");
        Thread.sleep(100);
        assertTrue(AudioPlayer.playing());
        AudioPlayer.CloseClip();
    }

    @Test
    public void testStopAudioClip() throws InterruptedException {
        AudioPlayer.playNewAudioClip1("popipo.wav");
        Thread.sleep(100);
        AudioPlayer.stopAudioClip();
        Thread.sleep(100);
        assertFalse(AudioPlayer.playing());
        AudioPlayer.CloseClip();
    }

    @Test
    public void testResumeAudioClip() throws InterruptedException {
        AudioPlayer.playNewAudioClip1("popipo.wav");
        Thread.sleep(100);
        long clipTime = AudioPlayer.getTime();
        Thread.sleep(100);
        AudioPlayer.stopAudioClip();
        Thread.sleep(100);
        AudioPlayer.resumeAudioClip();
        Thread.sleep(100);
        
        System.out.println("Ahora");
        
        assertTrue(AudioPlayer.playing());
       
        assertEquals(clipTime >= 100 , true);
        AudioPlayer.CloseClip();
    }

    @Test
    public void testActive() throws InterruptedException {
        AudioPlayer.playNewAudioClip1("popipo.wav");
        Thread.sleep(100);
        assertTrue(AudioPlayer.active());
        AudioPlayer.CloseClip();
    }

    @Test
    public void testPlaying() throws InterruptedException {
    	AudioPlayer.playNewAudioClip1("popipo.wav");
    	AudioPlayer.stopAudioClip();
    	Thread.sleep(100);
        assertFalse(AudioPlayer.playing());
        AudioPlayer.CloseClip();
    }

    @Test
    public void testGetTime() throws InterruptedException {
        AudioPlayer.playNewAudioClip1("popipo.wav");
        Thread.sleep(100);
        long clipTime = AudioPlayer.getTime();
        assertTrue(clipTime >= 0);
        AudioPlayer.CloseClip();
    }

}