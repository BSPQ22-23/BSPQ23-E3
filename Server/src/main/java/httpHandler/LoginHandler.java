package httpHandler;

import java.io.OutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.UserDAO;
import data.User;

import Server.Main;
/**
 * 
 * 
 * Class used to perform a login

 */
public class LoginHandler implements HttpHandler{
	private static final Logger log = LogManager.getLogger(LoginHandler.class);
	/**
	 *  *	
	 * When called, this input must be done through the headers:
	 * @param Name - The username of the client
	 * @param Password - The password of the client
	 * 
	 * @return returns a token to identify the user
	 */
	@Override
	public void handle(HttpExchange t) {
		try {
			List<String> a = t.getRequestHeaders().getOrDefault("Name", List.of(""));
			List<String> b = t.getRequestHeaders().getOrDefault("Password", List.of(""));
			String s = "notOK";
			
			User u =  UserDAO.getInstance().find(a.get(0));
			if(u != null && u.getPassword().equals(b.get(0))) {
				s = "" + System.currentTimeMillis();
				Main.userMap.put(s, u);
			}
			
			               
            t.sendResponseHeaders(200, s.length());
              
            OutputStream os = t.getResponseBody();
           
            
                         
            os.write(s.getBytes());
           
            os.close();
			
            log.info("LoginHandler ok");
		} catch (Exception e) {
			log.error("LoginHandler Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
