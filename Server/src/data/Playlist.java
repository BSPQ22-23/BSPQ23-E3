package data;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Playlist {
	@Join
	@Persistent(defaultFetchGroup = "true")
	private ArrayList<Song> songList = new ArrayList<Song>();
	private String name;
	
	public Playlist(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Song> getSongs(){
		return songList;
	}
	
	public void addSong(Song s) {
		songList.add(s);
	}
	
}
