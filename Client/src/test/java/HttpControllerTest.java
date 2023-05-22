package test.java;

import static org.junit.Assert.assertEquals;
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
	public void testSetServiceANDstartupAndSendFile() throws Exception {
		HttpController.setService("127.0.0.1", 8000);
        String expected = "http://127.0.0.1:8000/";
        String actual = HttpController.getDestination();
        assertEquals(expected, actual);
        Thread.sleep(1000);
		HttpController.register("YO", "1234");
		token = HttpController.login("YO", "1234");
		Thread.sleep(1000);
		HttpResponse<String> response = HttpController.sendFile("src/main/resources/popipo.wav", "pop", "LoLo", token);
	    int expected2 = 200;
	    int actual2 = response.statusCode();
	    assertEquals(expected2, actual2);
	    Thread.sleep(1000);
	}
	
    @Test
    public void testRecieveFile() throws IOException, URISyntaxException, InterruptedException, ExecutionException {
        HttpController.recieveFile("LoLo_popipo.wav");
        File file = new File("audios/LoLo_popipo.wav");
        assertTrue(file.exists());
        Thread.sleep(1000);
    }

    @Test
    public void testRecieveAvilableSongNames() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
        HttpController.setService("127.0.0.1", 8000);
        ArrayList<String> songNames = HttpController.recieveAvilableSongNames();
        assertTrue(songNames.contains("LoLo_popipo.wav"));
        Thread.sleep(1000);
    }
    
    @Test
    public void testRecievePlaylistSongs() throws URISyntaxException, InterruptedException, ExecutionException, IOException {

        ArrayList<String> playlistSongs = HttpController.recievePlaylistSongs("LoLo");
        assertTrue(playlistSongs.contains("LoLo_popipo.wav"));
        Thread.sleep(1000);
    }
    
    @After
	public void testCreatePlaylistANDDeletePlayListANDPaterminar() throws Exception {
    	assertTrue(HttpController.createPlaylist("Help", token));
		assertTrue(HttpController.createPlaylist("Hello", token));
		Thread.sleep(1000);
    	assertEquals("NaN", HttpController.deletePlaylist("Help", token));;
    	Thread.sleep(1000);
		HttpController.logout(token);
		Thread.sleep(1000);
	}
}
