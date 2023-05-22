package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.remoteConnection.HttpController;

public class HttpControllerTest {
	private String token;
	@Before
	public void startup() throws Exception {
		 HttpController.setService("127.0.0.1", 8000);
		 HttpController.sendFile("ds.wav", "WiWi", "LoLo", "user1");
		 HttpController.register("YO", "1234");
		 token = HttpController.login("YO", "1234");
	}
    @Test
    public void testSetService() throws IOException, URISyntaxException {
        HttpController.setService("127.0.0.1", 8000);
        String expected = "http://127.0.0.1:8000/";
        String actual = HttpController.getDestination();
        assertEquals(expected, actual);
    }


    @Test
    public void testSendFile() throws IOException, URISyntaxException, InterruptedException, ExecutionException {
        HttpResponse<String> response = HttpController.sendFile("ds.wav", "pop", "LoLo", token);
        int expected = 200;
        int actual = response.statusCode();
        assertEquals(expected, actual);
    }


    @Test
    public void testRecieveFile() throws IOException, URISyntaxException, InterruptedException, ExecutionException {
        
        HttpController.recieveFile("LoLo_ds.wav");
        File file = new File("LoLo_ds.wav");
        assertTrue(file.exists());
    }


    @Test
    public void testRecieveAvilableSongNames() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
        HttpController.setService("127.0.0.1", 8000);
        ArrayList<String> songNames = HttpController.recieveAvilableSongNames();
        assertTrue(songNames.contains("LoLo_ds.wav"));
    }
    
    @Test
    public void testRecievePlaylistSongs() throws URISyntaxException, InterruptedException, ExecutionException, IOException {

        ArrayList<String> playlistSongs = HttpController.recievePlaylistSongs("LoLo");
        assertTrue(playlistSongs.contains("LoLo_ds.wav"));
    }


    @Test
	public void testCreatePlaylist() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
		assertTrue(HttpController.createPlaylist("Help", token));
		assertFalse(HttpController.createPlaylist("Hello", token));
	}
    @Test
    public void deleteSongTest() throws URISyntaxException, InterruptedException, ExecutionException {
    	assertTrue(HttpController.deleteSong("LoLo_ds.wav", token));
    }
    @Test
    public void deletePlayList() throws URISyntaxException, InterruptedException, ExecutionException
    {
    	assertEquals("NaN", HttpController.deletePlaylist("Help", token));;
    }
    @After
	public void paterminar() throws Exception {
		HttpController.logout(token);
	}
}
