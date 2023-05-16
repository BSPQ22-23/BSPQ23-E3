package httpHandler;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/**
 * 
 * @author Aimar
 * Class used to Send all the avilable songs at the server
 * 
 * @return All the names of the songs
 */
public class SendAvilableSongsHandler implements HttpHandler{
	private static final Logger log = LogManager.getLogger(SendAvilableSongsHandler.class);
	/**
	 * @return String - list of all the avilable songs at the server, with the following format:
	 * song1#song2#song3#...
	 * 
	 */
	@Override
    public void handle(HttpExchange t) {
    	try {
    		
    		File folder = new File("audios");
    		File[] listOfFiles = folder.listFiles();
    		String names = "";
    		
    		
    		for(File f : listOfFiles) {
    			if(f.isFile()) {
    				names += "#" + f.getName();
    				
    			}
    		}
    		List<String> a = t.getRequestHeaders().getOrDefault("Try", List.of(""));
    		System.out.println(a.get(0));
           
            t.sendResponseHeaders(200, names.length());
              
            OutputStream os = t.getResponseBody();
           
            
                         
            os.write(names.getBytes());
           
            os.close();
            log.info("SendAvilableSongsHandler ok: songs: " + a);
		} catch (Exception e) {
			log.error("SendAvilableSongsHandler Error: " + e.getMessage());
			e.printStackTrace();
		}
    	
    }
}
