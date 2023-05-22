package dao;

import org.junit.Before;
import org.junit.Test;
//import static org.mockito.Mockito.*;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import data.Playlist;

public class PlaylistDAOTest {
   /* private PlaylistDAO playlistDAO;
    private PersistenceManager pm;
    private Transaction tx;
    private PersistenceManagerFactory pmf;

    @Before
    public void setup() {
        playlistDAO = PlaylistDAO.getInstance();
        pm = mock(PersistenceManager.class);
        pmf = mock(PersistenceManagerFactory.class);
        tx = mock(Transaction.class);
        when(pmf.getPersistenceManager()).thenReturn(pm);
        when(pm.currentTransaction()).thenReturn(tx);
        playlistDAO.setPmf(pmf);
    }

    @Test
    public void testSave() {
        Playlist playlist = new Playlist();
        playlistDAO.save(playlist);
        verify(pm).makePersistent(playlist);
    }

    @Test
    public void testDelete() {
        Playlist playlist = new Playlist();
        playlistDAO.delete(playlist);
        verify(pm).deletePersistent(playlist);
    }

    @Test
    public void testGetAll() {
        playlistDAO.getAll();
        verify(pm).getExtent(Playlist.class, true);
    }

    @Test
    public void testFind() {
        String name = "test";
        playlistDAO.find(name);
        verify(pm).newQuery("SELECT FROM " + Playlist.class.getName() + " WHERE name == '"+name+"'");
    }*/
}
