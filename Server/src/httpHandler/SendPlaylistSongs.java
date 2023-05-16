package httpHandler;

import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.PlaylistDAO;
import data.Song;

/**
 * 
 * @author Aimar
 * Class used to Send all the names of the songs that belong to a playlist
 *	
 *
 */
public class SendPlaylistSongs implements HttpHandler{
	private static final Logger log = LogManager.getLogger(SendPlaylistSongs.class);
	/**
	 *  When called, this input must be done:
	 * @param ListName - The name of the playlist - Must be passed through the headers
	 * 
	 * @return All the names of the songs at the playlist with the following format:
	 * song1#song2#song3#...
	 */
	@Override
	public void handle(HttpExchange t) {
		try {
    	
    		String names = "";
    		
    		
    		
    		List<String> a = t.getRequestHeaders().getOrDefault("ListName", List.of(""));
    		System.out.println(a.get(0));
    		for(Song f : PlaylistDAO.getInstance().find(a.get(0)).getSongs()) {
    			names+="#" + f.getName();
    			
    		}
            t.sendResponseHeaders(200, names.length());
              
            OutputStream os = t.getResponseBody();
           
            
                         
            os.write(names.getBytes());
           
            os.close();
            log.info("SendPlaylistSongs ok: Songs: " + a);
		} catch (Exception e) {
			log.error("SendPlaylistSongs Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
