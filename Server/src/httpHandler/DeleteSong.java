package httpHandler;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.PlaylistDAO;
import data.Playlist;

/**
	 * 
	 * 
	 * Class used to Delete an audio file
	 *	

	 */
   public class DeleteSong implements HttpHandler{
	   private static final Logger log = LogManager.getLogger(DeleteSong.class);
	   /**
	    * When called, this input must be done through the headers:
	    * @param Name - The name of the song file
	    * @param plName - The name of the playlist the song belongs to
	    */
   	@Override
   	public void handle(HttpExchange t) {
   		try {
   			List<String> a = t.getRequestHeaders().getOrDefault("Name", List.of(""));
   			List<String> b = t.getRequestHeaders().getOrDefault("plName", List.of(""));
   			File f = new File("audios/"+a.get(0));
   			Playlist p = PlaylistDAO.getInstance().find(b.get(0));
   			p.removeSong(a.get(0));
   			String response;
   			if(f.delete()) {
   				System.out.println("Deleted " + a.get(0));
   				response = "ok";
   			} else {
   				response = "not ok";
   			}
   			
   			 t.sendResponseHeaders(200, response.length());
                
                OutputStream os = t.getResponseBody();
               
                
                             
                os.write(response.getBytes());
               
                os.close();
                log.info("DeleteSong ok: Song: " + a.get(0));
			} catch (Exception e) {
				log.error("DeleteSong Error: " + e.getMessage());
   			e.printStackTrace();
   		}
   	}
   }
