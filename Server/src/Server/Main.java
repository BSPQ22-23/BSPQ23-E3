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
import java.util.HashMap;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.PlaylistDAO;
import dao.SongDAO;
import dao.UserDAO;
import data.Playlist;
import data.Song;
import data.User;
public class Main {
	private static final Logger log = LogManager.getLogger(Main.class);
	private static HashMap<String, User> userMap = new HashMap<String, User>();
	
	public static void main(String[] args) throws Exception {
		
		Files.createDirectories(Paths.get("./audios"));
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/audioSend", new RecieveFileFromClientHandler()); //If a message arrives, does what it orders
        server.createContext("/audioAsk", new SendMusicToClientHandler());
        server.createContext("/avilableSongSend", new SendAvilableSongsHandler());
        server.createContext("/getPlaylistSongs", new SendPlaylistSongs());
        server.createContext("/createPlaylist", new CreatePlaylist());
        server.createContext("/getPlaylists", new GetPlaylists());
        server.createContext("/DeleteSong", new DeleteSong());
        server.createContext("/DeletePlaylist", new DeletePlaylist());
        server.createContext("/Register", new RegisterHandler());
        server.createContext("/Login", new LoginHandler());
        server.createContext("/Logout", new LogOutHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        log.info("Server started...");
    }

    static class RecieveFileFromClientHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) {
        	try {
        		
        		List<String> a = t.getRequestHeaders().getOrDefault("songName", List.of(""));
        		List<String> b = t.getRequestHeaders().getOrDefault("songAlbum", List.of(""));
        		List<String> c = t.getRequestHeaders().getOrDefault("PlayList", List.of(""));
        		List<String> d = t.getRequestHeaders().getOrDefault("User", List.of(""));
        		
        		System.out.println(b.get(0));
        		User u = UserDAO.getInstance().find(userMap.get(d.get(0)).getUsername());
        		u.getSpecificPL(c.get(0)).addSong(new Song(a.get(0), b.get(0)));
        		UserDAO.getInstance().updateUser(u);
               	DataOutputStream dis = new DataOutputStream(new FileOutputStream("audios/"+a.get(0)));
               	
                String response = "Recieved";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                dis.write(t.getRequestBody().readAllBytes());
                dis.close();
                os.write(response.getBytes());
                os.close();
                log.info("RecieveFileFromClientHandler ok: " + a.get(0) + b.get(0));
			} catch (Exception e) {
				log.error("RecieveFileFromClientHandler Error: " + e.getMessage());
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
                log.info("SendAvilableSongsHandler ok: songs: " + a);
			} catch (Exception e) {
				log.error("SendAvilableSongsHandler Error: " + e.getMessage());
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
        		for(Song f : PlaylistDAO.getInstance().find(a.get(0)).getSongs()) {
        			names+="#" + f.getName();
        			
        		}
                t.sendResponseHeaders(200, names.length());
                  
                OutputStream os = t.getResponseBody();
               
                
                             
                os.write(names.getBytes());
               
                os.close();
                log.info("SendPlaylistSongs ok: Songs: " + a);
			} catch (Exception e) {
				log.error("SendPlaylistSongs Error: " + e.getMessage());
				e.printStackTrace();
			}
    	}
    }
    static class DeleteSong implements HttpHandler{
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

    static class CreatePlaylist implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
            	 		
        		
        		
        		List<String> a = t.getRequestHeaders().getOrDefault("ListName", List.of(""));
        		List<String> b = t.getRequestHeaders().getOrDefault("Username", List.of(""));
        		System.out.println(a.get(0));
        		UserDAO.getInstance().find(userMap.get(b.get(0)).getUsername()).addPlaylist(a.get(0));
        		UserDAO.getInstance().updateUser(UserDAO.getInstance().find(b.get(0)));
                t.sendResponseHeaders(200, 2);
                  
                OutputStream os = t.getResponseBody();
               
                String s = "ok";
                             
                os.write(s.getBytes());
               
                os.close();
                log.info("CreatePlaylist ok: " + a.get(0));
			} catch (Exception e) {
				log.error("CreatePlaylist Error: " + e.getMessage());
				e.printStackTrace();
			}
    	}
    }
    
    
    static class GetPlaylists implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
    			//List<String> a = t.getRequestHeaders().getOrDefault("Playlist", List.of(""));
    			
    			String names = "";
    			for(Playlist p : PlaylistDAO.getInstance().getAll()) {
    				names += "#"+p.getName();
    			}
    			               
                t.sendResponseHeaders(200, names.length());
                  
                OutputStream os = t.getResponseBody();
               
                
                             
                os.write(names.getBytes());
               
                os.close();
    			
                log.info("GetPlaylists ok");
			} catch (Exception e) {
				log.error("GetPlaylists Error: " + e.getMessage());
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
    			String s = "notOK";
    			
    			User u =  UserDAO.getInstance().find(a.get(0));
    			if(u != null && u.getPassword().equals(b.get(0))) {
    				s = "" + System.currentTimeMillis();
    				userMap.put(s, u);
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
    
    static class RegisterHandler implements HttpHandler{
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
    
    static class DeletePlaylist implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
    			List<String> a = t.getRequestHeaders().getOrDefault("Name", List.of(""));
    			List<String> b =  t.getRequestHeaders().getOrDefault("User", List.of(""));
    			String s = "NaN";
    			User u = UserDAO.getInstance().find(userMap.get(b.get(0)).getUsername());
    			Playlist p = PlaylistDAO.getInstance().find(a.get(0));
    			if(u.getPlaylist().containsKey(a.get(0))) {
    				
    				u.getPlaylist().remove(a.get(0));
    				
    				
    				UserDAO.getInstance().updateUser(u);
        			PlaylistDAO.getInstance().delete(p);
        			
        			for (Song l : p.getSongs()) {
						File f = new File("audios/" + l.getName());
						SongDAO.getInstance().delete(l);
						f.delete();
					}
        			
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
    
    static class LogOutHandler implements HttpHandler{
    	@Override
    	public void handle(HttpExchange t) {
    		try {
    			
    			List<String> b =  t.getRequestHeaders().getOrDefault("User", List.of(""));
    			String s = "NaN";
    			;
    			if(userMap.remove(b.get(0)) !=  null) {
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

}
