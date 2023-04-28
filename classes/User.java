package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

	protected String username;
	protected String password;
	protected boolean admin;
	protected Map<String, List<Song>> playlists;
	
	public User(String username, String password, boolean admin, Map<String, List<Song>> playlists) {
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
		this.playlists = new HashMap<String, List<Song>>();
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

	public Map<String, List<Song>> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Map<String, List<Song>> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", admin=" + admin + ", playlists=" + playlists
				+ "]";
	}	
}
