package data;


import java.util.HashMap;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class User {
	@PrimaryKey
	private String username;
	private String password;
	@Join
	@Persistent(defaultFetchGroup = "true")
	private HashMap<String, Playlist> playlist = new HashMap<String, Playlist>();
	private boolean admin = false;
	
	
	public User (String name, String Password) {
		username = name;
		this.password = Password;
	}
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public Playlist getSpecificPL(String name) {
		if(!playlist.containsKey(name)) {
			playlist.put(name, new Playlist(name));
		}
		return playlist.get(name);
	}
	public void addPlaylist(String name) {
		if(!playlist.containsKey(name)) {
			playlist.put(name, new Playlist(name));
		}
	}

	public HashMap<String, Playlist> getPlaylist() {
		return playlist;
	}



	public void setPlaylist(HashMap<String, Playlist> playlist) {
		this.playlist = playlist;
	}



	public boolean isAdmin() {
		return admin;
	}



	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
