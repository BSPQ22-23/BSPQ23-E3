
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.PlaylistDAO;
import dao.SongDAO;
import dao.UserDAO;
import data.Playlist;
import data.Song;
import data.User;

public class DataBaseTest {

	@Test
	public void test() {
		User u = new User("Eneko", "pop");
		u.setUsername("Eneko");
		u.setAdmin(true);
		u.setPassword("1234");
		Song s = new Song("Rick Roll", "pito");
		Playlist pl = new Playlist("try1");
		pl.getSongs().add(s);
		u.getPlaylist().put("try1",pl);
		UserDAO.getInstance().save(u);
		User u2 = UserDAO.getInstance().find("Eneko");
		assertEquals(u.getUsername(), UserDAO.getInstance().find("Eneko").getUsername());
		assertEquals(pl.getName(), PlaylistDAO.getInstance().find("try1").getName());
		assertEquals(s.getName(), SongDAO.getInstance().find("Rick Roll").getName());
		assertEquals(u.getUsername(), u2.getUsername());
		assertEquals(pl.getName(), u2.getPlaylist().get("try1").getName());
	}

}
