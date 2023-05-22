package data;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Song {
	/**
	 * The name of the song
	 */
	@PrimaryKey
	private String name;
	/**
	 * The name of the album
	 */
	private String album;

	/**
	 * 
	 * @param name
	 * @param album
	 */
	public Song(String name, String album) {
		this.name = name;
		this.album = album;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @return album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * 
	 * @param album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	public Song() {
       
    }
}
