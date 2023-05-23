
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dao.PlaylistDAO;
import dao.SongDAO;
import dao.UserDAO;
import data.Playlist;
import data.Song;
import data.User;

public class DataBaseTest {
	private Song s;
	private Playlist pl;
	@Before
	public void insert() throws InterruptedException {
		User u = new User("Aitor", "pop");
		User u2 = new User("Eneko", "popitas");
		u.setAdmin(true);
		u.setPassword("1234");
		s = new Song("Rick Roll 2", "pito");
		pl = new Playlist("try2");
		pl.getSongs().add(s);
		u.getPlaylist().put("try2",pl);
		UserDAO.getInstance().save(u);
		u2.setPassword("1234");
		Song s2 = new Song("Rick Roll ", "pito");
		Playlist pl2 = new Playlist("try1");
		pl2.getSongs().add(s2);
		u2.getPlaylist().put("try1",pl2);
		u.setPassword("new1234");
		UserDAO.getInstance().updateUser(u);
		UserDAO.getInstance().save(u2);
		Thread.sleep(2000);
	}
	
	@Test
	//@Ignore
	public void test() {
		User u3 = UserDAO.getInstance().find("Eneko");
		assertEquals("Eneko", u3.getUsername());
		assertEquals(pl.getName(), PlaylistDAO.getInstance().find("try2").getName());
		assertEquals(s.getName(), SongDAO.getInstance().find("Rick Roll 2").getName());
		assertEquals("try1", u3.getPlaylist().get("try1").getName());
	}

}
