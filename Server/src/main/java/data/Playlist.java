package data;

import java.util.ArrayList;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Playlist {
	/**
	 * the song list of the playlist
	 */
	@Join
	@Persistent(defaultFetchGroup = "true")
	private ArrayList<Song> songList;
	/**
	 * the name of the playlist
	 */
	private String name;

	/**
	 * 
	 * @param name
	 */
	public Playlist(String name) {
		this.name = name;
		songList = new ArrayList<>();
	}

	/**
	 * 
	 * @param void
	 */
	public Playlist() {

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
	 * @return arraylist of songs
	 */
	public ArrayList<Song> getSongs() {
		return songList;
	}

	/**
	 * 
	 * @param song
	 */
	public void addSong(Song s) {
		songList.add(s);
	}

	/**
	 * 
	 * @param song
	 */
	public void removeSong(String s) {
		for (Song song : songList) {
			if (song.getName().equals(s)) {
				songList.remove(songList.indexOf(song));
			}
		}
	}

}
