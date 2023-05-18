package data;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Song {
	@PrimaryKey
	private String name;
	private String album;
	
	public Song(String name, String album) {
		this.name = name;
		this.album = album;
	}
	
	public String getName() {
		return this.name;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}
}