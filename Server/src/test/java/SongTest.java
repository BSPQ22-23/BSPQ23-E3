import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import data.Song;

public class SongTest {
	Song song;
	
	@Before
	public void setUp() {
		song = new Song("Good life", "Graduation");
	}
	
    @Test
    public void testGetName() {
        assertEquals("Good life", song.getName());
    }

    @Test
    public void testGetAlbum() {
        assertEquals("Graduation", song.getAlbum());
    }

    @Test
    public void testSetAlbum() {
        song.setAlbum("Greatest Hits");
        assertEquals("Greatest Hits", song.getAlbum());
    }
}


