package data;
import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Song {
	private String name;
	
	public Song(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
