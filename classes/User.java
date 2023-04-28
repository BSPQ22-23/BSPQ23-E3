package classes;

import java.util.ArrayList;
import java.util.List;

public class User {

	protected String id;
	protected String username;
	protected String password;
	protected boolean admin;
	protected List<Playlist> playlists;
	
	public User(String username, String password, boolean admin, List<Playlist> playlists) {
		super();
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.playlists = playlists;
	}
	
	public User() {
		super();
		this.username = "";
		this.password = "";
		this.admin = true;
		this.playlists = new ArrayList<Playlist>();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin
				+ ", playlists=" + playlists + "]";
	}	
}
