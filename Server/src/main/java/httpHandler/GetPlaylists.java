package httpHandler;

import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.PlaylistDAO;
import data.Playlist;

/**
 * 
 * @author Aimar
 * Class used to send to the client the names of all the  playlists 
 
 */
public class GetPlaylists implements HttpHandler{
	private static final Logger log = LogManager.getLogger(GetPlaylists.class);
	/**
	 * * 
	 * @return All the names of the playlists int the following format:
	 * playlist1#playlist2#playlist3#...
 *
	 */
	@Override
	public void handle(HttpExchange t) {
		try {
			
			
			String names = "";
			for(Playlist p : PlaylistDAO.getInstance().getAll()) {
				names += "#"+p.getName();
			}
			               
            t.sendResponseHeaders(200, names.length());
              
            OutputStream os = t.getResponseBody();
           
            
                         
            os.write(names.getBytes());
           
            os.close();
			
            log.info("GetPlaylists ok");
		} catch (Exception e) {
			log.error("GetPlaylists Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

