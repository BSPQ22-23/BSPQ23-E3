package httpHandler;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.UserDAO;
import data.Song;
import data.User;
import Server.Main;

/**
 * 
 * Class used to recieve a file from a client side
 *	
 */
public class ReceiveFileFromClientHandler implements HttpHandler {
	private static final Logger log = LogManager.getLogger(ReceiveFileFromClientHandler.class);
	/**
	 * Recieves a file from the client.
	 * @return String recieved if it has been correctly recieved the data.
	 */
	@Override
    public void handle(HttpExchange t) {
    	try {
    		
    		List<String> a = t.getRequestHeaders().getOrDefault("songName", List.of(""));
    		List<String> b = t.getRequestHeaders().getOrDefault("songAlbum", List.of(""));
    		List<String> c = t.getRequestHeaders().getOrDefault("PlayList", List.of(""));
    		List<String> d = t.getRequestHeaders().getOrDefault("User", List.of(""));
    		
    		System.out.println(b.get(0));
    		User u = UserDAO.getInstance().find(Main.userMap.get(d.get(0)).getUsername());
    		u.getSpecificPL(c.get(0)).addSong(new Song(a.get(0), b.get(0)));
    		UserDAO.getInstance().updateUser(u);
           	DataOutputStream dis = new DataOutputStream(new FileOutputStream("audios/"+a.get(0)));
           	
            String response = "Recieved";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            dis.write(t.getRequestBody().readAllBytes());
            dis.close();
            os.write(response.getBytes());
            os.close();
            log.info("RecieveFileFromClientHandler ok: " + a.get(0) + b.get(0));
		} catch (Exception e) {
			log.error("RecieveFileFromClientHandler Error: " + e.getMessage());
			e.printStackTrace();
		}
    	
    }
    
}
