package Server;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
public class Main {
	public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started...");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
        	try {
        		List<String> a = t.getRequestHeaders().getOrDefault("user", List.of());
            	System.out.println(a.get(0));
                String response = "This is the response";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }
    }


}
