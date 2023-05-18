package httpHandler;

import java.io.OutputStream;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.UserDAO;
import data.User;

/**
	 * 
	 * 
	 * Class used to Register a new user
	 *	

	 * 
	 * 
	 */
   public class RegisterHandler implements HttpHandler{
   	/**
   	 * 	When called, this input must be done:
	 * @param Name - The name of the user
	 * @param Password - The password of the user
	 * 
	 * @return "ok" if the register has been succesful
   	 */
	   @Override
   	public void handle(HttpExchange t) {
   		try {
   			List<String> a = t.getRequestHeaders().getOrDefault("Name", List.of(""));
   			List<String> b = t.getRequestHeaders().getOrDefault("Password", List.of(""));
   			String s = "ok";
   			System.out.println("Registered: " + a + " pass: " + b);
   			
   			User u = new User(a.get(0), b.get(0));
   			UserDAO.getInstance().save(u);
   			
   			               
               t.sendResponseHeaders(200, s.length());
                 
               OutputStream os = t.getResponseBody();
              
               
                            
               os.write(s.getBytes());
              
               os.close();
               
   			
   		}catch (Exception e) {
   			e.printStackTrace();
   		}
   	}
   }
