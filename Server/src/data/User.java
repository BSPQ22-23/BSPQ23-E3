package data;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class User {

	private String username;
	private String password;
	private ArrayList<Playlist> playlist = new ArrayList<Playlist>();
	private boolean admin = false;
	
	
	
	public void addSong(Song song) {
		
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



	public ArrayList<Playlist> getPlaylist() {
		return playlist;
	}



	public void setPlaylist(ArrayList<Playlist> playlist) {
		this.playlist = playlist;
	}



	public boolean isAdmin() {
		return admin;
	}



	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
