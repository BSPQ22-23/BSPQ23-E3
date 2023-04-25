package data;

import java.util.ArrayList;
import java.util.HashMap;

public class Playlist {
	
	private ArrayList<Song> songList = new ArrayList<Song>();
	private String name;
	
	//Save all the current playlists
	static HashMap<String, Playlist> listPlaylist = new HashMap<String, Playlist>();
	
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
	
	static public Playlist getPlaylist(String name) {
		return listPlaylist.get(name);
	}
	static public void createPlaylist(String name) {
		listPlaylist.put(name, new Playlist(name));
	}
	static public void addSongToPlaylist(String list, String song) {
		listPlaylist.get(list).addSong(new Song(song));
	}
	static public HashMap<String, Playlist> getAllPlaylists(){
		return listPlaylist;
	}
}
