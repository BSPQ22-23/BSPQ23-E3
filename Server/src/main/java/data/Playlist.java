package data;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")

public class Playlist {
	@Join
	@Persistent(defaultFetchGroup = "true")
	private ArrayList<Song> songList;
	private String name;
	/**
	 * prueba
	 * @param name
	 */
	public Playlist(String name) {
		this.name = name;
		songList = new ArrayList<>();
	}
	public Playlist() {
       
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
	
	public void removeSong(String s) {
		for (Song song : songList) {
			if(song.getName().equals(s)) {
				songList.remove(songList.indexOf(song));
			}
		}
	}
	
}
