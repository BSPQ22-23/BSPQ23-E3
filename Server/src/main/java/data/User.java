package data;

import java.util.HashMap;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class User {
	/**
	 * The name of the user
	 */
	@PrimaryKey
	private String username;
	/**
	 * The password of the user
	 */
	private String password;
	@Join
	@Persistent(defaultFetchGroup = "true")
	/**
	 * The playlists of the user
	 */
	private HashMap<String, Playlist> playlist = new HashMap<String, Playlist>();
	/**
	 * The role of the user
	 */
	private boolean admin = false;

	/**
	 * 
	 * @param name
	 * @param Password
	 */
	public User(String name, String Password) {
		username = name;
		this.password = Password;
	}
	public User() {
		
    }


	/**
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @param name
	 * @return an specifica playlist
	 */
	public Playlist getSpecificPL(String name) {
		if (!playlist.containsKey(name)) {
			playlist.put(name, new Playlist(name));
		}
		return playlist.get(name);
	}

	/**
	 * 
	 * @param name
	 */
	public void addPlaylist(String name) {
		if (!playlist.containsKey(name)) {
			playlist.put(name, new Playlist(name));
		}
	}

	/**
	 * 
	 * @return HashMap with playlists
	 */
	public HashMap<String, Playlist> getPlaylist() {
		return playlist;
	}

	/**
	 * 
	 * @param playlist
	 */
	public void setPlaylist(HashMap<String, Playlist> playlist) {
		this.playlist = playlist;
	}

	/**
	 * 
	 * @return boolean indicating if is adming
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * 
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
