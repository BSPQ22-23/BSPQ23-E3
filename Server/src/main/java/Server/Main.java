package Server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import httpHandler.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import dao.PlaylistDAO;
import dao.SongDAO;
import dao.UserDAO;
import data.Playlist;
import data.Song;
import data.User;
/**
 * 
 * @author Aimar\n
 * 
 * Main class of the Http Server.
 */
public class Main {
	private static final Logger log = LogManager.getLogger(Main.class);
	public static HashMap<String, User> userMap = new HashMap<String, User>();
	
	public static void main(String[] args) throws Exception {
		
		Files.createDirectories(Paths.get("./audios"));
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/audioSend", new ReceiveFileFromClientHandler()); //If a message arrives, does what it orders
        server.createContext("/audioAsk", new SendMusicToClientHandler());
        server.createContext("/avilableSongSend", new SendAvilableSongsHandler());
        server.createContext("/getPlaylistSongs", new SendPlaylistSongs());
        server.createContext("/createPlaylist", new CreatePlaylist());
        server.createContext("/getPlaylists", new GetPlaylists());
        server.createContext("/DeleteSong", new DeleteSong());
        server.createContext("/DeletePlaylist", new DeletePlaylist());
        server.createContext("/Register", new RegisterHandler());
        server.createContext("/Login", new LoginHandler());
        server.createContext("/Logout", new LogOutHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        log.info("Server started...");
    }

    
 

}
