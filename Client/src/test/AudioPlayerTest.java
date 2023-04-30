package test;

import static org.junit.Assert.*;

import javax.sound.sampled.Clip;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import audioManagement.AudioPlayer;

public class AudioPlayerTest {

    private AudioPlayer audioPlayer;

    @Before
    public void setUp() throws Exception {
        audioPlayer = new AudioPlayer();
    }

    @After
    public void tearDown() throws Exception {
        audioPlayer.stopAudioClip();
        audioPlayer = null;
    }

    @Test
    public void testPlayNewAudioClip() {
        audioPlayer.playNewAudioClip("ds.wav");
        assertTrue(audioPlayer.playing());
    }

    @Test
    public void testStopAudioClip() {
        audioPlayer.playNewAudioClip("ds.wav");
        audioPlayer.stopAudioClip();
        assertFalse(audioPlayer.playing());
    }

    @Test
    public void testResumeAudioClip() {
        audioPlayer.playNewAudioClip("ds.wav");
        long clipTime = audioPlayer.getTime();
        audioPlayer.stopAudioClip();
        audioPlayer.resumeAudioClip();
        assertEquals(clipTime, audioPlayer.getTime());
        assertTrue(audioPlayer.playing());
    }

    @Test
    public void testActive() {
        audioPlayer.playNewAudioClip("ds.wav");
        assertTrue(audioPlayer.active());
    }

    @Test
    public void testPlaying() {
        assertFalse(audioPlayer.playing());
    }

    @Test
    public void testGetTime() {
        audioPlayer.playNewAudioClip("ds.wav");
        long clipTime = audioPlayer.getTime();
        assertTrue(clipTime >= 0);
    }

}