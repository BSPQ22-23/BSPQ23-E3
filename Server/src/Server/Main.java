package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
public class Main {
	public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/audioSend", new MyAudioHandler()); //Si le llega un mensaje con esto, hace lo que se llama
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started...");
    }

	//Move to a class
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
        	try {
        		List<String> a = t.getRequestHeaders().getOrDefault("user", List.of(""));
            	System.out.println(a.get(0));
            	System.out.println("Guatemala");
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
    static class MyAudioHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
        	try {
        		DataOutputStream dis = new DataOutputStream(new FileOutputStream("ola.wav"));
        		List<String> a = t.getRequestHeaders().getOrDefault("songName", List.of(""));
            	System.out.println(a.get(0));
            	System.out.println("Paraguay");
                String response = "Recieved";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                dis.write(t.getRequestBody().readAllBytes());
                dis.close();
                os.write(response.getBytes());
                os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }
        
    }


}
