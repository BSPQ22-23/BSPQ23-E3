package httpHandler;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.PlaylistDAO;
import dao.SongDAO;
import dao.UserDAO;
import data.Playlist;
import data.Song;
import data.User;
import Server.Main;
/**
 * 
 * 
 * Class used to Delete a playlist and the songs it contains

 */
public class DeletePlaylist implements HttpHandler{
	private static final Logger log = LogManager.getLogger(DeletePlaylist.class);
	/**
	 *  *	
	 * When called, this input must be done through the headers:
	 * @param Name - The name of the playlist we want to delete
	 * @param User - The token to identify the user
	 */
	@Override
	public void handle(HttpExchange t) {
		try {
			List<String> a = t.getRequestHeaders().getOrDefault("Name", List.of(""));
			List<String> b =  t.getRequestHeaders().getOrDefault("User", List.of(""));
			String s = "NaN";
			User u = UserDAO.getInstance().find(Main.userMap.get(b.get(0)).getUsername());
			Playlist p = PlaylistDAO.getInstance().find(a.get(0));
			if(u.getPlaylist().containsKey(a.get(0))) {
				
				u.getPlaylist().remove(a.get(0));
				
				
				UserDAO.getInstance().updateUser(u);
    			PlaylistDAO.getInstance().delete(p);
    			
    			for (Song l : p.getSongs()) {
					File f = new File("audios/" + l.getName());
					SongDAO.getInstance().delete(l);
					f.delete();
				}
    			
			}            
            t.sendResponseHeaders(200, s.length());
              
            OutputStream os = t.getResponseBody();
           
            
                         
            os.write(s.getBytes());
           
            os.close();
			
            log.info("RegisterHandler ok");
		} catch (Exception e) {
			log.error("RegisterHandler Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}