package httpHandler;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * 
 * 
 * Class used to Send a file
 *	

 * 

 */
public class SendMusicToClientHandler implements HttpHandler{
	private static final Logger log = LogManager.getLogger(SendMusicToClientHandler.class);
	/**
	 * When called, this input must be done through the headers:
	 * @param songName - The name of the song file - Must be passed through the headres
	 * 
 	 * @return Data of the music file, encoded in base64
	 */
	@Override
    public void handle(HttpExchange t) {
    	try {
    		
    		List<String> a = t.getRequestHeaders().getOrDefault("songName", List.of(""));
           	DataInputStream dis = new DataInputStream(new FileInputStream("audios/"+a.get(0)));
        	
            byte[] ba = dis.readAllBytes();
            
            System.out.println(ba.length);
            t.sendResponseHeaders(200, ba.length);
            
            
            OutputStream os = t.getResponseBody();
            //t.getResponseBody().write(ba);
                           
            os.write(ba);
            dis.close();
            os.close();
            log.info("SendMusicToClientHandler ok: bytes:" + ba.length);
		} catch (Exception e) {
			log.error("SendMusicToClientHandler Error: " + e.getMessage());
			e.printStackTrace();
		}
    	
    }
}
