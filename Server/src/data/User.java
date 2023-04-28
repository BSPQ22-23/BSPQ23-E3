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
}
