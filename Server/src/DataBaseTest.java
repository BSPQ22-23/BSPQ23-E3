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
		User u = new User();
		u.setUsername("Eneko");
		u.setAdmin(true);
		u.setPassword("1234");
		Song s = new Song("Rick Roll");
		Playlist pl = new Playlist("try1");
		pl.getSongs().add(s);
		u.getPlaylist().add(pl);
		UserDAO.getInstance().save(u);
		assertEquals(u, UserDAO.getInstance().find("Eneko"));
		assertEquals(pl, PlaylistDAO.getInstance().find("try1"));
		assertEquals(s, SongDAO.getInstance().find("Rick Roll"));
	}

}
