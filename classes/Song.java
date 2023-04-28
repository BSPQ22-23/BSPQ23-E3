package classes;

public class Song {
	
	protected String id;
	protected String name;
	protected String artist;
	protected String album;
	protected String dir;
	
	public Song(String name, String artist, String album, String dir) {
		super();
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.dir = dir;
	}
	
	public Song() {
		super();
		this.name = "";
		this.artist = "";
		this.album = "";
		this.dir = "";
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

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", album=" + album + ", dir=" + dir + "]";
	}
}
