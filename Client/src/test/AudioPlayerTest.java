package test;

import static org.junit.Assert.*;

import javax.sound.sampled.Clip;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import audioManagement.AudioPlayer;

public class AudioPlayerTest {


    @After
    public void tearDown() throws Exception {
        AudioPlayer.stopAudioClip();
    }

    @Test
    public void testPlayNewAudioClip() {
        AudioPlayer.playNewAudioClip("ds.wav");
        assertTrue(AudioPlayer.playing());
    }

    @Test
    public void testStopAudioClip() {
        AudioPlayer.playNewAudioClip("ds.wav");
        AudioPlayer.stopAudioClip();
        assertFalse(AudioPlayer.playing());
    }

    @Test
    public void testResumeAudioClip() {
        AudioPlayer.playNewAudioClip("ds.wav");
        long clipTime = AudioPlayer.getTime();
        AudioPlayer.stopAudioClip();
        AudioPlayer.resumeAudioClip();
        assertEquals(clipTime, AudioPlayer.getTime());
        assertTrue(AudioPlayer.playing());
    }

    @Test
    public void testActive() {
        AudioPlayer.playNewAudioClip("ds.wav");
        assertTrue(AudioPlayer.active());
    }

    @Test
    public void testPlaying() {
        assertFalse(AudioPlayer.playing());
    }

    @Test
    public void testGetTime() {
        AudioPlayer.playNewAudioClip("ds.wav");
        long clipTime = AudioPlayer.getTime();
        assertTrue(clipTime >= 0);
    }

}