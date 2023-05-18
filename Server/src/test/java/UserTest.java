

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.Playlist;
import data.User;

import java.util.HashMap;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("testuser", "testpassword");
    }

    @Test
    public void testGetUsername() {
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    public void testGetSpecificPLWithExistingPL() {
        user.addPlaylist("testplaylist");
        Playlist playlist = user.getSpecificPL("testplaylist");
        assertNotNull(playlist);
        assertEquals("testplaylist", playlist.getName());
    }

    @Test
    public void testGetSpecificPLWithNonExistingPL() {
        Playlist playlist = user.getSpecificPL("testplaylist");
        assertNotNull(playlist);
        assertEquals("testplaylist", playlist.getName());
    }

    @Test
    public void testAddPlaylist() {
        user.addPlaylist("testplaylist");
        assertTrue(user.getPlaylist().containsKey("testplaylist"));
    }

    @Test
    public void testGetPlaylist() {
        user.addPlaylist("testplaylist");
        HashMap<String, Playlist> playlistMap = user.getPlaylist();
        assertNotNull(playlistMap);
        assertEquals(1, playlistMap.size());
        assertTrue(playlistMap.containsKey("testplaylist"));
    }

    @Test
    public void testSetPlaylist() {
        HashMap<String, Playlist> playlistMap = new HashMap<String, Playlist>();
        playlistMap.put("testplaylist", new Playlist("testplaylist"));
        user.setPlaylist(playlistMap);
        assertEquals(1, user.getPlaylist().size());
        assertTrue(user.getPlaylist().containsKey("testplaylist"));
    }

    @Test
    public void testIsAdmin() {
        assertFalse(user.isAdmin());
    }

    @Test
    public void testSetAdmin() {
        user.setAdmin(true);
        assertTrue(user.isAdmin());
    }
}
