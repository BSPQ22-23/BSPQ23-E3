package classes;

public class Song {
	
	protected String name;
	protected String artist;
	protected String album;
	
	public Song(String name, String artist, String album) {
		super();
		this.name = name;
		this.artist = artist;
		this.album = album;
	}
	
	public Song() {
		super();
		this.name = "";
		this.artist = "";
		this.album = "";
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
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Song [name=" + name + ", artist=" + artist + ", album=" + album + "]";
	}
}
