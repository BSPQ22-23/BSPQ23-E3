package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
public class Main {
	public static void main(String[] args) throws Exception {
		
		
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/audioSend", new RecieveFileFromClientHandler()); //If a message arrives, does what it orders
        server.createContext("/audioAsk", new SendMusicToClientHandler());
        server.createContext("/avilableSongSend", new SendAvilableSongsHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started...");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
        	try {
        		List<String> a = t.getRequestHeaders().getOrDefault("user", List.of(""));
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
    static class RecieveFileFromClientHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
        	try {
        		
        		List<String> a = t.getRequestHeaders().getOrDefault("songName", List.of(""));
        		List<String> b = t.getRequestHeaders().getOrDefault("songAlbum", List.of(""));
        		//TODO pass the name from b to Database
        		System.out.println(b.get(0));
               	DataOutputStream dis = new DataOutputStream(new FileOutputStream("audios/"+a.get(0)));
            	
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
    
    static class SendMusicToClientHandler implements HttpHandler{
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
                
                System.out.println(ba.length);               
                os.write(ba);
                dis.close();
                os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }
    }
    
    static class SendAvilableSongsHandler implements HttpHandler{
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
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        }
    }


}
