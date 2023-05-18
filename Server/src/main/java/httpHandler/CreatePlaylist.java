package httpHandler;

import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.UserDAO;

import Server.Main;

/**
 * 
 * 
 * Class used to Create a playlist for a user
 *	

 */
public class CreatePlaylist implements HttpHandler{
	private static final Logger log = LogManager.getLogger(CreatePlaylist.class);
	/**
	 *  When called, this input must be done through the headers:
	 * @param ListName - The name of the playlist to create
	 * @param Username - The token to identify the user
	 * 
	 * @return String, "ok" if the playlist has been succesfully created
	 */
	@Override
	public void handle(HttpExchange t) {
		try {
        	 		
    		
    		
    		List<String> a = t.getRequestHeaders().getOrDefault("ListName", List.of(""));
    		List<String> b = t.getRequestHeaders().getOrDefault("Username", List.of(""));
    		System.out.println(a.get(0));
    		UserDAO.getInstance().find(Main.userMap.get(b.get(0)).getUsername()).addPlaylist(a.get(0));
    		UserDAO.getInstance().updateUser(UserDAO.getInstance().find(b.get(0)));
            t.sendResponseHeaders(200, 2);
              
            OutputStream os = t.getResponseBody();
           
            String s = "ok";
                         
            os.write(s.getBytes());
           
            os.close();
            log.info("CreatePlaylist ok: " + a.get(0));
		} catch (Exception e) {
			log.error("CreatePlaylist Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
