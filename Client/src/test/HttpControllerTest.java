package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import remoteConnection.HttpController;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HttpControllerTest {

	@Before
	public void startup() throws Exception {
		 HttpController.setService("127.0.0.1", 8000);
		 //HttpController.register("user1", "Contra");
		 HttpController.sendFile("src/songs/ds.wav", "WiWi", "LoLo", "user1");
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
        HttpController.setService("127.0.0.1", 8000);
        HttpResponse<String> response = HttpController.sendFile("src/songs/ds.wav", "pop", "LoLo", "user1");
        int expected = 200;
        int actual = response.statusCode();
        assertEquals(expected, actual);
    }


    @Test
    public void testRecieveFile() throws IOException, URISyntaxException, InterruptedException, ExecutionException {
        HttpController.setService("127.0.0.1", 8000);
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
        HttpController.setService("127.0.0.1", 8000);
        ArrayList<String> playlistSongs = HttpController.recievePlaylistSongs("LoLo");
        assertTrue(playlistSongs.contains("LoLo_ds.wav"));
    }


    @Test
	public void testCreatePlaylist() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
		HttpController.setService("127.0.0.1", 8000);
		assertTrue(HttpController.createPlaylist("Help", "user1"));
		assertFalse(HttpController.createPlaylist("Hello", "user1"));
		assertTrue(HttpController.recievePlaylistSongs("Huat	").isEmpty());
	}
}
