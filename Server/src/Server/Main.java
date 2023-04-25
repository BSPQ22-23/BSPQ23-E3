package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import data.Playlist;
import data.Song;
public class Main {
	public static void main(String[] args) throws Exception {
		
		Files.createDirectories(Paths.get("./audios"));
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/audioSend", new RecieveFileFromClientHandler()); //If a message arrives, does what it orders
        server.createContext("/audioAsk", new SendMusicToClientHandler());
        server.createContext("/avilableSongSend", new SendAvilableSongsHandler());
        server.createContext("/getPlaylistSongs", new SendPlaylistSongs());
        server.createContext("/createPlaylist", new CreatePlaylist());
        server.createContext("/addSongToPlaylist", new AddSongToPlaylist());
        server.createContext("/getPlaylists", new GetPlaylists());
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
    
    static class SendPlaylistSongs implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
        	
        		String names = "";
        		
        		
        		
        		List<String> a = t.getRequestHeaders().getOrDefault("ListName", List.of(""));
        		System.out.println(a.get(0));
        		for(Song f : Playlist.getPlaylist(a.get(0)).getSongs()) {
        			names+="#" + f.getName();
        			
        		}
                t.sendResponseHeaders(200, names.length());
                  
                OutputStream os = t.getResponseBody();
               
                
                             
                os.write(names.getBytes());
               
                os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }

    static class CreatePlaylist implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
            	 		
        		
        		
        		List<String> a = t.getRequestHeaders().getOrDefault("ListName", List.of(""));
        		System.out.println(a.get(0));
        		Playlist.createPlaylist(a.get(0));
                t.sendResponseHeaders(200, 2);
                  
                OutputStream os = t.getResponseBody();
               
                String s = "ok";
                             
                os.write(s.getBytes());
               
                os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    static class AddSongToPlaylist implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
    		List<String> a = t.getRequestHeaders().getOrDefault("ListName", List.of(""));
    		List<String> b = t.getRequestHeaders().getOrDefault("SongName", List.of(""));
    		Playlist.addSongToPlaylist(a.get(0),b.get(0));
    		t.sendResponseHeaders(200, 2);
            
            OutputStream os = t.getResponseBody();
           
            String s = "ok";
                         
            os.write(s.getBytes());
           
            os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    static class GetPlaylists implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
    			List<String> a = t.getRequestHeaders().getOrDefault("Playlist", List.of(""));
    			
    			String names = "";
    			for(Playlist p : Playlist.getAllPlaylists().values()) {
    				names += "#"+p.getName();
    			}
    			               
                t.sendResponseHeaders(200, names.length());
                  
                OutputStream os = t.getResponseBody();
               
                
                             
                os.write(names.getBytes());
               
                os.close();
    			
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    static class LoginHandler implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
    			List<String> a = t.getRequestHeaders().getOrDefault("Name", List.of(""));
    			List<String> b = t.getRequestHeaders().getOrDefault("Password", List.of(""));
    			String s = "ok";
    			//TODO login funcionality
    			
    			               
                t.sendResponseHeaders(200, s.length());
                  
                OutputStream os = t.getResponseBody();
               
                
                             
                os.write(s.getBytes());
               
                os.close();
    			
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    static class RegisterHandler implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
    			List<String> a = t.getRequestHeaders().getOrDefault("Name", List.of(""));
    			List<String> b = t.getRequestHeaders().getOrDefault("Password", List.of(""));
    			String s = "ok";
    			//TODO register funcionality
    			
    			               
                t.sendResponseHeaders(200, s.length());
                  
                OutputStream os = t.getResponseBody();
               
                
                             
                os.write(s.getBytes());
               
                os.close();
    			
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }

}
