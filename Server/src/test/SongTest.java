package test;

import org.junit.Test;

import data.Song;

import static org.junit.Assert.*;

public class SongTest {

    @Test
    public void testGetName() {
        Song song = new Song("Good life", "Graduation");
        assertEquals("Good life", song.getName());
    }

    @Test
    public void testGetAlbum() {
        Song song = new Song("Good life", "Graduation");
        assertEquals("Graduation", song.getAlbum());
    }

    @Test
    public void testSetAlbum() {
        Song song = new Song("Good life", "Graduation");
        song.setAlbum("Greatest Hits");
        assertEquals("Greatest Hits", song.getAlbum());
    }
}


