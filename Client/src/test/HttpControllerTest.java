package test;

import static org.junit.Assert.*;
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


    @Test
    public void testSetService() throws IOException, URISyntaxException {
        HttpController.setService("localhost", 8080);
        String expected = "http://localhost:8080/";
        String actual = HttpController.getDestination();
        assertEquals(expected, actual);
    }

    @Test
    public void testSendRequest() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
        HttpController.setService("localhost", 8080);
        HttpResponse<String> response = HttpController.sendRequest("searchSong", Map.of("title", "Shape of You"));
        int expected = 200;
        int actual = response.statusCode();
        assertEquals(expected, actual);
    }


    @Test
    public void testSendFile() throws IOException, URISyntaxException, InterruptedException, ExecutionException {
        HttpController.setService("localhost", 8080);
        HttpResponse<String> response = HttpController.sendFile("src/songs/ds.wav", "pop", "workout", "user1");
        int expected = 200;
        int actual = response.statusCode();
        assertEquals(expected, actual);
    }


    @Test
    public void testRecieveFile() throws IOException, URISyntaxException, InterruptedException, ExecutionException {
        HttpController.setService("localhost", 8080);
        HttpController.recieveFile("ds.wav");
        File file = new File("src/songs/ds.wav");
        assertTrue(file.exists());
    }


    @Test
    public void testRecieveAvilableSongNames() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
        HttpController.setService("localhost", 8080);
        ArrayList<String> songNames = HttpController.recieveAvilableSongNames();
        assertTrue(songNames.contains("Shape of You"));
    }
    
    @Test
    public void testRecievePlaylistSongs() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
        HttpController.setService("localhost", 8080);
        ArrayList<String> playlistSongs = HttpController.recievePlaylistSongs("workout");
        assertTrue(playlistSongs.contains("song.mp3"));
    }


    @Test
	public void testCreatePlaylist() throws URISyntaxException, InterruptedException, ExecutionException, IOException {
		HttpController.setService("localhost", 8080);
		assertTrue(HttpController.createPlaylist("workout", "user1"));
		assertFalse(HttpController.createPlaylist("workout", "user1"));
		assertTrue(HttpController.recievePlaylistSongs("workout").isEmpty());
	}
}
