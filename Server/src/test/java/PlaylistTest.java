
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.Playlist;
import data.Song;

public class PlaylistTest {

	
	private Playlist playlist;
	
	@Before
	public void setUp() {
		playlist = new Playlist("My Playlist");
	}
	
	@Test
	public void testGetName() {
		assertEquals("My Playlist", playlist.getName());
	}
	
	@Test
	public void testAddSong() {
		Song song1 = new Song("Song 1", "Album 1");
		Song song2 = new Song("Song 2", "Album 2");
		
		playlist.addSong(song1);
		playlist.addSong(song2);
		
		assertEquals(2, playlist.getSongs().size());
		assertTrue(playlist.getSongs().contains(song1));
		assertTrue(playlist.getSongs().contains(song2));
	}
	
	@Test
	public void testRemoveSong() {
		Song song1 = new Song("Song 1", "Album 1");
		Song song2 = new Song("Song 2", "Album 2");
		
		playlist.addSong(song1);
		playlist.addSong(song2);
		
		playlist.removeSong("Song 1");
		
		assertEquals(1, playlist.getSongs().size());
		assertFalse(playlist.getSongs().contains(song1));
		assertTrue(playlist.getSongs().contains(song2));
	}
	
}
