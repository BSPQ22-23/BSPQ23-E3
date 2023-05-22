package test.java;

import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.remoteConnection.HttpController;

public class HttpControllerTestLaterVersion {
	private String token;
	@Before
	public void startup() throws Exception {
		 HttpController.setService("127.0.0.1", 8000);
		 token = HttpController.login("YO", "1234");
		 HttpController.sendFile("src/main/resources/popipo.wav", "WiWi", "LoLo", token);
		 Thread.sleep(1000);
	}

    @Test
    public void deleteSongTest() throws URISyntaxException, InterruptedException, ExecutionException {
    	assertTrue(HttpController.deleteSong("LoLo_popipo.wav", token));
    	Thread.sleep(1000);
    }

    @After
	public void paterminar() throws Exception {
		HttpController.logout(token);
		Thread.sleep(1000);
	}
}
