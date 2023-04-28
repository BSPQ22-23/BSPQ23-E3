package classes;

import java.util.ArrayList;
import java.util.List;

public class Album {
	
	protected String id;
	protected String name;
	protected String artist;
	protected String date;
	protected List<Song> songList;
	
	public Album(String name, String artist, String date, List<Song> songList) {
		super();
		this.name = name;
		this.artist = artist;
		this.date = date;
		this.songList = songList;
	}
	
	public Album() {
		super();
		this.name = "";
		this.artist = "";
		this.date = "";
		this.songList = new ArrayList<Song>();
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

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", name=" + name + ", artist=" + artist + ", date=" + date + ", songList=" + songList
				+ "]";
	}
}
