package classes;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	protected String id;
	protected String name;
	protected List<Song> songs;
	
	public Playlist(String name, List<Song> songs) {
		super();
		this.name = name;
		this.songs = songs;
	}
	
	public Playlist() {
		super();
		this.name = "";
		this.songs = new ArrayList<Song>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", songs=" + songs + "]";
	}	
}
