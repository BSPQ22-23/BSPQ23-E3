package httpHandler;

import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Server.Main;

/**
 * 
 * @author Aimar
 * Class used to perform a Logout

 */
public class LogOutHandler implements HttpHandler{
	private static final Logger log = LogManager.getLogger(LogOutHandler.class);
	/**
	 *  *	
	 * When called, this input must be done through the headers:
	 * @param User - The token to identify the user
	 * 
	 * @return "Ok" if is correctly logged out, "NaN" if is not correctly logged out.
	 */
	@Override
	public void handle(HttpExchange t) {
		try {
			
			List<String> b =  t.getRequestHeaders().getOrDefault("User", List.of(""));
			String s = "NaN";
			;
			if(Main.userMap.remove(b.get(0)) !=  null) {
				s = "Ok";
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